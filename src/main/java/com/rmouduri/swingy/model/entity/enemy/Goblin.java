package com.rmouduri.swingy.model.entity.enemy;

import com.rmouduri.swingy.model.statistic.*;

public class Goblin extends AbstractEnemy {
    /**
     * The class name
     */
    public static final String DEFAULT_ENEMY_CLASS_NAME = "Goblin";

    /**
     * The default goblin's attack at level 1
     */
    public static final Attack DEFAULT_ENEMY_CLASS_ATTACK = new Attack(Double.valueOf(30.0));

    /**
     * The default goblin's damages at level 1
     */
    public static final Damage DEFAULT_ENEMY_CLASS_DAMAGE = new Damage(
            new Attack(Double.valueOf(115)), new Attack(Double.valueOf(165)));

    /**
     * The default goblin's hit points at level 1
     */
    public static final HitPoints DEFAULT_ENEMY_CLASS_HIT_POINTS = new HitPoints(Long.valueOf(400));

    /**
     * The default goblin's defense at level 1
     */
    public static final Defense DEFAULT_ENEMY_CLASS_DEFENSE = new Defense(Double.valueOf(13));

    /**
     * The default goblin's defense at level 1
     */
    public static final AttackSpeed DEFAULT_ENEMY_CLASS_ATTACK_SPEED = new AttackSpeed(Double.valueOf(1.4));

    /**
     * Constructor
     * @param level Enemy's level
     * @param isBoss Enemy's base stats are multiplied by 1.67 if boss
     */
    protected Goblin(final int level, final boolean isBoss) {
        super(DEFAULT_ENEMY_CLASS_NAME + (isBoss ? " Boss" : ""), DEFAULT_ENEMY_CLASS_NAME, level,
                new Attack(DEFAULT_ENEMY_CLASS_ATTACK.getRawStat() * (isBoss ? 1.67 : 1)),
                new HitPoints((long) (DEFAULT_ENEMY_CLASS_HIT_POINTS.getRawStat() * (isBoss ? 1.67 : 1))),
                new Defense(DEFAULT_ENEMY_CLASS_DEFENSE.getRawStat() * (isBoss ? 1.67 : 1)),
                new AttackSpeed(DEFAULT_ENEMY_CLASS_ATTACK_SPEED.getRawStat() * (isBoss ? 1.67 : 1)),
                DEFAULT_ENEMY_CLASS_DAMAGE);
    }
}
