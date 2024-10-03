package com.rmouduri.swingy.model.artifact.armor;

import com.rmouduri.swingy.model.artifact.AbstractArtifact;
import com.rmouduri.swingy.model.statistic.Defense;

public abstract class Armor extends AbstractArtifact {
    /**
     * The defense of the armor
     */
    private Defense defense;

    /**
     * Get the Armor's defense
     * @return The Armor's defense
     */
    public Defense getDefense() {
        return this.defense;
    }

    /**
     * Set the Armor's defense to @param defenseParam
     * @param defenseParam
     */
    protected void setDefense(Defense defenseParam) {
        this.defense = defenseParam;
    }

    @Override
    public String toString() {
        return String.format("T%d %s (HP: %d)", this.getTier(), this.getArtifactType(), this.getDefense());
    }
}
