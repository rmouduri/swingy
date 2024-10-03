package com.rmouduri.swingy.model.game.battle;

import com.rmouduri.swingy.model.entity.AbstractEntity;

import java.util.ArrayList;

public class BattleSummary {
    /**
     * The list of AttackSummaries
     */
    private final ArrayList<AttackSummary> attackSummaries;

    /**
     * Winner of the battle
     */
    private AbstractEntity winner;

    /**
     * Loser of the battle
     */
    private AbstractEntity loser;

    /**
     * Constructor
     */
    public BattleSummary() {
        this.attackSummaries = new ArrayList<AttackSummary>();
    }

    /**
     * Add an attackSummary to the BattleSummary, needs to be called after the attack
     * @param attackSummaryParam
     */
    public void addAttackSummary(final AttackSummary attackSummaryParam) {
        this.attackSummaries.add(attackSummaryParam);
    }

    /**
     * Get the attack summaries list
     * @return The attack summaries list
     */
    public ArrayList<AttackSummary> getAttackSummaries() { return this.attackSummaries; }

    /**
     * Get the Battle Winner
     * @return The battle Winner
     */
    public AbstractEntity getWinner() { return this.winner; }

    /**
     * Set the Winner
     * @param winnerParam
     */
    protected void setWinner(final AbstractEntity winnerParam) { this.winner = winnerParam; }

    /**
     * Get the Battle Loser
     * @return The battle Loser
     */
    public AbstractEntity getLoser() { return this.loser; }

    /**
     * Set the Loser
     * @param loserParam
     */
    protected void setLoser(final AbstractEntity loserParam) { this.loser = loserParam; }
}
