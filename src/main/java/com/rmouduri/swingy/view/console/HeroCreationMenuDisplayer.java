package com.rmouduri.swingy.view.console;

import com.rmouduri.swingy.model.entity.hero.AbstractHero;
import com.rmouduri.swingy.model.game.menu.HeroCreationMenu;

public class HeroCreationMenuDisplayer {
    /**
     * Error message for already existing hero
     */
    public static final String HERO_ALREADY_EXISTS = "Hero '%s' already exists.";

    /**
     * Hero Creation Menu
     */
    private HeroCreationMenu heroCreationMenu;

    /**
     * Constructor
     */
    public HeroCreationMenuDisplayer() {}

    /**
     * Constructor
     * @param heroCreationMenuParam
     */
    public HeroCreationMenuDisplayer(final HeroCreationMenu heroCreationMenuParam) {
        this.setDisplayableItem(heroCreationMenuParam);
    }

    /**
     * Display the different available classes
     */
    public void displayClassSelection() {
        System.out.println();
        for (final AbstractHero c : this.heroCreationMenu.getHeroClassesList()) {
            System.out.println(String.format("%s: HP: \u001B[32m%d\u001B[0m; Atk: \u001B[31m%.1f\u001B[0m; " +
                "Def: \u001B[34m%.1f\u001B[0m; AS: \u001B[33m%.1f\u001B[0m\n",
                AbstractHero.NAME_COLOR + c.getEntityClass() + AbstractHero.RESET_COLOR,
                c.getMaxHitPoints().getRawStat(), c.getAttack().getRawStat(),
                c.getDefense().getRawStat(), c.getAttackSpeed().getRawStat()));
        }

        System.out.print("Select one of the classes above: ");
    }

    /**
     * Print error message for already existing character of name @param name
     * @param name
     */
    public void printErrorHeroNameAlreadyExisting(final String name) {
        System.err.println(String.format(HeroCreationMenuDisplayer.HERO_ALREADY_EXISTS, name));
    }

    /**
     * Display the select username message
     */
    public void displayNameSelection() {
        System.out.print("\nEnter a character's name: ");
    }

    public void setDisplayableItem(final HeroCreationMenu heroCreationMenuParam) { this.heroCreationMenu = heroCreationMenuParam; }
}
