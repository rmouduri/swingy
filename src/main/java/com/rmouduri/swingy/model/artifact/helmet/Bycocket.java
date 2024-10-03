package com.rmouduri.swingy.model.artifact.helmet;

import com.rmouduri.swingy.model.statistic.HitPoints;

public class Bycocket extends Helmet {
    /**
     * The hit points stat per tier, i.e. rarity
     */
    public static final HitPoints[] HitPointsPerTier = {
        new HitPoints(Long.valueOf(150)),
        new HitPoints(Long.valueOf(170)),
        new HitPoints(Long.valueOf(190)),
        new HitPoints(Long.valueOf(220)),
        new HitPoints(Long.valueOf(250)),
        new HitPoints(Long.valueOf(300)),
        new HitPoints(Long.valueOf(350)),
        new HitPoints(Long.valueOf(450))
    };

    /**
     * The default name of Bycocket
     */
    public static final String DEFAULT_HELMET_TYPE_NAME = "Bycocket";


    /**
     * Constructor
     * @param tierParam
     */
    public Bycocket(final int tierParam) {
        this.setArtifactType(Bycocket.DEFAULT_HELMET_TYPE_NAME);
        this.setTier(tierParam);
        this.setHitPoints(Bycocket.HitPointsPerTier[tierParam]);
    }
}
