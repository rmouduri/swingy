package com.rmouduri.swingy.model.artifact.weapon;

import java.util.Arrays;
import java.util.List;

import com.rmouduri.swingy.model.entity.hero.Warrior;
import com.rmouduri.swingy.model.statistic.AttackSpeed;
import com.rmouduri.swingy.model.statistic.Damage;

public class Sword extends Weapon {
    /**
     * The damage stat per tier, i.e. rarity
     */
    public static final List<Damage> damagePerTier = Arrays.asList(
        new Damage(Double.valueOf(240.0), Double.valueOf(300.0)),
        new Damage(Double.valueOf(285.0), Double.valueOf(345.0)),
        new Damage(Double.valueOf(295.0), Double.valueOf(355.0)),
        new Damage(Double.valueOf(305.0), Double.valueOf(365.0)),
        new Damage(Double.valueOf(315.0), Double.valueOf(375.0)),
        new Damage(Double.valueOf(325.0), Double.valueOf(385.0)),
        new Damage(Double.valueOf(340.0), Double.valueOf(400.0)),
        new Damage(Double.valueOf(355.0), Double.valueOf(415.0))
    );

    /**
     * The default attack speed of Sword
     */
    public static final AttackSpeed DEFAULT_ATTACK_SPEED = new AttackSpeed(Double.valueOf(1.0));

    /**
     * The default name of Sword
     */
    public static final String DEFAULT_WEAPON_TYPE_NAME = "Sword";

    /**
     * Classes that can equip a Sword
     */
    public static final List<String> acceptedClasses = Arrays.asList(
        Warrior.DEFAULT_HERO_CLASS_NAME
    );


    /**
     * Constructor
     * @param tierParam
     */
    public Sword(final int tierParam) {
        this.setArtifactType(Sword.DEFAULT_WEAPON_TYPE_NAME);
        this.setTier(tierParam);
        this.setDamage(Sword.damagePerTier.get(this.getTier()));
        this.setAttackSpeed(Sword.DEFAULT_ATTACK_SPEED);
    }
}
