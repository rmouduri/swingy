package com.rmouduri.swingy.model.artifact.armor;

import com.rmouduri.swingy.model.statistic.Defense;

/**
 * Robe
 */
public class Robe extends Armor {
/**
     * The defense stat per tier, i.e. rarity
     */
    public static final Defense[] DefensePerTier = {
        new Defense(Double.valueOf(8.0)),
        new Defense(Double.valueOf(9.0)),
        new Defense(Double.valueOf(11.0)),
        new Defense(Double.valueOf(13.0)),
        new Defense(Double.valueOf(15.0)),
        new Defense(Double.valueOf(17.0)),
        new Defense(Double.valueOf(19.0)),
        new Defense(Double.valueOf(23.0))
    };

    /**
     * The default name of Robe
     */
    public static final String DEFAULT_ARMOR_TYPE_NAME = "Robe";


    /**
     * Constructor
     * @param tierParam
     */
    public Robe(final int tierParam) {
        this.setArtifactType(Robe.DEFAULT_ARMOR_TYPE_NAME);
        this.setTier(tierParam);
        this.setDefense(Robe.DefensePerTier[tierParam]);
    }
}