package com.rmouduri.swingy.model.statistic;

public class AttackSpeed extends Statistic<Double> {
    /**
     * Constructor
     * @param attackSpeedParam
     */
    public AttackSpeed(final Double attackSpeedParam) {
        super(attackSpeedParam);
    }

    /**
     * Copy constructor
     * @param attackSpeedParam
     */
    public AttackSpeed(final AttackSpeed attackSpeedParam) {
        super(attackSpeedParam);
    }

    /**
     * Set the AttackSpeed
     * @param attackSpeedParam
     */
    protected void setAttackSpeed(final AttackSpeed attackSpeedParam) { this.setRawStat(attackSpeedParam.getRawStat()); }

    /**
     * Set the AttackSpeed
     * @param rawAttackSpeedParam
     */
    protected void setAttackSpeed(final Double rawAttackSpeedParam) { this.setRawStat(rawAttackSpeedParam); }
}
