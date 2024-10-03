package com.rmouduri.swingy.model.entity;

import com.rmouduri.swingy.model.statistic.Attack;
import com.rmouduri.swingy.model.statistic.AttackSpeed;
import com.rmouduri.swingy.model.statistic.Damage;
import com.rmouduri.swingy.model.statistic.Defense;
import com.rmouduri.swingy.model.statistic.HitPoints;

public abstract class AbstractEntity {
    public static final String RESET_COLOR = "\u001B[0m";

    /**
     * The Entity's name
     */
    private String name;

    /**
     * The Entity's class
     */
    private String entityClass;

    /**
     * The Entity's level
     */
    private int level;

    /**
     * The damage
     */
    private Damage damage;

    /**
     * The baseAttack
     */
    private Attack baseAttack;

    /**
     * The Attack
     */
    private Attack attack;

    /**
     * The Attack Speed
     */
    private AttackSpeed attackSpeed;

    /**
     * The baseDefense
     */
    private Defense baseDefense;

    /**
     * The defense
     */
    private Defense defense;

    /**
     * The base hit points
     */
    private HitPoints baseHitPoints;

    /**
     * The maximum hit points
     */
    private HitPoints maxHitPoints;

    /**
     * The current hit points
     */
    private HitPoints currentHitPoints;

    /**
     * The entity's attack bar
     */
    private Double attackBar;

    /**
     * Get the Name
     * @return The name of the Entity
     */
    public String getName() { return this.name; }

    /**
     * Set the Name to @param NameParam
     * @param nameParam
     */
    public void setName(final String nameParam) { this.name = nameParam; }

    /**
     * Get the EntityClass
     * @return the EntityClass
     */
    public String getEntityClass() { return this.entityClass; }

    /**
     * Set the EntityClass to @param EntityClassParam
     * @param entityClassParam
     */
    public void setEntityClass(final String entityClassParam) { this.entityClass = entityClassParam; }

    /**
     * Get the Entity's level
     * @return The Entity's level
     */
    public int getLevel() { return this.level; }

    /**
     * Set the Entity's level to @param levelParam
     * @param levelParam
     */
    public void setLevel(final int levelParam) { this.level = levelParam; }

    /**
     * Get the damage
     */
    public Damage getDamage() { return this.damage; }

    /**
     * Get the baseAttack
     */
    public Attack getBaseAttack() { return this.baseAttack; }

    /**
     * Get the attack
     */
    public Attack getAttack() { return this.attack; }

    /**
     * Get the Attack
     * @return the Attack
     */
    public AttackSpeed getAttackSpeed() { return this.attackSpeed; }

    /**
     * Set the attackSpeed
     * @param attackSpeedParam
     */
    protected void setAttackSpeed(final AttackSpeed attackSpeedParam) { this.attackSpeed = attackSpeedParam; }

    /**
     * Get the baseDefense
     */
    public Defense getBaseDefense() { return this.baseDefense; }

    /**
     * Get the defense
     */
    public Defense getDefense() { return this.defense; }

    /**
     * Get the baseHitPoints
     */
    public HitPoints getBaseHitPoints() { return this.baseHitPoints; }

    /**
     * Get the maxHitPoints
     */
    public HitPoints getMaxHitPoints() { return this.maxHitPoints; }

    /**
     * Get the currentHitPoints
     */
    public HitPoints getCurrentHitPoints() { return this.currentHitPoints; }

    /**
     * Set the damage
     */
    protected void setDamage(final Damage DamageParam) { this.damage = DamageParam; }

    /**
     * Set the attack
     */
    protected void setBaseAttack(final Attack baseAttackParam) { this.baseAttack = baseAttackParam; }

    /**
     * Set the attack
     */
    protected void setAttack(final Attack attackParam) { this.attack = attackParam; }

    /**
     * Set the baseDefense
     */
    protected void setBaseDefense(final Defense baseDefenseParam) { this.baseDefense = baseDefenseParam; }

    /**
     * Set the defense
     */
    protected void setDefense(final Defense defenseParam) { this.defense = defenseParam; }

    /**
     * Set the baseHitPoints
     */
    protected void setBaseHitPoints(final HitPoints baseHitPointsParam) { this.baseHitPoints = baseHitPointsParam; }

    /**
     * Set the maxHitPoints
     */
    protected void setMaxHitPoints(final HitPoints maxHitPointsParam) { this.maxHitPoints = maxHitPointsParam; }

    /**
     * Set the currentHitPoints
     */
    public void setCurrentHitPoints(final HitPoints currentHitPointsParam) { this.currentHitPoints = currentHitPointsParam; }

    /**
     * Get the entity's attack bar
     * @return The entity's attack bar
     */
    public Double getAttackBar() { return this.attackBar; }

    /**
     * Set the entity's attack bar
     * @param attackBarParam
     */
    public void setAttackBar(final Double attackBarParam) { this.attackBar = attackBarParam; }

    /**
     * Returns the entity's living state
     * @return The entity's living state
     */
    public boolean isAlive() { return this.currentHitPoints.getRawStat() > 0; }

    public abstract String getColoredName();

    public abstract String getHtmlColor();
}
