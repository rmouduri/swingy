package com.rmouduri.swingy.model.statistic;

public class Attack extends Statistic<Double> {
    /**
     * Constructor
     * @param attackParam
     */
    public Attack(final Double attackParam) {
        super(attackParam);
    }

    /**
     * Copy constructor
     * @param attackParam
     */
    public Attack(final Attack attackParam) {
        super(attackParam);
    }

    /**
     * Set the Attack
     * @param attackParam
     */
    protected void setAttack(final Attack attackParam) { this.setRawStat(attackParam.getRawStat()); }

    /**
     * Set the Attack
     * @param rawAttackParam
     */
    protected void setAttack(final Double rawAttackParam) { this.setRawStat(rawAttackParam); }
}
