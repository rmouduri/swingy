package com.rmouduri.swingy.model.game.battle;

import com.rmouduri.swingy.model.entity.AbstractEntity;
import com.rmouduri.swingy.model.statistic.HitPoints;

public class AttackSummary {
    /**
     * Current attacker
     */
    private final AbstractEntity attacker;

    /**
     * Current defender
     */
    private final AbstractEntity defender;

    /**
     * Defender Hit Points after the attack
     */
    private final HitPoints defenderHitPoints;

    /**
     * The damage dealt from attacker to defender
     */
    private final long damageDealt;

    /**
     * Constructor
     * @param attackerParam
     * @param defenderParam
     * @param damageDealtParam
     */
    protected AttackSummary(final AbstractEntity attackerParam, final AbstractEntity defenderParam,
            final long damageDealtParam) {
        this.attacker = attackerParam;
        this.defender = defenderParam;
        this.defenderHitPoints = new HitPoints(this.defender.getCurrentHitPoints());
        this.damageDealt = damageDealtParam;
    }

    /**
     * Get the attacker
     * @return The attacker
     */
    public AbstractEntity getAttacker() { return this.attacker; }

    /**
     * Get the defender
     * @return The defender
     */
    public AbstractEntity getDefender() { return this.defender; }

    /**
     * Get the damageDealt
     * @return The damageDealt
     */
    public long getDamageDealt() { return this.damageDealt; }
}
