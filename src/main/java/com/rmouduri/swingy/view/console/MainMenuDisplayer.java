package com.rmouduri.swingy.view.console;

import com.rmouduri.swingy.model.game.menu.MainMenu;

public class MainMenuDisplayer implements Displayable<MainMenu> {
    private static final String MAIN_MENU_SELECTION =
            "\nWould you like to Create a character \u001B[36m(Create/c)\u001B[0m or "
                + "Select an already existing character \u001B[36m(Select/s)\u001B[0m ?: ";

    /**
     * The main menu
     */
    private MainMenu mainMenu;

    /**
     * Constructor
     */
    public MainMenuDisplayer() {
        this.mainMenu = null;
    }

    /**
     * Constructor
     */
    public MainMenuDisplayer(final MainMenu mainMenuParam) {
        this.setDisplayableItem(mainMenuParam);
    }

    /**
     * Display the MainMenu
     */
    @Override
    public void display() {
        System.out.print(MainMenuDisplayer.MAIN_MENU_SELECTION);
    }

    @Override
    public void setDisplayableItem(final MainMenu mainMenuParam) {
        this.mainMenu = mainMenuParam;
    }
}
