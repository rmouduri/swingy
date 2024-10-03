package com.rmouduri.swingy.model.artifact.helmet;

import com.rmouduri.swingy.model.statistic.HitPoints;

public class MagicHat extends Helmet {
    /**
     * The hit points stat per tier, i.e. rarity
     */
    public static final HitPoints[] HitPointsPerTier = {
        new HitPoints(Long.valueOf(100)),
        new HitPoints(Long.valueOf(120)),
        new HitPoints(Long.valueOf(140)),
        new HitPoints(Long.valueOf(170)),
        new HitPoints(Long.valueOf(200)),
        new HitPoints(Long.valueOf(250)),
        new HitPoints(Long.valueOf(300)),
        new HitPoints(Long.valueOf(400))
    };

    /**
     * The default name of MagicHat
     */
    public static final String DEFAULT_HELMET_TYPE_NAME = "Magic Hat";


    /**
     * Constructor
     * @param tierParam
     */
    public MagicHat(final int tierParam) {
        this.setArtifactType(MagicHat.DEFAULT_HELMET_TYPE_NAME);
        this.setTier(tierParam);
        this.setHitPoints(MagicHat.HitPointsPerTier[tierParam]);
    }
}
