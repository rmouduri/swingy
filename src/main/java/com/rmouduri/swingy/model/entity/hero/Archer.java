package com.rmouduri.swingy.model.entity.hero;

import com.rmouduri.swingy.model.artifact.armor.LeatherArmor;
import com.rmouduri.swingy.model.artifact.helmet.Bycocket;
import com.rmouduri.swingy.model.artifact.weapon.Bow;
import com.rmouduri.swingy.model.artifact.weapon.Staff;
import com.rmouduri.swingy.model.artifact.weapon.WeaponFactory;
import com.rmouduri.swingy.model.statistic.Attack;
import com.rmouduri.swingy.model.statistic.Defense;
import com.rmouduri.swingy.model.statistic.HitPoints;

public class Archer extends AbstractHero {
    /**
     * The class name
     */
    public static final String DEFAULT_HERO_CLASS_NAME = "Archer";

    /**
     * The default archer's attack at level 1
     */
    public static final Attack DEFAULT_HERO_CLASS_ATTACK = new Attack(Double.valueOf(80.0));

    /**
     * The default archer's hit points at level 1
     */
    public static final HitPoints DEFAULT_HERO_CLASS_HIT_POINTS = new HitPoints(Long.valueOf(1050));

    /**
     * The default archer's defense at level 1
     */
    public static final Defense DEFAULT_HERO_CLASS_DEFENSE = new Defense(Double.valueOf(30));


    /**
     * Constructor for fresh Archer
     * @param nameParam
     */
    protected Archer(final String nameParam) {
        super(nameParam,
            Archer.DEFAULT_HERO_CLASS_NAME, Archer.DEFAULT_HERO_CLASS_ATTACK,
            Archer.DEFAULT_HERO_CLASS_HIT_POINTS, Archer.DEFAULT_HERO_CLASS_DEFENSE);
        this.equip(WeaponFactory.generateWeapon(Bow.DEFAULT_WEAPON_TYPE_NAME, 0));
    }

    /**
     * Constructor for an already existing Archer
     * @param nameParam
     * @param experienceParam
     * @param bycocketParam
     * @param leatherArmorParam
     * @param bowParam
     */
    protected Archer(final String nameParam, final long experienceParam,
            final Bycocket bycocketParam, final LeatherArmor leatherArmorParam, final Bow bowParam) {
        super(nameParam,
            Archer.DEFAULT_HERO_CLASS_NAME, Archer.DEFAULT_HERO_CLASS_ATTACK,
            Archer.DEFAULT_HERO_CLASS_HIT_POINTS, Archer.DEFAULT_HERO_CLASS_DEFENSE,
            experienceParam, bycocketParam, leatherArmorParam, bowParam);
    }
}
