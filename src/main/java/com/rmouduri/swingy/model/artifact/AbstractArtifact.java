package com.rmouduri.swingy.model.artifact;

import com.rmouduri.swingy.model.entity.hero.AbstractHero;

public abstract class AbstractArtifact {
    /**
     * The owner of the artifact
     */
    private AbstractHero owner;

    /**
     * The type of artifact
     */
    private String artifactType;

    /**
     * The tier of the weapon, i.e. the rarity
     */
    private int tier;


    /**
     * Constructor
     */
    public AbstractArtifact() {}

    /**
     * Get artifactType
     * @return artifactType
     */
    public String getArtifactType() { return this.artifactType; }

    /**
     * Set artifactType
     * @param artifactType
     */
    public void setArtifactType(final String artifactTypeParam) { this.artifactType = artifactTypeParam; }

    /**
     * Get owner
     * @return owner
     */
    public AbstractHero getOwner() { return this.owner; }

    /**
     * Set Owner
     * @param newOwner
     */
    public void setOwner(final AbstractHero newOwner) {
        this.owner = newOwner;
    }

    /**
     * Unset Owner
     */
    public void unsetOwner() {
        this.owner = null;
    }

    /**
     * Get the tier of the Weapon
     * @return the tier of the Weapon
     */
    public int getTier() {
        return this.tier;
    }

    /**
     * Set the tier of the Weapon to @param tierParam
     * @param tierParam
     */
    public void setTier(int tierParam) {
        this.tier = tierParam;
    }
}
