package com.rmouduri.swingy.model.entity.hero;

import com.rmouduri.swingy.model.artifact.armor.Armor;
import com.rmouduri.swingy.model.artifact.helmet.Helmet;
import com.rmouduri.swingy.model.artifact.weapon.Weapon;
import com.rmouduri.swingy.model.entity.AbstractEntity;
import com.rmouduri.swingy.model.game.Game;
import com.rmouduri.swingy.model.statistic.Attack;
import com.rmouduri.swingy.model.statistic.Damage;
import com.rmouduri.swingy.model.statistic.Defense;
import com.rmouduri.swingy.model.statistic.HitPoints;

/**
 * AbstractHero
 */
public abstract class AbstractHero extends AbstractEntity {
    public static final String NAME_COLOR = "\u001B[36m";

    public static final String HTML_COLOR = "#1E90FF";

    /**
     * The Hero's experience
     */
    private long experience;

    /**
     * The Hero's experience advancement in the current level
     */
    private long overflowExperience;

    /**
     * The Hero's weapon artifact
     */
    private Weapon weapon;

    /**
     * The Hero's armor artifact
     */
    private Armor armor;

    /**
     * The Hero's helmet artifact
     */
    private Helmet helmet;


    /**
     * Constructor for fresh Hero
     * @param nameParam
     * @param heroClassParam
     */
    protected AbstractHero(final String nameParam, final String heroClassParam,
            final Attack baseAttackParam, final HitPoints baseHitPointsParam, final Defense baseDefenseParam) {
        this.setName(nameParam);
        this.setEntityClass(heroClassParam);

        this.setBaseAttack(baseAttackParam);
        this.setAttack(this.getBaseAttack());
        this.setBaseHitPoints(baseHitPointsParam);
        this.setMaxHitPoints(this.getBaseHitPoints());
        this.setCurrentHitPoints(this.getBaseHitPoints());
        this.setBaseDefense(baseDefenseParam);
        this.setDefense(this.getBaseDefense());

        this.setExperience(0);
        this.setLevel(1);
    }

    /**
     * Constructor for an already existing Hero
     * @param nameParam
     * @param heroClassParam
     * @param experienceParam
     * @param helmetParam
     * @param armorParam
     * @param weaponParam
     */
    protected AbstractHero(final String nameParam, final String heroClassParam,
            final Attack baseAttackParam, final HitPoints baseHitPointsParam, final Defense baseDefenseParam,
            final long experienceParam, final Helmet helmetParam,
            final Armor armorParam, final Weapon weaponParam) {
        this.setName(nameParam);
        this.setEntityClass(heroClassParam);

        this.setBaseAttack(baseAttackParam);
        this.setAttack(this.getBaseAttack());
        this.setBaseHitPoints(baseHitPointsParam);
        this.setMaxHitPoints(this.getBaseHitPoints());
        this.setCurrentHitPoints(this.getBaseHitPoints());
        this.setBaseDefense(baseDefenseParam);
        this.setDefense(this.getBaseDefense());

        this.setExperience(0);
        this.setLevel(1);
        this.receiveExperience(experienceParam, false);
        this.equip(helmetParam);
        this.equip(armorParam);
        this.equip(weaponParam);
    }

    /**
     * Get the Experience
     * @return the Experience
     */
    public long getExperience() { return this.experience; }

    /**
     * Set the Experience to @param ExperienceParam
     * @param experienceParam
     */
    private void setExperience(final long experienceParam) { this.experience = experienceParam; }

    /**
     * Get the Hero's overflowExperience
     * @return The Hero's overflowExperience
     */
    public long getOverflowExperience() { return this.overflowExperience; }

    /**
     * Set the Hero's overflowExperience
     * @param overflowExperienceParam
     */
    private void setOverflowExperience(final long overflowExperienceParam) { this.overflowExperience = overflowExperienceParam; }

    /**
     * Equip the weapon @param weaponParam
     * @param weaponParam
     */
    public void equip(final Weapon weaponParam) {
        weaponParam.setOwner(this);
        this.setWeapon(weaponParam);
        this.setDamage(new Damage(
            this.getWeapon().getDamage().getMinRawAttack() + this.getAttack().getRawStat(),
            this.getWeapon().getDamage().getMaxRawAttack() + this.getAttack().getRawStat()));
        this.setAttackSpeed(this.getWeapon().getAttackSpeed());
    }

    /**
     * Equip the helmet @param helmetParam
     * @param helmetParam
     */
    public void equip(final Helmet helmetParam) {
        if (this.getHelmet() != null) {
            this.setMaxHitPoints(
                new HitPoints(this.getMaxHitPoints().getRawStat() - this.getHelmet().getHitPoints().getRawStat()));
        }

        if (helmetParam == null) {
            this.setHelmet(null);
            return;
        }

        helmetParam.setOwner(this);
        this.setHelmet(helmetParam);
        this.setMaxHitPoints(new HitPoints(
                this.getMaxHitPoints().getRawStat() + this.getHelmet().getHitPoints().getRawStat()));
        this.setCurrentHitPoints(new HitPoints(
                this.getCurrentHitPoints().getRawStat() + this.getHelmet().getHitPoints().getRawStat()));

        if (getCurrentHitPoints().getRawStat() > getMaxHitPoints().getRawStat()) {
            this.setCurrentHitPoints(this.getMaxHitPoints());
        }
    }

    /**
     * Equip the armor @param armorParam
     * @param armorParam
     */
    public void equip(final Armor armorParam) {
        if (this.getArmor() != null) {
            this.setDefense(
                new Defense(this.getDefense().getRawStat() - this.getArmor().getDefense().getRawStat()));
        }

        if (armorParam == null) {
            this.setArmor(null);
            return;
        }

        armorParam.setOwner(this);
        this.setArmor(armorParam);
        this.setDefense(
            new Defense(this.getDefense().getRawStat() + this.getArmor().getDefense().getRawStat()));
    }

    /**
     * Get the Weapon
     * @return the Weapon
     */
    public Weapon getWeapon() { return this.weapon; }

    /**
     * Set the Weapon to @param WeaponParam
     * @param weaponParam
     */
    private void setWeapon(final Weapon weaponParam) {
        this.weapon = weaponParam;
    }

    /**
     * Get the Armor
     * @return the Armor
     */
    public Armor getArmor() { return this.armor; }

    /**
     * Set the Armor to @param ArmorParam
     * @param armorParam
     */
    private void setArmor(final Armor armorParam) {
        this.armor = armorParam;
    }

    /**
     * Get the Helmet
     * @return the Helmet
     */
    public Helmet getHelmet() { return this.helmet; }

    /**
     * Set the Helmet to @param HelmetParam
     * @param helmetParam
     */
    private void setHelmet(final Helmet helmetParam) {
        this.helmet = helmetParam;
    }

    /**
     * Get the xp required to level up from level to level + 1
     * @param level
     * @return The xp required to level up from level to level + 1
     */
    static public long getXpPerLevel(final long level) {
        return level * 1000 + (level - 1) * (level - 1) * 450;
    }

    /**
     * Upgrade the Hero's stats when leveling up, specific to the class
     */
    protected void upgradeStatsByLevelUp() {
        this.setAttack(new Attack(
            this.getAttack().getRawStat() + this.getBaseAttack().getRawStat() / 10));
        this.setDefense(new Defense(
            this.getDefense().getRawStat() + this.getBaseDefense().getRawStat() / 10));
        this.setMaxHitPoints(new HitPoints(
            this.getMaxHitPoints().getRawStat() + this.getBaseHitPoints().getRawStat() / 10));
        this.setCurrentHitPoints(new HitPoints(
            this.getCurrentHitPoints().getRawStat() + this.getBaseHitPoints().getRawStat() / 10));
    }

    /**
     * Level up the Hero's level and upgrade their stats accordingly
     */
    private void levelUp(final boolean verbose) {
        this.setLevel(this.getLevel() + 1);

        if (verbose && Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            System.out.println(String.format("%s leveled up to level %d!", this.getColoredName(), this.getLevel()));
        }

        this.upgradeStatsByLevelUp();
    }

    /**
     * Receive experienceReceived experience and level up if conditions met
     * @param experienceReceived
     */
    public void receiveExperience(final long experienceReceived, final boolean verbose) {
        long lvlUpXpRequired;

        if (verbose && Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            System.out.println(String.format("\n%s receives %d exp.", this.getColoredName(), experienceReceived));
        }

        this.setExperience(this.getExperience() + experienceReceived);
        this.setOverflowExperience(this.getOverflowExperience() + experienceReceived);
        while (this.getOverflowExperience() > 0) {
            lvlUpXpRequired = AbstractHero.getXpPerLevel(this.getLevel());

            if (this.getOverflowExperience() >= lvlUpXpRequired) {
                this.setOverflowExperience(this.getOverflowExperience() - lvlUpXpRequired);
                this.levelUp(verbose);
            } else {
                break ;
            }
        }
    }

    /**
     * Get a colored name
     * @return The colored name
     */
    @Override
    public String getColoredName() {
        return NAME_COLOR + this.getName() + RESET_COLOR;
    }

    /**
     * Get the HTML color
     * @return The HTML color
     */
    @Override
    public String getHtmlColor() {
        return HTML_COLOR;
    }

    @Override
    public String toString() {
        return String.format("%s: Lvl %d (%s)\n" +
            "  HP: \u001B[32m%d\u001B[0m; Atk: \u001B[31m%.1f\u001B[0m; Def: \u001B[34m%.1f\u001B[0m; AS: \u001B[33m%.1f\u001B[0m\n" +
            "  Weapon: T%d; Armor: %s; Helmet: %s\n", this.getColoredName(),
            this.getLevel(), this.getEntityClass(), this.getMaxHitPoints().getRawStat(), this.getAttack().getRawStat(),
            this.getDefense().getRawStat(), this.getAttackSpeed().getRawStat(),
            this.getWeapon().getTier(), this.getArmor() != null ? "T" + this.getArmor().getTier() : "None",
            this.getHelmet() != null ? "T" + this.getHelmet().getTier() : "None");
    }

    public String toHtmlString() {
        return String.format("<html><span style='color:%s;'>%s</span>: Lvl %d (%s)" +
                " HP: <span style='color:#3CB371;'>%d</span>;" +
                " Atk: <span style='color:red;'>%.1f</span>;" +
                " Def: <span style='color:blue;'>%.1f</span>;" +
                " AS: <span style='color:orange;'>%.1f</span>;" +
                " Weapon: T%d; Armor: %s; Helmet: %s</html>", this.getHtmlColor(), this.getName(),
                this.getLevel(), this.getEntityClass(), this.getMaxHitPoints().getRawStat(), this.getAttack().getRawStat(),
                this.getDefense().getRawStat(), this.getAttackSpeed().getRawStat(),
                this.getWeapon().getTier(), this.getArmor() != null ? "T" + this.getArmor().getTier() : "None",
                this.getHelmet() != null ? "T" + this.getHelmet().getTier() : "None");
    }
}
