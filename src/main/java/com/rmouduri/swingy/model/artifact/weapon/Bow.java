package com.rmouduri.swingy.model.artifact.weapon;

import java.util.Arrays;
import java.util.List;

import com.rmouduri.swingy.model.entity.hero.Archer;
import com.rmouduri.swingy.model.statistic.AttackSpeed;
import com.rmouduri.swingy.model.statistic.Damage;

public class Bow extends Weapon {
    /**
     * The damage stat per tier, i.e. rarity
     */
    public static final List<Damage> damagePerTier = Arrays.asList(
        new Damage(Double.valueOf(105.0), Double.valueOf(135.0)),
        new Damage(Double.valueOf(125.0), Double.valueOf(155.0)),
        new Damage(Double.valueOf(135.0), Double.valueOf(165.0)),
        new Damage(Double.valueOf(155.0), Double.valueOf(185.0)),
        new Damage(Double.valueOf(165.0), Double.valueOf(195.0)),
        new Damage(Double.valueOf(175.0), Double.valueOf(205.0)),
        new Damage(Double.valueOf(205.0), Double.valueOf(235.0)),
        new Damage(Double.valueOf(215.0), Double.valueOf(245.0))
    );

    /**
     * The default attack speed of Bow
     */
    public static final AttackSpeed DEFAULT_ATTACK_SPEED = new AttackSpeed(Double.valueOf(2.0));

    /**
     * The default name of Bow
     */
    public static final String DEFAULT_WEAPON_TYPE_NAME = "Bow";

    /**
     * Classes that can equip a Bow
     */
    public static final List<String> acceptedClasses = Arrays.asList(
        Archer.DEFAULT_HERO_CLASS_NAME
    );


    /**
     * Constructor
     * @param tierParam
     */
    public Bow(final int tierParam) {
        this.setArtifactType(Bow.DEFAULT_WEAPON_TYPE_NAME);
        this.setTier(tierParam);
        this.setDamage(Bow.damagePerTier.get(this.getTier()));
        this.setAttackSpeed(Bow.DEFAULT_ATTACK_SPEED);
    }
}
