package com.rmouduri.swingy.model.game;

import com.rmouduri.swingy.model.artifact.armor.ArmorFactory;
import com.rmouduri.swingy.model.artifact.armor.HeavyArmor;
import com.rmouduri.swingy.model.artifact.armor.LeatherArmor;
import com.rmouduri.swingy.model.artifact.armor.Robe;
import com.rmouduri.swingy.model.artifact.helmet.Bycocket;
import com.rmouduri.swingy.model.artifact.helmet.HeavyHelmet;
import com.rmouduri.swingy.model.artifact.helmet.HelmetFactory;
import com.rmouduri.swingy.model.artifact.helmet.MagicHat;
import com.rmouduri.swingy.model.artifact.weapon.Bow;
import com.rmouduri.swingy.model.artifact.weapon.Staff;
import com.rmouduri.swingy.model.artifact.weapon.Sword;
import com.rmouduri.swingy.model.artifact.weapon.WeaponFactory;
import com.rmouduri.swingy.model.entity.enemy.AbstractEnemy;
import com.rmouduri.swingy.model.entity.hero.AbstractHero;
import com.rmouduri.swingy.model.entity.hero.Archer;
import com.rmouduri.swingy.model.entity.hero.Warrior;
import com.rmouduri.swingy.model.entity.hero.Wizard;
import com.rmouduri.swingy.model.environment.Map;
import com.rmouduri.swingy.model.environment.MapTile;
import com.rmouduri.swingy.model.environment.PlayerMapPosition;
import com.rmouduri.swingy.model.game.battle.Battle;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.Attributes;

import javax.swing.*;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class MapPlayer implements Startable {
    private static final int UP_ARROW_KEY = 65;
    private static final int DOWN_ARROW_KEY = 66;
    private static final int RIGHT_ARROW_KEY = 67;
    private static final int LEFT_ARROW_KEY = 68;
    private static final int ENTER_KEY = 13;
    private static final int ESC_KEY = 27;
    private static final int ARROW_KEY = 91;

    private static final String NORTH_DIRECTION = "North";
    private static final String SOUTH_DIRECTION = "South";
    private static final String EAST_DIRECTION = "East";
    private static final String WEST_DIRECTION = "West";

    private static final String FIGHT = "Fight";
    private static final String RUN = "Run";
    private static final String HERO_WON = "Hero won";
    private static final String HERO_LOST = "Hero lost";
    private static final String HERO_FLED = "Hero fled";

    private static final String PRESS_ARROW_KEYS_MESSAGE = "Press arrow keys (h for help): ";

    private static final String HELP_MESSAGE = "\n\n\u001B[36mArrow Keys\u001B[0m: Move to the arrow direction\n" +
            "\u001B[36mm\u001B[0m: Display the map\n" +
            "\u001B[36ms\u001B[0m: Display the hero's stats\n" +
            "\u001B[36mh\u001B[0m: Display the help menu\n" +
            "\u001B[36mg\u001B[0m: Switch to GUI mode\n" +
            "\u001B[36mq\u001B[0m: Quit the map\n";

    private static final String FIGHT_OR_RUN_PROMPT_CONSOLE =
        "You encountered a Lvl %d %s, do you wish to fight \u001B[36m(Fight/f)\u001B[0m of run away \u001B[36m(run/r)\u001B[0m ?: ";

    private static final String FIGHT_OR_RUN_PROMPT_GUI =
            "<html>You encountered a Lvl %d <span style='color:red;'>%s</span>, do you wish to fight or run away ?</html>";

    private static  final String ARTIFACT_FOUND = "%s found a T%d %s !";

    /**
     * The map being played
     */
    private Map map;

    /**
     * The hero playing
     */
    private AbstractHero hero;

    /**
     * The user input
     */
    private String userInput;

    /**
     * The game
     */
    private final Game game;

    /**
     * Constructor
     * @param gameParam
     */
    public MapPlayer(final Game gameParam) {
        this.game = gameParam;
        this.initMapPlayerButtons();
    }

    @Override
    public void start() {
        this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_MAP);
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            String direction;

            this.map.display();

            while (true) {
                direction = this.inputDirectionManager();

                if (Game.USER_INPUT_QUIT.equals(direction)) {
                    this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_MAIN_MENU);
                    return;
                } else if (Game.GUI_MODE.equals(direction)) {
                    this.game.setDisplayMode(Game.GUI_MODE);
                    return;
                } else if (this.canPlayerMoveTo(direction)) {
                    this.movePlayerTo(direction);

                    System.out.println();
                    this.map.display();

                    if (Game.USER_INPUT_QUIT.equals(this.handleTile())) {
                        this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_MAIN_MENU);
                        return;
                    }
                } else {
                    System.out.println("You can't move " + direction);
                }
            }
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            this.game.getSwingyWindow().getMapPlayerGui().getSideBar().addHero(this.hero);
            this.enableDirectionButtons();
            this.game.getSwingyWindow().getMapPlayerGui().getMapGui().setMap(this.getMap());
            this.game.getSwingyWindow().displayMapGui();
        }
    }

    /**
     * Handle the tile the player currently is in
     * @return If the game should quit after
     */
    private String handleTile() {
        final Random random = new Random();
        final MapTile playerTile = this.map.getPlayerTile();
        final AbstractEnemy enemy = playerTile.getEnemy();

        if (enemy != null) {
            final String result = this.encounterEnemy(playerTile, enemy);

            if (HERO_WON.equals(result)) {
                final int rng = random.nextInt(6);

                if (enemy.getName().contains("Boss")) {
                    this.dropArtifact(enemy, rng);
                    if (Game.GUI_MODE.equals((Game.getDisplayMode()))) {
                        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().updateHeroStats();
                    }
                    this.game.getDatabase().addOrUpdateHero(this.hero);
                    this.congratulate();
                    return Game.USER_INPUT_QUIT;
                } else if (rng >= 3) {
                    this.dropArtifact(enemy, rng);
                    if (Game.GUI_MODE.equals((Game.getDisplayMode()))) {
                        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().updateHeroStats();
                    }
                }
            } else if (HERO_LOST.equals(result)) {
                if (Game.GUI_MODE.equals((Game.getDisplayMode()))) {
                    this.game.getSwingyWindow().getMapPlayerGui().getSideBar().updateHeroStats();
                }
                this.displayLostMessage();
                return Game.USER_INPUT_QUIT;
            }
            this.game.getDatabase().addOrUpdateHero(this.hero);
        } else if (playerTile.isHealingTile()
                && this.hero.getCurrentHitPoints().getRawStat() < this.hero.getMaxHitPoints().getRawStat()
                && this.promptHeal()) {
            final long healedHP = playerTile.consumeHealing(this.hero);

            if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                System.out.println(String.format("%s healed \u001B[32m%d\u001B[0m HP.", hero.getName(), healedHP));
            }
        }

        return null;
    }

    /**
     * Quit the map to return to the main menu
     */
    private void quitMap() {
        this.game.getMainMenu().start();
    }

    /**
     * Display congratulation message
     */
    private void congratulate() {
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            System.out.println("\n\u001B[32mCongratulations, you conquered the map !\u001B[0m");
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            JOptionPane.showMessageDialog(this.game.getSwingyWindow(),
                    String.format("<html><span style='color:#3CB371;'>Congratulations, you conquered the map !</span></html>",
                            this.getHero().getName()),
                    "You won !", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Display the "You lost" message
     */
    private void displayLostMessage() {
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            System.out.println("\n\u001B[32mYou lost.\u001B[0m");
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            JOptionPane.showMessageDialog(this.game.getSwingyWindow(),
                    String.format("<html><span style='color:red;'>You lost.</span></html>",
                            this.getHero().getName()),
                    "Defeat", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Set enabled state of direction buttons
     */
    private void enableDirectionButtons() {
        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setNorthButtonEnabled(
                this.canPlayerMoveTo(MapPlayer.NORTH_DIRECTION));
        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setSouthButtonEnabled(
                this.canPlayerMoveTo(MapPlayer.SOUTH_DIRECTION));
        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setEastButtonEnabled(
                this.canPlayerMoveTo(MapPlayer.EAST_DIRECTION));
        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setWestButtonEnabled(
                this.canPlayerMoveTo(MapPlayer.WEST_DIRECTION));
    }

    /**
     * Initialize map player buttons for gui mode
     */
    private void initMapPlayerButtons() {
        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setQuitButtonListener(e -> {
            Object[] options = {"Leave", "Stay"};
            int n = JOptionPane.showOptionDialog(this.game.getSwingyWindow(),
                    "You will have to retry a new map of the current hero's level",
                    "Leave the current map ?",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, null);

            if (n == 0) {
                this.quitMap();
            }
        });

        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setConsoleModeButtonListener(e -> {
            this.game.setDisplayMode(Game.CONSOLE_MODE);
        });

        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setNorthButtonListener(e -> {
            this.handleGuiDirection(MapPlayer.NORTH_DIRECTION);
        });

        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setSouthButtonListener(e -> {
            this.handleGuiDirection(MapPlayer.SOUTH_DIRECTION);
        });

        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setEastButtonListener(e -> {
            this.handleGuiDirection(MapPlayer.EAST_DIRECTION);
        });

        this.game.getSwingyWindow().getMapPlayerGui().getSideBar().setWestButtonListener(e -> {
            this.handleGuiDirection(MapPlayer.WEST_DIRECTION);
        });
    }

    /**
     * Handle the direction gui button action
     * @param direction
     */
    private void handleGuiDirection(final String direction) {
        if (this.canPlayerMoveTo(direction)) {
            this.movePlayerTo(direction);
            this.game.getSwingyWindow().getMapPlayerGui().getMapGui().updateMap();

            final String result = this.handleTile();
            this.game.getSwingyWindow().getMapPlayerGui().getSideBar().updateHeroStats();

            if (Game.USER_INPUT_QUIT.equals(result)) {
                this.quitMap();
            }
        }
        this.enableDirectionButtons();
    }

    /**
     * Manage user input for directions
     * @return The direction
     */
    private String inputDirectionManager() {
        Terminal terminal = null;
        Attributes originalAttributes = null;
        this.setUserInput("");
        int ch;

        try {
            terminal = TerminalBuilder.terminal();
            originalAttributes = terminal.enterRawMode();

            System.out.println();
            System.out.print(PRESS_ARROW_KEYS_MESSAGE);
            while (true) {
                ch = terminal.reader().read();
                if (ch == ENTER_KEY && this.isDirectionValid(this.getUserInput())) {
                    System.out.println();
                    terminal.close();
                    return this.getUserInput();
                } else if (ch == 's') {
                    this.displayHeroStatsInTerminal(terminal, originalAttributes);
                } else if (ch == 'm') {
                    this.displayMapInTerminal(terminal, originalAttributes);
                } else if (ch == 'h') {
                    this.displayHelpInTerminal(terminal, originalAttributes);
                } else if (ch == 'q' && this.promptQuestionInTerminal(
                        "Do you want to quit the current map? (Yes/No): ", terminal, originalAttributes)) {
                    terminal.close();
                    return Game.USER_INPUT_QUIT;
                } else if (ch == 'g' && this.promptQuestionInTerminal(
                        "Do you want to switch to GUI mode? (Yes/No): ", terminal, originalAttributes)) {
                    terminal.close();
                    return Game.GUI_MODE;
                } else if (ch == ESC_KEY && terminal.reader().read() == ARROW_KEY) {
                    ch = terminal.reader().read();
                    switch (ch) {
                        case UP_ARROW_KEY:
                            this.setUserInput(this.manageArrowKeyPress(NORTH_DIRECTION, this.getUserInput().length()));
                            break;
                        case DOWN_ARROW_KEY:
                            this.setUserInput(this.manageArrowKeyPress(SOUTH_DIRECTION, this.getUserInput().length()));
                            break;
                        case RIGHT_ARROW_KEY:
                            this.setUserInput(this.manageArrowKeyPress(EAST_DIRECTION, this.getUserInput().length()));
                            break;
                        case LEFT_ARROW_KEY:
                            this.setUserInput(this.manageArrowKeyPress(WEST_DIRECTION, this.getUserInput().length()));
                            break;
                        default:
                            break;
                    }
                }
            } // While end
        } catch (IOException e) {
            System.err.println("Error in inputDirectionManager: " + e.getMessage());
            if (terminal != null) {
                terminal.setAttributes(originalAttributes);
                try {
                    terminal.close();
                } catch (IOException ex) {
                    return Game.INVALID_USER_INPUT;
                }
            }
            return Game.INVALID_USER_INPUT;
        }
    }

    /**
     * Encounter an enemy and prompt to fight or run away from it
     * @param playerTile
     * @param enemy
     * @return The result of the encounter
     */
    private String encounterEnemy(final MapTile playerTile, final AbstractEnemy enemy) {
        if (RUN.equals(this.promptFightOrRun(enemy))) {
            final Random random = new Random();

            if (random.nextInt(2) == 0) {
                this.displayHeroRanAway();
                return HERO_FLED;
            } else {
                this.displayHeroCouldntRunAway();
            }
        }

        final Battle battle = new Battle(this.hero, enemy, this.game);
        battle.start();
        battle.display();

        if (battle.getBattleSummary().getWinner() == this.hero) {
            playerTile.updateEnemy();
            this.hero.receiveExperience((long) ((AbstractHero.getXpPerLevel(enemy.getLevel()) * .667)
                    * (enemy.getName().contains("Boss") ? 2.5 : 1)), true);
            return HERO_WON;
        } else {
            return HERO_LOST;
        }
    }

    /**
     * Display the hero ran away successfully
     */
    private void displayHeroRanAway() {
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            System.out.println(String.format("%s ran away.", this.hero.getColoredName()));
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            JOptionPane.showMessageDialog(this.game.getSwingyWindow(),
                    String.format("<html><span style='color:#1E90FF;'>%s</span> ran away.</html>",
                            this.getHero().getName()),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Display the hero failed to run away
     */
    private void displayHeroCouldntRunAway() {
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            System.out.println(String.format("%s couldn't run away.\n", this.hero.getColoredName()));
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            JOptionPane.showMessageDialog(this.game.getSwingyWindow(),
                    String.format("<html><span style='color:#1E90FF;'>%s</span> couldn't run away.</html>",
                            this.getHero().getName()),
                    "Failure",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Print @param amt escape characters
     * @param amt
     */
    private void printEscapeChar(final int amt) {
        final String esc = "\u001B[D";

        System.out.print(esc.repeat(amt) + "\u001B[J");
        System.out.flush();
    }

    /**
     * Escape, print and store direction
     * @param direction
     * @param escLength
     * @return The direction string
     */
    private String manageArrowKeyPress(final String direction, final int escLength) {
        final String userInput = direction;

        this.printEscapeChar(escLength);
        System.out.print(userInput);
        return userInput;
    }

    /**
     * Check whether userInput is a valid direction
     * @param userInput
     * @return true is userInput is a valid direction, false otherwise
     */
    private boolean isDirectionValid(final String userInput) {
        return NORTH_DIRECTION.equals(userInput) || SOUTH_DIRECTION.equals(userInput) ||
            EAST_DIRECTION.equals(userInput) || WEST_DIRECTION.equals(userInput);
    }

    /**
     * Check whether the player can move this direction or not
     * @param direction
     * @return true if the player can, false if not
     */
    private boolean canPlayerMoveTo(final String direction) {
        final PlayerMapPosition playerMapPosition = this.map.getPlayerPosition();

        if (NORTH_DIRECTION.equals(direction) && playerMapPosition.getY() > 0) {
            return true;
        } else if (SOUTH_DIRECTION.equals(direction) && playerMapPosition.getY() < this.map.getMapLineSize() - 1) {
            return true;
        } else if (EAST_DIRECTION.equals(direction) && playerMapPosition.getX() < this.map.getMapLineSize() - 1) {
            return true;
        } else if (WEST_DIRECTION.equals(direction) && playerMapPosition.getX() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Move to the direction @param direction
     * @param direction
     */
    private void movePlayerTo(final String direction) {
        final PlayerMapPosition playerMapPosition = this.map.getPlayerPosition();

        if (NORTH_DIRECTION.equals(direction)) {
            this.map.movePlayerTo(playerMapPosition.getX(), playerMapPosition.getY() - 1);
        } else if (SOUTH_DIRECTION.equals(direction)) {
            this.map.movePlayerTo(playerMapPosition.getX(), playerMapPosition.getY() + 1);
        } else if (EAST_DIRECTION.equals(direction)) {
            this.map.movePlayerTo(playerMapPosition.getX() + 1, playerMapPosition.getY());
        } else if (WEST_DIRECTION.equals(direction) && this.map.getPlayerPosition().getX() > 0) {
            this.map.movePlayerTo(playerMapPosition.getX() - 1, playerMapPosition.getY());
        }
    }

    /**
     * Prompt a question
     * @param question
     * @param terminal
     * @param originalAttributes
     * @return True if yes, false otherwise
     */
    private boolean promptQuestionInTerminal(final String question, Terminal terminal, Attributes originalAttributes) {
        this.printEscapeChar(PRESS_ARROW_KEYS_MESSAGE.length() + this.getUserInput().length());
        terminal.setAttributes(originalAttributes);

        final boolean answer = this.promptQuestion(question);

        terminal.enterRawMode();
        this.setUserInput("");
        if (!answer) {
            System.out.print(PRESS_ARROW_KEYS_MESSAGE);
        }
        return answer;
    }

    /**
     * Display the hero stats
     * @param terminal
     * @param originalAttributes
     */
    private void displayHeroStatsInTerminal(Terminal terminal, Attributes originalAttributes) {
        terminal.setAttributes(originalAttributes);

        System.out.println(String.format(
            "\n\n%s Lvl %d %s\n" +
            "Exp:    \u001B[35m%d/%d\u001B[0m\n" +
            "HP:     \u001B[32m%d/%d\u001B[0m\n" +
            "Atk:    \u001B[31m%.1f\u001B[0m\n" +
            "Def:    \u001B[34m%.1f\u001B[0m\n" +
            "AtkSpd: \u001B[33m%.1f\u001B[0m\n" +
            "Weapon: T%d; Armor: %s; Helmet: %s\n",
            this.hero.getColoredName(), this.hero.getLevel(), this.hero.getEntityClass(),
            this.hero.getOverflowExperience(), AbstractHero.getXpPerLevel(this.hero.getLevel()),
            this.hero.getCurrentHitPoints().getRawStat(), this.hero.getMaxHitPoints().getRawStat(),
            this.hero.getAttack().getRawStat(),
            this.hero.getDefense().getRawStat(), this.hero.getAttackSpeed().getRawStat(),
            this.hero.getWeapon().getTier(),
            this.hero.getArmor() != null ? "T" + this.hero.getArmor().getTier() : "None",
            this.hero.getHelmet() != null ? "T" + this.hero.getHelmet().getTier() : "None")
        );

        terminal.enterRawMode();
        this.setUserInput("");
        System.out.print(PRESS_ARROW_KEYS_MESSAGE);
    }

    /**
     * Display the map
     * @param terminal
     * @param originalAttributes
     */
    private void displayMapInTerminal(Terminal terminal, Attributes originalAttributes) {
        terminal.setAttributes(originalAttributes);

        System.out.println();
        System.out.println();
        this.map.display();
        System.out.println();

        terminal.enterRawMode();
        this.setUserInput("");
        System.out.print(PRESS_ARROW_KEYS_MESSAGE);
    }

    /**
     * Prompt a question
     * @param terminal
     * @param originalAttributes
     */
    private void displayHelpInTerminal(Terminal terminal, Attributes originalAttributes) {
        terminal.setAttributes(originalAttributes);

        System.out.println(HELP_MESSAGE);

        terminal.enterRawMode();
        this.setUserInput("");
        System.out.print(PRESS_ARROW_KEYS_MESSAGE);
    }

    /**
     * Ask the user if they want to fight or run away from @parma enemy
     * @param enemy
     * @return FIGHT or RUN
     */
    public String promptFightOrRun(final AbstractEnemy enemy) {
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            Scanner scanner = new Scanner(System.in);
            String userInput = null;

            System.out.println();
            do {
                System.out.print(String.format(FIGHT_OR_RUN_PROMPT_CONSOLE, enemy.getLevel(), enemy.getColoredName()));
                try {
                    userInput = scanner.nextLine();
                    if (FIGHT.equalsIgnoreCase(userInput) || "f".equalsIgnoreCase(userInput)) {
                        System.out.println();
                        return FIGHT;
                    } else if (RUN.equalsIgnoreCase(userInput) || "r".equalsIgnoreCase(userInput)) {
                        System.out.println();
                        return RUN;
                    }
                } catch (NoSuchElementException e) {
                    scanner = new Scanner(System.in);
                    System.out.println();
                }
            } while (true);
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            Object[] options = {"Fight", "Run away"};
            int n = JOptionPane.showOptionDialog(this.game.getSwingyWindow(),
                    String.format(FIGHT_OR_RUN_PROMPT_GUI, enemy.getLevel(), enemy.getName()),
                    "Enemy encountered",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, null);

            return n == 0 ? FIGHT : RUN;
        }

        return RUN;
    }

    /**
     * Prompt whether the user wants to heal or not
     * @return True if the player wants to heal, false otherwise
     */
    public boolean promptHeal() {
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            return this.promptQuestion(String.format(
                    "Your HP is \u001B[32m%d/%d\u001B[0m, do you want to heal? (Yes/No): ",
                    this.hero.getCurrentHitPoints().getRawStat(), this.hero.getMaxHitPoints().getRawStat()));
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(this.game.getSwingyWindow(),
                    String.format(
                            "<html>Your HP is <span style='color:#3CB371;'>%d/%d</span>, do you want to heal?</html>",
                            this.hero.getCurrentHitPoints().getRawStat(), this.hero.getMaxHitPoints().getRawStat()),
                    "You found a healing tile",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, null);

            return n == 0;
        }

        return false;
    }

    /**
     * Ask the user a question @param question with yes or no input
     * @param question
     * @return true if user entered yes, false otherwise
     */
    private boolean promptQuestion(final String question) {
        Scanner scanner = new Scanner(System.in);
        String userInput = null;

        System.out.println();
        do {
            System.out.print(question);
            try {
                userInput = scanner.nextLine();
                if ("yes".equalsIgnoreCase(userInput) || "y".equalsIgnoreCase(userInput)) {
                    return true;
                } else if ("no".equalsIgnoreCase(userInput) || "n".equalsIgnoreCase(userInput)) {
                    return false;
                }
            } catch (NoSuchElementException e) {
                scanner = new Scanner(System.in);
                System.out.println();
            }
        } while (true);
    }

    /**
     * Drop an artifact from the class of the hero of level mapLevel / 2 (+ 1 if boss)
     * @param enemy
     */
    private void dropArtifact(final AbstractEnemy enemy, final int rng) {
        final int artifactLevel = Math.min((this.map.getLevel() / 2 + (enemy.getName().contains("Boss") ? 1 : 0)), 7);
        final String[] archerArtifacts = {
                Bow.DEFAULT_WEAPON_TYPE_NAME, Bycocket.DEFAULT_HELMET_TYPE_NAME, LeatherArmor.DEFAULT_ARMOR_TYPE_NAME};
        final String[] warriorArtifacts = {
                Sword.DEFAULT_WEAPON_TYPE_NAME, HeavyHelmet.DEFAULT_HELMET_TYPE_NAME, HeavyArmor.DEFAULT_ARMOR_TYPE_NAME};
        final String[] wizardArtifacts = {
                Staff.DEFAULT_WEAPON_TYPE_NAME, MagicHat.DEFAULT_HELMET_TYPE_NAME, Robe.DEFAULT_ARMOR_TYPE_NAME};

        if (Archer.DEFAULT_HERO_CLASS_NAME.equals(this.hero.getEntityClass())) {
            if (rng % 3 == 0 && this.hero.getWeapon().getTier() < artifactLevel) {
                this.hero.equip(WeaponFactory.generateWeapon(archerArtifacts[rng % 3], artifactLevel));
                if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                    System.out.println(String.format(ARTIFACT_FOUND,
                            this.hero.getColoredName(), artifactLevel, archerArtifacts[rng % 3]));
                }
            } else if (rng % 3 == 1 && (this.hero.getHelmet() == null || this.hero.getHelmet().getTier() < artifactLevel)) {
                this.hero.equip(HelmetFactory.generateHelmet(archerArtifacts[rng % 3], artifactLevel));
                if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                    System.out.println(String.format(ARTIFACT_FOUND,
                            this.hero.getColoredName(), artifactLevel, archerArtifacts[rng % 3]));
                }
            } else if (rng % 3 == 2 && (this.hero.getArmor() == null || this.hero.getArmor().getTier() < artifactLevel)) {
                this.hero.equip(ArmorFactory.generateArmor(archerArtifacts[rng % 3], artifactLevel));
                if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                    System.out.println(String.format(ARTIFACT_FOUND,
                            this.hero.getColoredName(), artifactLevel, archerArtifacts[rng % 3]));
                }
            }
        } else if (Warrior.DEFAULT_HERO_CLASS_NAME.equals(this.hero.getEntityClass())) {
            if (rng % 3 == 0 && this.hero.getWeapon().getTier() < artifactLevel) {
                this.hero.equip(WeaponFactory.generateWeapon(warriorArtifacts[rng % 3], artifactLevel));
                if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                    System.out.println(String.format(ARTIFACT_FOUND,
                            this.hero.getColoredName(), artifactLevel, warriorArtifacts[rng % 3]));
                }
            } else if (rng % 3 == 1 && (this.hero.getHelmet() == null || this.hero.getHelmet().getTier() < artifactLevel)) {
                this.hero.equip(HelmetFactory.generateHelmet(warriorArtifacts[rng % 3], artifactLevel));
                if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                    System.out.println(String.format(ARTIFACT_FOUND,
                            this.hero.getColoredName(), artifactLevel, warriorArtifacts[rng % 3]));
                }
            } else if (rng % 3 == 2 && (this.hero.getArmor() == null || this.hero.getArmor().getTier() < artifactLevel)) {
                this.hero.equip(ArmorFactory.generateArmor(warriorArtifacts[rng % 3], artifactLevel));
                if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                    System.out.println(String.format(ARTIFACT_FOUND,
                            this.hero.getColoredName(), artifactLevel, warriorArtifacts[rng % 3]));
                }
            }
        } else if (Wizard.DEFAULT_HERO_CLASS_NAME.equals(this.hero.getEntityClass())) {
            if (rng % 3 == 0 && this.hero.getWeapon().getTier() < artifactLevel) {
                this.hero.equip(WeaponFactory.generateWeapon(wizardArtifacts[rng % 3], artifactLevel));
                if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                    System.out.println(String.format(ARTIFACT_FOUND,
                            this.hero.getColoredName(), artifactLevel, wizardArtifacts[rng % 3]));
                }
            } else if (rng % 3 == 1 && (this.hero.getHelmet() == null || this.hero.getHelmet().getTier() < artifactLevel)) {
                this.hero.equip(HelmetFactory.generateHelmet(wizardArtifacts[rng % 3], artifactLevel));
                if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                    System.out.println(String.format(ARTIFACT_FOUND,
                            this.hero.getColoredName(), artifactLevel, wizardArtifacts[rng % 3]));
                }
            } else if (rng % 3 == 2 && (this.hero.getArmor() == null || this.hero.getArmor().getTier() < artifactLevel)) {
                this.hero.equip(ArmorFactory.generateArmor(wizardArtifacts[rng % 3], artifactLevel));
                if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                    System.out.println(String.format(ARTIFACT_FOUND,
                            this.hero.getColoredName(), artifactLevel, wizardArtifacts[rng % 3]));
                }
            }
        }
    }

    /**
     * Get the userInput
     * @return the userInput
     */
    private String getUserInput() { return this.userInput; }

    /**
     * Set the userInput
     * @param userInputParam
     */
    private void setUserInput(final String userInputParam) { this.userInput = userInputParam; }

    /**
     * Get the Map
     * @return The Map
     */
    public Map getMap() { return this.map; }

    /**
     * Set the Map
     * @param map
     */
    public void setMap(final Map map) { this.map = map; }

    /**
     * Get the Hero
     * @return The Hero
     */
    public AbstractHero getHero() { return this.hero; }

    /**
     * Set the Hero
     * @param hero
     */
    public void setHero(final AbstractHero hero) { this.hero = hero; }
}
