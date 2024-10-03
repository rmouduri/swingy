package com.rmouduri.swingy.model.entity.hero;

import com.rmouduri.swingy.model.artifact.armor.HeavyArmor;
import com.rmouduri.swingy.model.artifact.helmet.HeavyHelmet;
import com.rmouduri.swingy.model.artifact.weapon.Bow;
import com.rmouduri.swingy.model.artifact.weapon.Sword;
import com.rmouduri.swingy.model.artifact.weapon.WeaponFactory;
import com.rmouduri.swingy.model.statistic.Attack;
import com.rmouduri.swingy.model.statistic.Defense;
import com.rmouduri.swingy.model.statistic.HitPoints;

public class Warrior extends AbstractHero {
    /**
     * The class name
     */
    public static final String DEFAULT_HERO_CLASS_NAME = "Warrior";

    /**
     * The default warrior's attack at level 1
     */
    public static final Attack DEFAULT_HERO_CLASS_ATTACK = new Attack(Double.valueOf(85.0));

    /**
     * The default warrior's hit points at level 1
     */
    public static final HitPoints DEFAULT_HERO_CLASS_HIT_POINTS = new HitPoints(Long.valueOf(1350));

    /**
     * The default warrior's defense at level 1
     */
    public static final Defense DEFAULT_HERO_CLASS_DEFENSE = new Defense(Double.valueOf(40));


    /**
     * Constructor for fresh Warrior
     * @param nameParam
     */
    protected Warrior(final String nameParam) {
        super(nameParam,
            Warrior.DEFAULT_HERO_CLASS_NAME, Warrior.DEFAULT_HERO_CLASS_ATTACK,
            Warrior.DEFAULT_HERO_CLASS_HIT_POINTS, Warrior.DEFAULT_HERO_CLASS_DEFENSE);
        this.equip(WeaponFactory.generateWeapon(Sword.DEFAULT_WEAPON_TYPE_NAME, 0));
    }

    /**
     * Constructor for an already existing Warrior
     * @param nameParam
     * @param experienceParam
     * @param heavyHelmetParam
     * @param heavyArmorParam
     * @param swordParam
     */
    protected Warrior(final String nameParam, final long experienceParam,
            final HeavyHelmet heavyHelmetParam, final HeavyArmor heavyArmorParam, final Sword swordParam) {
        super(nameParam,
            Warrior.DEFAULT_HERO_CLASS_NAME, Warrior.DEFAULT_HERO_CLASS_ATTACK,
            Warrior.DEFAULT_HERO_CLASS_HIT_POINTS, Warrior.DEFAULT_HERO_CLASS_DEFENSE,
            experienceParam, heavyHelmetParam, heavyArmorParam, swordParam);
    }
}
