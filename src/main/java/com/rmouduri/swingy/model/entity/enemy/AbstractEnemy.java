package com.rmouduri.swingy.model.entity.enemy;

import com.rmouduri.swingy.model.entity.AbstractEntity;
import com.rmouduri.swingy.model.statistic.*;

/**
 * AbstractEnemy
 */
public abstract class AbstractEnemy extends AbstractEntity {
    public static final String NAME_COLOR = "\u001B[31m";

    public static final String HTML_COLOR = "red";

    /**
     * Constructor for enemy
     *
     * @param nameParam
     * @param enemyClassParam
     * @param levelParam
     * @param baseAttackParam
     * @param baseHitPointsParam
     * @param baseDefenseParam
     */
    protected AbstractEnemy(final String nameParam, final String enemyClassParam, final int levelParam,
                            final Attack baseAttackParam, final HitPoints baseHitPointsParam, final Defense baseDefenseParam,
                            final AttackSpeed attackSpeedParam, final Damage damageParam) {
        this.setName(nameParam);
        this.setEntityClass(enemyClassParam);

        this.setBaseAttack(baseAttackParam);
        this.setAttack(this.getBaseAttack());

        this.setDamage(damageParam);

        this.setBaseHitPoints(baseHitPointsParam);
        this.setMaxHitPoints(this.getBaseHitPoints());
        this.setCurrentHitPoints(this.getBaseHitPoints());

        this.setBaseDefense(baseDefenseParam);
        this.setDefense(this.getBaseDefense());

        this.setAttackSpeed(attackSpeedParam);

        this.setLevel(levelParam);

        this.computeStatsByLevel();
    }

    /**
     * Sets up the enemy's stats based on level
     */
    private void computeStatsByLevel() {
        this.setAttack(new Attack(this.getAttack().getRawStat() +
            ((this.getBaseHitPoints().getRawStat()) / 10 * (this.getLevel() - 1))));

        this.setMaxHitPoints(new HitPoints(this.getMaxHitPoints().getRawStat() +
            ((this.getBaseHitPoints().getRawStat()) / 10 * (this.getLevel() - 1))));

        this.setCurrentHitPoints(new HitPoints(this.getCurrentHitPoints().getRawStat() +
            ((this.getBaseHitPoints().getRawStat()) / 10 * (this.getLevel() - 1))));

        this.setDefense(new Defense(this.getDefense().getRawStat() +
            ((this.getBaseDefense().getRawStat()) / 10 * (this.getLevel() - 1))));
    }

    /**
     * Get a colored name
     * @return The colored name
     */
    @Override
    public String getColoredName() {
        return NAME_COLOR + this.getName() + RESET_COLOR;
    }

    /**
     * Get the HTML color
     * @return The HTML color
     */
    @Override
    public String getHtmlColor() {
        return HTML_COLOR;
    }
}
