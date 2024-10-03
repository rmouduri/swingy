package com.rmouduri.swingy.model.statistic;

public abstract class Statistic<T extends Number> {
    /**
     * The raw stat
     */
    private T rawStat;

    /**
     * Constructor
     * @param rawStatParam
     */
    protected Statistic(T rawStatParam) {
        this.setRawStat(rawStatParam);
    }

    /**
     * Copy constructor
     * @param copy
     */
    protected Statistic(Statistic<T> copy) {
        this.setRawStat(copy.getRawStat());
    }

    /**
     * Get the raw stat
     * @return the raw stat
     */
    public T getRawStat() { return this.rawStat; }

    /**
     * Set the raw stat to rawStatParam
     * @param rawStatParam
     */
    public void setRawStat(T rawStatParam) { this.rawStat = rawStatParam; }
}