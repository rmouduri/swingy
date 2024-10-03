package com.rmouduri.swingy.model.artifact.weapon;

import com.rmouduri.swingy.model.artifact.AbstractArtifact;
import com.rmouduri.swingy.model.statistic.AttackSpeed;
import com.rmouduri.swingy.model.statistic.Damage;

public abstract class Weapon extends AbstractArtifact {
    /**
     * The damage of the Weapon
     */
    private Damage damage;

    /**
     * The attack speed of the Weapon
     */
    private AttackSpeed attackSpeed;


    /**
     * Get the damage of the Weapon
     * @return the damage of the Weapon
     */
    public Damage getDamage() {
        return this.damage;
    }

    /**
     * Set the damage of the weapon to @param damageParam
     * @param damageParam
     */
    protected void setDamage(Damage damageParam) {
        this.damage = damageParam;
    }

    /**
     * Get the attack speed of the Weapon
     * @return the attack speed of the Weapon
     */
    public AttackSpeed getAttackSpeed() {
        return this.attackSpeed;
    }

    /**
     * Set the attack speed of the weapon to @param attackSpeedParam
     * @param attackSpeedParam
     */
    protected void setAttackSpeed(AttackSpeed attackSpeedParam) {
        this.attackSpeed = attackSpeedParam;
    }

    @Override
    public String toString() {
        return String.format("T%d %s (Dmg: %s, AS: %f, DPS: %f)",
            this.getTier(), this.getArtifactType(), this.getDamage().toString(),
            this.getAttackSpeed(),
            (this.getDamage().getMinRawAttack() + this.getDamage().getMaxRawAttack()) / 2 * this.getAttackSpeed().getRawStat());
    }
}
