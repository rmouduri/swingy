package com.rmouduri.swingy.model.artifact.helmet;

import com.rmouduri.swingy.model.artifact.AbstractArtifact;
import com.rmouduri.swingy.model.statistic.HitPoints;

public abstract class Helmet extends AbstractArtifact {
    /**
     * The hit points of the helmet
     */
    private HitPoints hitPoints;

    /**
     * Get the Helmet's hit points
     * @return The Helmet's hit points
     */
    public HitPoints getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Set the Helmet's hit points to @param hitPointsParam
     * @param hitPointsParam
     */
    protected void setHitPoints(final HitPoints hitPointsParam) {
        this.hitPoints = hitPointsParam;
    }

    @Override
    public String toString() {
        return String.format("T%d %s (HP: %d)", this.getTier(), this.getArtifactType(), this.getHitPoints());
    }
}
