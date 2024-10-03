package com.rmouduri.swingy.view.console;

import com.rmouduri.swingy.model.entity.hero.AbstractHero;
import com.rmouduri.swingy.model.game.menu.HeroSelectionMenu;

import java.util.List;

public class HeroSelectionMenuDisplayer implements Displayable<HeroSelectionMenu> {
    private static final String ERROR_MESSAGE_NO_AVAILABLE_HEROES = "\nNo available heroes, please create one.";
    private static final String MESSAGE_SELECT_HEROES_ABOVE = "Select one of the heroes above: ";

    /**
     * The hero selection menu
     */
    private HeroSelectionMenu heroSelectionMenu;

    /**
     * Constructor
     */
    public HeroSelectionMenuDisplayer() {}

    /**
     * Constructor
     * @param heroSelectionMenuParam
     */
    public HeroSelectionMenuDisplayer(final HeroSelectionMenu heroSelectionMenuParam) {
        this.setDisplayableItem(heroSelectionMenuParam);
    }

    @Override
    public void display() {
        System.out.println();
        final List<AbstractHero> heroesList = this.heroSelectionMenu.getHeroesList();

        for (AbstractHero hero : this.heroSelectionMenu.getHeroesList()) {
            System.out.println(hero);
        }

        System.out.print(MESSAGE_SELECT_HEROES_ABOVE);
    }

    /**
     * Display the error message for when no heroes are available to select
     */
    public void displayNoAvailableHeroesError() {
        System.out.println(ERROR_MESSAGE_NO_AVAILABLE_HEROES);
    }

    @Override
    public void setDisplayableItem(final HeroSelectionMenu heroSelectionMenuParam) {
        this.heroSelectionMenu = heroSelectionMenuParam;
    }
}
