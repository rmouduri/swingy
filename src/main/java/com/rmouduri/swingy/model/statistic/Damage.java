package com.rmouduri.swingy.model.statistic;

public class Damage {
    /**
     * The minimum attack power of the range
     */
    private Attack minAttack;

    /**
     * The maximum attack power of the range
     */
    private Attack maxAttack;


    /**
     * Constructor
     * @param minAttackParam
     * @param maxAttackParam
     */
    public Damage(final Attack minAttackParam, final Attack maxAttackParam) {
        this.minAttack = minAttackParam;
        this.maxAttack = maxAttackParam;
    }

    /**
     * Constructor
     * @param minAttackParam
     * @param maxAttackParam
     */
    public Damage(final Double minAttackParam, final Double maxAttackParam) {
        this.minAttack = new Attack(minAttackParam);
        this.maxAttack = new Attack(maxAttackParam);
    }

    /**
     * Get the minimum attack power of the range
     * @return the minAttack
     */
    public Attack getMinAttack() { return this.minAttack; }
    
    /**
     * Get the maximum attack power of the range
     * @return the maxAttack
     */
    public Attack getMaxAttack() { return this.maxAttack; }

    /**
     * Get the minimum raw attack power of the range
     * @return the minAttack
     */
    public Double getMinRawAttack() { return this.minAttack.getRawStat(); }

    /**
     * Get the maximum raw attack power of the range
     * @return the maxAttack
     */
    public Double getMaxRawAttack() { return this.maxAttack.getRawStat(); }

    @Override
    public String toString() { 
        return String.format("%f-%f", this.getMinAttack().getRawStat(), this.getMaxAttack().getRawStat());
    }
}
