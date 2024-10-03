package com.rmouduri.swingy.model.artifact.armor;

import com.rmouduri.swingy.model.statistic.Defense;

/**
 * HeavyArmor
 */
public class HeavyArmor extends Armor {
/**
     * The defense stat per tier, i.e. rarity
     */
    public static final Defense[] DefensePerTier = {
        new Defense(Double.valueOf(15.0)),
        new Defense(Double.valueOf(16.0)),
        new Defense(Double.valueOf(18.0)),
        new Defense(Double.valueOf(21.0)),
        new Defense(Double.valueOf(23.0)),
        new Defense(Double.valueOf(25.0)),
        new Defense(Double.valueOf(28.0)),
        new Defense(Double.valueOf(33.0))
    };

    /**
     * The default name of HeavyArmor
     */
    public static final String DEFAULT_ARMOR_TYPE_NAME = "Heavy Armor";


    /**
     * Constructor
     * @param tierParam
     */
    public HeavyArmor(final int tierParam) {
        this.setArtifactType(HeavyArmor.DEFAULT_ARMOR_TYPE_NAME);
        this.setTier(tierParam);
        this.setDefense(HeavyArmor.DefensePerTier[tierParam]);
    }
}
