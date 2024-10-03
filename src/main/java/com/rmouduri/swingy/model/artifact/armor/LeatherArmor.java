package com.rmouduri.swingy.model.artifact.armor;

import com.rmouduri.swingy.model.statistic.Defense;

/**
 * LeatherArmor
 */
public class LeatherArmor extends Armor {
/**
     * The defense stat per tier, i.e. rarity
     */
    public static final Defense[] DefensePerTier = {
        new Defense(Double.valueOf(10.0)),
        new Defense(Double.valueOf(11.0)),
        new Defense(Double.valueOf(13.0)),
        new Defense(Double.valueOf(15.0)),
        new Defense(Double.valueOf(17.0)),
        new Defense(Double.valueOf(19.0)),
        new Defense(Double.valueOf(21.0)),
        new Defense(Double.valueOf(25.0))
    };

    /**
     * The default name of LeatherArmor
     */
    public static final String DEFAULT_ARMOR_TYPE_NAME = "Leather Armor";


    /**
     * Constructor
     * @param tierParam
     */
    public LeatherArmor(final int tierParam) {
        this.setArtifactType(LeatherArmor.DEFAULT_ARMOR_TYPE_NAME);
        this.setTier(tierParam);
        this.setDefense(LeatherArmor.DefensePerTier[tierParam]);
    }
}