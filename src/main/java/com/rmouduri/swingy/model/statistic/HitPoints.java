package com.rmouduri.swingy.model.statistic;

public class HitPoints extends Statistic<Long> {
    /**
     * Constructor
     * @param hitPointsParam
     */
    public HitPoints(final Long hitPointsParam) {
        super(hitPointsParam);
    }

    /**
     * Copy constructor
     * @param hitPointsParam
     */
    public HitPoints(final HitPoints hitPointsParam) {
        super(hitPointsParam);
    }

    /**
     * Set the HitPoints
     * @param hitPointsParam
     */
    protected void setHitPoints(final HitPoints hitPointsParam) { this.setRawStat(hitPointsParam.getRawStat()); }

    /**
     * Set the HitPoints
     * @param rawHitPointsParam
     */
    protected void setHitPoints(final Long rawHitPointsParam) { this.setRawStat(rawHitPointsParam); }
}
