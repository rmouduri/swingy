package com.rmouduri.swingy.model.game.menu;

import com.rmouduri.swingy.model.entity.hero.AbstractHero;
import com.rmouduri.swingy.model.game.Game;
import com.rmouduri.swingy.model.game.Startable;
import com.rmouduri.swingy.view.console.HeroSelectionMenuDisplayer;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HeroSelectionMenu implements Startable {
    /**
     * Hero selection menu displayer
     */
    private final HeroSelectionMenuDisplayer heroSelectionMenuDisplayer;

    /**
     * User input scanner
     */
    private Scanner userInputScanner;

    /**
     * The heroes' list
     */
    private List<AbstractHero> heroesList;

    /**
     * The selected hero
     */
    private AbstractHero selectedHero;

    /**
     * The game
     */
    private final Game game;

    /**
     * Constructor
     * @param gameParam
     */
    public HeroSelectionMenu(final Game gameParam) {
        this.heroSelectionMenuDisplayer = new HeroSelectionMenuDisplayer(this);
        this.userInputScanner = new Scanner(System.in);
        this.game = gameParam;
        this.initSelectHeroMenuButtons();
    }

    /**
     * Display the hero selection menu and process the user input
     */
    @Override
    public void start() {
        this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_SELECT_HERO_MENU);

        String heroNameInput;
        this.setHeroesList(this.game.getDatabase().getAllCharacters());

        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            if (this.getHeroesList() == null || this.getHeroesList().isEmpty()) {
                this.heroSelectionMenuDisplayer.displayNoAvailableHeroesError();
                return;
            }

            do {
                this.heroSelectionMenuDisplayer.display();
                heroNameInput = this.getHeroNameInput();
            } while (Game.INVALID_USER_INPUT.equals(heroNameInput));

            if (Game.USER_INPUT_BACK.equalsIgnoreCase(heroNameInput)) {
                this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_MAIN_MENU);
                this.setSelectedHero(null);
                return;
            } else if (Game.GUI_MODE.equalsIgnoreCase(heroNameInput)) {
                this.game.setDisplayMode(Game.GUI_MODE);
                return;
            }

            this.findAndSetHero(heroNameInput);
            final AbstractHero hero = this.getSelectedHero();
            if (hero != null) {
                this.game.setHero(hero);
                this.game.initNewMapPlayer();
                this.game.getMapPlayer().start();
            }
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            this.game.getSwingyWindow().displaySelectHeroGui(this.getHeroesList());
        }
    }

    /**
     * Find the hero in the heroesList and set it with setSelectedHero()
     * @param heroName
     */
    private void findAndSetHero(final String heroName) {
        this.setSelectedHero(this.getHeroesList().stream()
                .filter(hero -> heroName.equals(hero.getName()))
                .findFirst()
                .orElse(null));
    }

    /**
     * Get and check the user input
     * @return the user input if valid, else null
     */
    private String getHeroNameInput() {
        final String userInput;
        try {
            userInput = this.userInputScanner.nextLine();
        } catch (NoSuchElementException e) {
            this.userInputScanner = new Scanner(System.in);
            return Game.INVALID_USER_INPUT;
        }

        if (Game.USER_INPUT_BACK.equalsIgnoreCase(userInput)
                || Game.USER_INPUT_BACK.substring(0, 1).equalsIgnoreCase(userInput.toLowerCase())) {
            return Game.USER_INPUT_BACK;
        } else if (Game.GUI_MODE.equalsIgnoreCase(userInput)
                || Game.GUI_MODE.substring(0, 1).equalsIgnoreCase(userInput)) {
            return Game.GUI_MODE;
        }

        if (!Game.INVALID_USER_INPUT.equals(userInput)
                && this.getHeroesList().stream().anyMatch(hero -> userInput.equals(hero.getName()))) {
            return userInput;
        }

        Game.printErrorInvalidUserInput();
        return Game.INVALID_USER_INPUT;
    }

    /**
     * Initialize select hero menu buttons for gui mode
     */
    private void initSelectHeroMenuButtons() {
        this.game.getSwingyWindow().getSelectHeroGui().setSelectButtonListener(e -> {
            final String rawSelectedHero = this.game.getSwingyWindow().getSelectHeroGui().getSelectedHeroValue();
            if (rawSelectedHero == null) {
                return;
            }

            final String parsedHero = rawSelectedHero.split("<html><span style='color:#1E90FF;'>|</span>")[1];
            this.findAndSetHero(parsedHero);

            final AbstractHero hero = this.getSelectedHero();
            if (hero != null) {
                this.game.setHero(hero);
                this.game.initNewMapPlayer();
                this.game.getMapPlayer().start();
            }
        });

        this.game.getSwingyWindow().getSelectHeroGui().setConsoleModeButtonListener(e -> {
            this.game.setDisplayMode(Game.CONSOLE_MODE);
        });

        this.game.getSwingyWindow().getSelectHeroGui().setBackButtonListener(e -> {
            this.game.getMainMenu().start();
        });
    }

    /**
     * Get the SelectedHero
     * @return The SelectedHero
     */
    public AbstractHero getSelectedHero() { return this.selectedHero; }

    /**
     * Set the SelectedHero
     * @param selectedHeroParam
     */
    private void setSelectedHero(final AbstractHero selectedHeroParam) { this.selectedHero = selectedHeroParam; }

    /**
     * Get Heroes list
     * @return the heroes list
     */
    public List<AbstractHero> getHeroesList() { return this.heroesList; }

    /**
     * Set heroes list
     * @param heroesListParam
     */
    public void setHeroesList(List<AbstractHero> heroesListParam) { this.heroesList = heroesListParam; }
}
