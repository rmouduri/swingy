package com.rmouduri.swingy.model.game.battle;

import com.rmouduri.swingy.model.entity.AbstractEntity;
import com.rmouduri.swingy.model.entity.enemy.AbstractEnemy;
import com.rmouduri.swingy.model.entity.hero.AbstractHero;
import com.rmouduri.swingy.model.game.Game;
import com.rmouduri.swingy.model.game.Startable;
import com.rmouduri.swingy.model.statistic.Damage;
import com.rmouduri.swingy.model.statistic.HitPoints;
import com.rmouduri.swingy.view.console.BattleDisplayer;

import java.util.Random;

public class Battle implements Startable {
    /**
     * The hero in battle
     */
    private final AbstractHero hero;

    /**
     * The enemy in battle
     */
    private final AbstractEnemy enemy;

    /**
     * The battle displayer
     */
    private final BattleDisplayer battleDisplayer;

    /**
     * The BattleSummary
     */
    private final BattleSummary battleSummary;

    /**
     * The game
     */
    private final Game game;

    /**
     * Constructor
     * @param heroParam
     * @param enemyParam
     */
    public Battle(final AbstractHero heroParam, final AbstractEnemy enemyParam, final Game gameParam) {
        this.game = gameParam;
        this.hero = heroParam;
        this.enemy = enemyParam;
        this.battleSummary = new BattleSummary();
        this.battleDisplayer = new BattleDisplayer(this);
    }

    /**
     * Play the full battle between the Hero and the Enemy
     */
    @Override
    public void start() {
        AbstractEntity attacker;
        AbstractEntity defender;
        Double heroAttackBar;
        Double enemyAttackBar;
        long damagesDealt;

        this.getHero().setAttackBar(0.0);
        this.getEnemy().setAttackBar(0.0);

        while (true) {
            this.getHero().setAttackBar(this.getHero().getAttackBar() + this.getHero().getAttackSpeed().getRawStat());
            this.getEnemy().setAttackBar(this.getEnemy().getAttackBar() + this.getEnemy().getAttackSpeed().getRawStat());

            heroAttackBar = this.getHero().getAttackBar();
            enemyAttackBar = this.getEnemy().getAttackBar();
            if (heroAttackBar >= 10.0 && enemyAttackBar >= 10.0) {
                if (heroAttackBar >= enemyAttackBar) {
                    attacker = this.getHero();
                    defender = this.getEnemy();
                } else {
                    attacker = this.getEnemy();
                    defender = this.getHero();
                }
            } else if (heroAttackBar >= 10.0) {
                attacker = this.getHero();
                defender = this.getEnemy();
            } else if (enemyAttackBar >= 10.0) {
                attacker = this.getEnemy();
                defender = this.getHero();
            } else {
                continue;
            }

            damagesDealt = this.attack(attacker, defender);
            this.getBattleSummary().addAttackSummary(new AttackSummary(attacker, defender, damagesDealt));
            attacker.setAttackBar(0.0);

            if (!this.getHero().isAlive()) {
                this.getBattleSummary().setWinner(this.getEnemy());
                this.getBattleSummary().setLoser(this.getHero());
                break;
            } else if (!this.getEnemy().isAlive()) {
                this.getBattleSummary().setWinner(this.getHero());
                this.getBattleSummary().setLoser(this.getEnemy());
                break;
            }
        }
    }

    /**
     * The @param attacker attacks the @param defender
     * @param attackerParam
     * @param defenderParam
     */
    private long attack(final AbstractEntity attackerParam, final AbstractEntity defenderParam) {
        final long damagesDealt = this.computeDamagesDealt(attackerParam, defenderParam);

        defenderParam.setCurrentHitPoints(
            new HitPoints(defenderParam.getCurrentHitPoints().getRawStat() - damagesDealt));

        return damagesDealt;
    }

    /**
     * Display the battle
     */
    public void display() {
        this.battleDisplayer.display();
    }

    /**
     * Get random number based on @param damage min and max atk
     * @param damage
     * @return The randomized damage value
     */
    private static long getRandDamage(final Damage damage) {
        final Random random = new Random();

        return random.nextLong(damage.getMinRawAttack().longValue(), damage.getMaxRawAttack().longValue());
    }

    /**
     * Computes damages dealt by attacker to defender based on attacker's attack and defender's defense
     * @param attacker
     * @param defender
     * @return The damages dealt by attacker to defender
     */
    private long computeDamagesDealt(final AbstractEntity attacker, final AbstractEntity defender) {
        long damagesDealt;
        double dmgReduction = defender.getDefense().getRawStat() * 10 - attacker.getAttack().getRawStat();

        if (dmgReduction > 50.0) {
            dmgReduction = 50.0;
        }
        damagesDealt = Double.valueOf(
            (attacker.getAttack().getRawStat() + Battle.getRandDamage(attacker.getDamage()))
                * ((100 - dmgReduction) / 100)).longValue();

        final long currDefenderHitPoints = defender.getCurrentHitPoints().getRawStat();
        if (damagesDealt > currDefenderHitPoints) {
            damagesDealt = currDefenderHitPoints;
        }
        return damagesDealt;
    }

    /**
     * Get the battle's Hero
     * @return The battle's Hero
     */
    public AbstractHero getHero() { return this.hero; }

    /**
     * Get the battle's Enemy
     * @return The battle's Enemy
     */
    public AbstractEnemy getEnemy() { return this.enemy; }

    /**
     * Get the BattleSummary
     * @return The BattleSummary
     */
    public BattleSummary getBattleSummary() { return this.battleSummary; }

    /**
     * Get the Game
     * @return The Game
     */
    public Game getGame() { return this.game; }
}
