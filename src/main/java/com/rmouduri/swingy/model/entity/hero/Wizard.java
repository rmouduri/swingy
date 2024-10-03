package com.rmouduri.swingy.model.entity.hero;

import com.rmouduri.swingy.model.artifact.armor.Robe;
import com.rmouduri.swingy.model.artifact.helmet.MagicHat;
import com.rmouduri.swingy.model.artifact.weapon.Bow;
import com.rmouduri.swingy.model.artifact.weapon.Staff;
import com.rmouduri.swingy.model.artifact.weapon.WeaponFactory;
import com.rmouduri.swingy.model.statistic.Attack;
import com.rmouduri.swingy.model.statistic.Defense;
import com.rmouduri.swingy.model.statistic.HitPoints;

public class Wizard extends AbstractHero {
    /**
     * The class name
     */
    public static final String DEFAULT_HERO_CLASS_NAME = "Wizard";

    /**
     * The default archer's attack at level 1
     */
    public static final Attack DEFAULT_HERO_CLASS_ATTACK = new Attack(Double.valueOf(130.0));

    /**
     * The default archer's hit points at level 1
     */
    public static final HitPoints DEFAULT_HERO_CLASS_HIT_POINTS = new HitPoints(Long.valueOf(900));

    /**
     * The default archer's defense at level 1
     */
    public static final Defense DEFAULT_HERO_CLASS_DEFENSE = new Defense(Double.valueOf(25));


    /**
     * Constructor for fresh Wizard
     * @param nameParam
     */
    protected Wizard(final String nameParam) {
        super(nameParam,
            Wizard.DEFAULT_HERO_CLASS_NAME, Wizard.DEFAULT_HERO_CLASS_ATTACK,
            Wizard.DEFAULT_HERO_CLASS_HIT_POINTS, Wizard.DEFAULT_HERO_CLASS_DEFENSE);
        this.equip(WeaponFactory.generateWeapon(Staff.DEFAULT_WEAPON_TYPE_NAME, 0));
    }

    /**
     * Constructor for an already existing Wizard
     * @param nameParam
     * @param experienceParam
     * @param magicHatParam
     * @param robeParam
     * @param staffParam
     */
    protected Wizard(final String nameParam, final long experienceParam,
            final MagicHat magicHatParam, final Robe robeParam, final Staff staffParam) {
        super(nameParam,
            Wizard.DEFAULT_HERO_CLASS_NAME, Wizard.DEFAULT_HERO_CLASS_ATTACK,
            Wizard.DEFAULT_HERO_CLASS_HIT_POINTS, Wizard.DEFAULT_HERO_CLASS_DEFENSE,
            experienceParam, magicHatParam, robeParam, staffParam);
    }
}
