package com.rmouduri.swingy.model.artifact.helmet;

import com.rmouduri.swingy.model.statistic.HitPoints;

public class HeavyHelmet extends Helmet {
    /**
     * The hit points stat per tier, i.e. rarity
     */
    public static final HitPoints[] HitPointsPerTier = {
        new HitPoints(Long.valueOf(200)),
        new HitPoints(Long.valueOf(220)),
        new HitPoints(Long.valueOf(240)),
        new HitPoints(Long.valueOf(270)),
        new HitPoints(Long.valueOf(300)),
        new HitPoints(Long.valueOf(350)),
        new HitPoints(Long.valueOf(400)),
        new HitPoints(Long.valueOf(500))
    };

    /**
     * The default name of HeavyHelmet
     */
    public static final String DEFAULT_HELMET_TYPE_NAME = "Heavy Helmet";


    /**
     * Constructor
     * @param tierParam
     */
    public HeavyHelmet(final int tierParam) {
        this.setArtifactType(HeavyHelmet.DEFAULT_HELMET_TYPE_NAME);
        this.setTier(tierParam);
        this.setHitPoints(HeavyHelmet.HitPointsPerTier[tierParam]);
    }
}
