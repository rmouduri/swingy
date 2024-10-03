package com.rmouduri.swingy.model.entity.enemy;

public class EnemyFactory {
    /**
     * Generate a fresh enemy of class @param enemyClass
     * @param enemyClass
     * @param level
     * @param isBoss
     * @return The fresh enemy
     */
    public static AbstractEnemy generateEnemy(final String enemyClass, final int level, final boolean isBoss) {
        if (ArcherSkeleton.DEFAULT_ENEMY_CLASS_NAME.equals(enemyClass)) {
            return new ArcherSkeleton(level, isBoss);
        } else if (DarkSoulsDog.DEFAULT_ENEMY_CLASS_NAME.equals(enemyClass)) {
            return new DarkSoulsDog(level, isBoss);
        } else if (Goblin.DEFAULT_ENEMY_CLASS_NAME.equals(enemyClass)) {
            return new Goblin(level, isBoss);
        } else if (Zombie.DEFAULT_ENEMY_CLASS_NAME.equals(enemyClass)) {
            return new Zombie(level, isBoss);
        }

        return null;
    }
}
