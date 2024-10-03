package com.rmouduri.swingy.model.artifact.weapon;

import java.util.Arrays;
import java.util.List;

import com.rmouduri.swingy.model.entity.hero.Wizard;
import com.rmouduri.swingy.model.statistic.AttackSpeed;
import com.rmouduri.swingy.model.statistic.Damage;

public class Staff extends Weapon {
    /**
     * The damage stat per tier, i.e. rarity
     */
    public static final List<Damage> damagePerTier = Arrays.asList(
        new Damage(Double.valueOf(190.0), Double.valueOf(240.0)),
        new Damage(Double.valueOf(230.0), Double.valueOf(275.0)),
        new Damage(Double.valueOf(240.0), Double.valueOf(285.0)),
        new Damage(Double.valueOf(250.0), Double.valueOf(295.0)),
        new Damage(Double.valueOf(260.0), Double.valueOf(305.0)),
        new Damage(Double.valueOf(270.0), Double.valueOf(315.0)),
        new Damage(Double.valueOf(285.0), Double.valueOf(330.0)),
        new Damage(Double.valueOf(300.0), Double.valueOf(345.0))
    );

    /**
     * The default attack speed of Staff
     */
    public static final AttackSpeed DEFAULT_ATTACK_SPEED = new AttackSpeed(Double.valueOf(1.4));

    /**
     * The default name of Staff
     */
    public static final String DEFAULT_WEAPON_TYPE_NAME = "Staff";

    /**
     * Classes that can equip a Staff
     */
    public static final List<String> acceptedClasses = Arrays.asList(
        Wizard.DEFAULT_HERO_CLASS_NAME
    );


    /**
     * Constructor
     * @param tierParam
     */
    public Staff(final int tierParam) {
        this.setArtifactType(Staff.DEFAULT_WEAPON_TYPE_NAME);
        this.setTier(tierParam);
        this.setDamage(Staff.damagePerTier.get(this.getTier()));
        this.setAttackSpeed(Staff.DEFAULT_ATTACK_SPEED);
    }
}
