package com.rmouduri.swingy.model.game.menu;

import com.rmouduri.swingy.model.game.Game;
import com.rmouduri.swingy.model.game.Startable;
import com.rmouduri.swingy.view.console.MainMenuDisplayer;

import java.util.Scanner;

public class MainMenu implements Startable {
    public static final String USER_INPUT_CREATE_CHARACTER = "create";

    public static final String USER_INPUT_SELECT_CHARACTER = "select";

    /**
     * The MainMenuDisplayer
     */
    private final MainMenuDisplayer mainMenuDisplayer;

    /**
     * User input scanner
     */
    private Scanner userInputScanner;

    /**
     * User input
     */
    private String userInput;

    /**
     * The swingy window
     */
    private final Game game;

    /**
     * Constructor
     */
    public MainMenu(final Game gameParam) {
        this.userInputScanner = new Scanner(System.in);
        this.userInput = null;
        this.mainMenuDisplayer = new MainMenuDisplayer(this);
        this.game = gameParam;
        this.initMainMenuButtons();
    }

    /**
     * Get and scan user input
     * @return The processed user input
     */
    private String processUserInput() {
        final String localUserInput;
        try {
            localUserInput = this.userInputScanner.nextLine();
        } catch (Exception e) {
            this.userInputScanner = new Scanner(System.in);
            return Game.INVALID_USER_INPUT;
        }

        if (Game.USER_INPUT_QUIT.equalsIgnoreCase(localUserInput)
                || Game.USER_INPUT_QUIT.substring(0, 1).equalsIgnoreCase(localUserInput)) {
            return Game.USER_INPUT_QUIT;
        } else if (Game.GUI_MODE.equalsIgnoreCase(localUserInput)
                || Game.GUI_MODE.substring(0, 1).equalsIgnoreCase(localUserInput)) {
            return Game.GUI_MODE;
        } else if (MainMenu.USER_INPUT_CREATE_CHARACTER.equalsIgnoreCase(localUserInput)
                || MainMenu.USER_INPUT_CREATE_CHARACTER.substring(0, 1).equalsIgnoreCase(localUserInput)) {
            return MainMenu.USER_INPUT_CREATE_CHARACTER;
        } else if (MainMenu.USER_INPUT_SELECT_CHARACTER.equalsIgnoreCase(localUserInput)
                || MainMenu.USER_INPUT_SELECT_CHARACTER.substring(0, 1).equalsIgnoreCase(localUserInput)) {
            return MainMenu.USER_INPUT_SELECT_CHARACTER;
        }

        Game.printErrorInvalidUserInput();
        return Game.INVALID_USER_INPUT;
    }

    /**
     * Display Main Menu and handle user input
     */
    @Override
    public void start() {
        this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_MAIN_MENU);
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            do {
                this.mainMenuDisplayer.display();
                this.setUserInput(this.processUserInput());
            } while (Game.INVALID_USER_INPUT.equals(this.getUserInput()));

            if (Game.USER_INPUT_QUIT.equals(this.getUserInput())) {
                this.game.quitGame();
            } else if (Game.GUI_MODE.equals(this.getUserInput())) {
                this.game.setDisplayMode(Game.GUI_MODE);
            } else if (MainMenu.USER_INPUT_CREATE_CHARACTER.equals(this.getUserInput())) {
                this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_CREATE_HERO_MENU);
            } else if (MainMenu.USER_INPUT_SELECT_CHARACTER.equals(this.getUserInput())) {
                this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_SELECT_HERO_MENU);
            }
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            this.game.getSwingyWindow().displayMainMenuGui();
        }
    }

    /**
     * Initialize main menu buttons for gui mode
     */
    private void initMainMenuButtons() {
        this.game.getSwingyWindow().getMainMenuGui().setSelectButtonListener(e -> {
            this.game.getHeroSelectionMenu().start();
        });

        this.game.getSwingyWindow().getMainMenuGui().setCreateButtonListener(e -> {
            this.game.getHeroCreationMenu().start();
        });

        this.game.getSwingyWindow().getMainMenuGui().setConsoleModeButtonListener(e -> {
            this.game.setDisplayMode(Game.CONSOLE_MODE);
        });

        this.game.getSwingyWindow().getMainMenuGui().setQuitButtonListener(e -> {
            this.game.quitGame();
        });
    }

    /**
     * Get user input
     * @return The user input
     */
    public String getUserInput() { return this.userInput; }

    /**
     * Set user input
     * @param userInput
     */
    private void setUserInput(String userInput) { this.userInput = userInput; }
}
