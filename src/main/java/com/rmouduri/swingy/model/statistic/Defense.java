package com.rmouduri.swingy.model.statistic;

public class Defense extends Statistic<Double> {
    /**
     * Constructor
     * @param defenseParam
     */
    public Defense(final Double defenseParam) {
        super(defenseParam);
    }

    /**
     * Copy constructor
     * @param defenseParam
     */
    public Defense(final Defense defenseParam) {
        super(defenseParam);
    }

    /**
     * Set the Defense
     * @param defenseParam
     */
    protected void setDefense(final Defense defenseParam) { this.setRawStat(defenseParam.getRawStat()); }

    /**
     * Set the Defense
     * @param rawDefenseParam
     */
    protected void setDefense(final Double rawDefenseParam) { this.setRawStat(rawDefenseParam); }
}
