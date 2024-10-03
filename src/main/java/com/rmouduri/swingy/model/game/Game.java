package com.rmouduri.swingy.model.game;

import com.rmouduri.swingy.controller.database.Database;
import com.rmouduri.swingy.model.entity.hero.AbstractHero;
import com.rmouduri.swingy.model.environment.Map;
import com.rmouduri.swingy.model.game.menu.HeroCreationMenu;
import com.rmouduri.swingy.model.game.menu.HeroSelectionMenu;
import com.rmouduri.swingy.model.game.menu.MainMenu;
import com.rmouduri.swingy.view.gui.SwingyWindow;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game implements Startable {
    private static final String GAME_LOGO =
            "\n\t __      __       .__                                   __              _________       .__                      \n" +
            "\t/  \\    /  \\ ____ |  |   ____  ____   _____   ____    _/  |_  ____     /   _____/_  _  _|__| ____    ____ ___.__.\n" +
            "\t\\   \\/\\/   // __ \\|  | _/ ___\\/  _ \\ /     \\_/ __ \\   \\   __\\/  _ \\    \\_____  \\\\ \\/ \\/ /  |/    \\  / ___<   |  |\n" +
            "\t \\        /\\  ___/|  |_\\  \\__(  <_> )  Y Y  \\  ___/    |  | (  <_> )   /        \\\\     /|  |   |  \\/ /_/  >___  |\n" +
            "\t  \\__/\\  /  \\___  >____/\\___  >____/|__|_|  /\\___  >   |__|  \\____/   /_______  / \\/\\_/ |__|___|  /\\___  // ____|\n" +
            "\t       \\/       \\/          \\/            \\/     \\/                           \\/                \\//_____/ \\/     \n";

    /**
     * Constant for console mode
     */
    public static final String CONSOLE_MODE = "console";

    /**
     * Constant for gui mode
     */
    public static final String GUI_MODE = "gui";

    /**
     * Constant for invalid user input
     */
    public static final String INVALID_USER_INPUT = "";

    /**
     * Constant for quitting main menu
     */
    public static final String USER_INPUT_QUIT = "quit";

    /**
     * Constant for quitting current menu
     */
    public static final String USER_INPUT_BACK = "back";

    /**
     * Constant for invalid user input message
     */
    public static final String ERROR_MSG_INVALID_USER_INPUT = "Invalid user input.";

    public static final String TO_BE_DISPLAYED_MAIN_MENU = "Main Menu";
    public static final String TO_BE_DISPLAYED_SELECT_HERO_MENU = "Select Hero Menu";
    public static final String TO_BE_DISPLAYED_CREATE_HERO_MENU = "Create Hero Menu";
    public static final String TO_BE_DISPLAYED_BATTLE = "Battle";
    public static final String TO_BE_DISPLAYED_MAP = "Map";

    /**
     * The current hero in use
     */
    private AbstractHero hero;

    /**
     * The MapPlayer
     */
    private MapPlayer mapPlayer;

    /**
     * The current display mode
     */
    private static String displayMode;

    /**
     * Menu to be displayed
     */
    private String toBeDisplayed;

    /**
     * Boolean whether the game should quit at the next loop
     */
    private boolean quitGame;

    /**
     * The MainMenu
     */
    private final MainMenu mainMenu;

    /**
     * The HeroCreationMenu
     */
    private final HeroCreationMenu heroCreationMenu;

    /**
     * The HeroSelectionMenu
     */
    private final HeroSelectionMenu heroSelectionMenu;

    /**
     * The database
     */
    private final Database database;

    /**
     * The swingy window
     */
    private final SwingyWindow swingyWindow;

    /**
     * Constructor
     * @param displayModeParam
     */
    public Game(final String displayModeParam, final Database databaseParam, final SwingyWindow swingyWindowParam) {
        this.setHero(null);
        this.quitGame = false;
        this.database = databaseParam;
        this.swingyWindow = swingyWindowParam;

        this.mainMenu = new MainMenu(this);
        this.heroCreationMenu = new HeroCreationMenu(this);
        this.heroSelectionMenu = new HeroSelectionMenu(this);
        this.mapPlayer = new MapPlayer(this);

        this.setDisplayMode(displayModeParam);
        this.setToBeDisplayed(Game.TO_BE_DISPLAYED_MAIN_MENU);

        this.swingyWindow.displayMainMenuGui();
    }

    /**
     * Starts the game
     */
    @Override
    public void start() {
        Game.greet();

        while (!this.quitGame) {
            if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
                this.updateDisplay();
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                this.quitGame();
            }
        }
//        while (true) {
//            if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
//                this.mainMenu.display();
//                userInput = this.mainMenu.getUserInput();
//
//                if (Game.USER_INPUT_QUIT.equals(userInput)) {
//                    this.quitGame();
//                } else if (Game.GUI_MODE.equals(userInput)) {
//                    this.setDisplayMode(Game.GUI_MODE);
//                } else if (MainMenu.USER_INPUT_CREATE_CHARACTER.equals(userInput)) {
//                    this.heroCreationMenu.display();
//                    this.setHero(this.heroCreationMenu.getCreatedHero());
//                    this.database.addOrUpdateHero(this.getHero());
//                } else if (MainMenu.USER_INPUT_SELECT_CHARACTER.equals(userInput)) {
//                    this.heroSelectionMenu.display();
//                    this.setHero(this.heroSelectionMenu.getSelectedHero());
//                }
//
//                if (this.getHero() == null) {
//                    continue;
//                }
//
//                System.out.println("\n\u001B[32mGame starting\u001B[0m\n");
//                mapPlayer.start();
//            }
//            System.out.println("OK");
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {}
//        }
    }

    /**
     * Greet user with Swingy Logo
     */
    public static void greet() {
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            System.out.println(Game.GAME_LOGO);
        }
    }

    /**
     * Print the error message for when user enters invalid input
     */
    public static void printErrorInvalidUserInput() {
        System.err.println(Game.ERROR_MSG_INVALID_USER_INPUT);
    }

    /**
     * Quit the game
     */
    public void quitGame() {
        this.quitGame = true;
        System.out.println("\nQuitting Swingy...");
    }

    /**
     * Get the Hero
     * @return The Hero
     */
    public AbstractHero getHero() { return this.hero; }

    /**
     * Set the hero
     * @param heroParam
     */
    public void setHero(final AbstractHero heroParam) { this.hero = heroParam; }

    /**
     * Get the DisplayMode
     * @return The DisplayMode
     */
    public static String getDisplayMode() { return Game.displayMode; }

    /**
     * Set the displayMode
     * @param displayModeParam
     */
    public void setDisplayMode(final String displayModeParam) {
        Game.displayMode = displayModeParam;
        this.swingyWindow.setVisible(Game.GUI_MODE.equals(displayModeParam));

        if (Game.GUI_MODE.equalsIgnoreCase(displayModeParam)) {
            System.out.println("\nIn GUI Mode...");
            this.updateDisplay();
        } else try {
            System.in.read(new byte[System.in.available()]);
        } catch (IOException ignored) {}
    }

    /**
     * Update the display based on toBeDisplayed variable
     */
    private void updateDisplay() {
        if (Game.TO_BE_DISPLAYED_MAIN_MENU.equals(this.getToBeDisplayed())) {
            this.mainMenu.start();
        } else if (Game.TO_BE_DISPLAYED_SELECT_HERO_MENU.equals(this.getToBeDisplayed())) {
            this.heroSelectionMenu.start();
        } else if (Game.TO_BE_DISPLAYED_CREATE_HERO_MENU.equals(this.getToBeDisplayed())) {
            this.heroCreationMenu.start();
        } else if (Game.TO_BE_DISPLAYED_MAP.equals(this.getToBeDisplayed())) {
            this.mapPlayer.start();
        }
    }

    /**
     * Initializes the new Map and MapPlayer
     */
    public void initNewMapPlayer() {
        final Map map = new Map(this.getHero().getLevel());
        this.mapPlayer.setMap(map);
        this.mapPlayer.setHero(this.getHero());
    }

    /**
     * Get the MapPlayer
     * @return The MapPlayer
     */
    public MapPlayer getMapPlayer() { return this.mapPlayer; }

    /**
     * Set the MapPlayer
     * @param mapPlayerParam
     */
    public void setMapPlayer(final MapPlayer mapPlayerParam) { this.mapPlayer = mapPlayerParam; }

    /**
     * Get the SwingyWindow
     * @return The SwingyWindow
     */
    public SwingyWindow getSwingyWindow() { return this.swingyWindow; }

    /**
     * Get the MainMenu
     * @return The MainMenu
     */
    public MainMenu getMainMenu() { return this.mainMenu; }

    /**
     * Get the HeroCreationMenu
     * @return The HeroCreationMenu
     */
    public HeroCreationMenu getHeroCreationMenu() { return this.heroCreationMenu; }

    /**
     * Get the HeroSelectionMenu
     * @return The HeroSelectionMenu
     */
    public HeroSelectionMenu getHeroSelectionMenu() { return this.heroSelectionMenu; }

    /**
     * Get the Database
     * @return The Database
     */
    public Database getDatabase() { return this.database; }

    /**
     * Get To Be Displayed String
     * @return The To Be Displayed String
     */
    public String getToBeDisplayed() { return this.toBeDisplayed; }

    /**
     * Set the To Be Displayed String
     * @param toBeDisplayedParam
     */
    public void setToBeDisplayed(String toBeDisplayedParam) { this.toBeDisplayed = toBeDisplayedParam; }
}
