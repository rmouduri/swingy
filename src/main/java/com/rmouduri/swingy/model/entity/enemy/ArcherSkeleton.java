package com.rmouduri.swingy.model.entity.enemy;

import com.rmouduri.swingy.model.statistic.*;

public class ArcherSkeleton extends AbstractEnemy {
    /**
     * The class name
     */
    public static final String DEFAULT_ENEMY_CLASS_NAME = "Archer Skeleton";

    /**
     * The default Archer Skeleton's attack at level 1
     */
    public static final Attack DEFAULT_ENEMY_CLASS_ATTACK = new Attack(Double.valueOf(40.0));

    /**
     * The default Archer Skeleton's damages at level 1
     */
    public static final Damage DEFAULT_ENEMY_CLASS_DAMAGE = new Damage(
            new Attack(Double.valueOf(90)), new Attack(Double.valueOf(140)));

    /**
     * The default Archer Skeleton's hit points at level 1
     */
    public static final HitPoints DEFAULT_ENEMY_CLASS_HIT_POINTS = new HitPoints(Long.valueOf(350));

    /**
     * The default Archer Skeleton's defense at level 1
     */
    public static final Defense DEFAULT_ENEMY_CLASS_DEFENSE = new Defense(Double.valueOf(17));

    /**
     * The default Archer Skeleton's defense at level 1
     */
    public static final AttackSpeed DEFAULT_ENEMY_CLASS_ATTACK_SPEED = new AttackSpeed(Double.valueOf(1.2));

    /**
     * Constructor
     * @param level Enemy's level
     * @param isBoss Enemy's base stats are multiplied by 1.67 if boss
     */
    protected ArcherSkeleton(final int level, final boolean isBoss) {
        super(DEFAULT_ENEMY_CLASS_NAME + (isBoss ? " Boss" : ""), DEFAULT_ENEMY_CLASS_NAME, level,
                new Attack(DEFAULT_ENEMY_CLASS_ATTACK.getRawStat() * (isBoss ? 1.67 : 1)),
                new HitPoints((long) (DEFAULT_ENEMY_CLASS_HIT_POINTS.getRawStat() * (isBoss ? 1.67 : 1))),
                new Defense(DEFAULT_ENEMY_CLASS_DEFENSE.getRawStat() * (isBoss ? 1.67 : 1)),
                new AttackSpeed(DEFAULT_ENEMY_CLASS_ATTACK_SPEED.getRawStat() * (isBoss ? 1.67 : 1)),
                DEFAULT_ENEMY_CLASS_DAMAGE);
    }
}
