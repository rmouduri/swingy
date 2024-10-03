package com.rmouduri.swingy.model.environment;

import com.rmouduri.swingy.model.entity.enemy.AbstractEnemy;
import com.rmouduri.swingy.model.entity.hero.AbstractHero;
import com.rmouduri.swingy.model.statistic.HitPoints;

public class MapTile {
    /**
     * Character for undiscovered tiles
     */
    public static final String UNDISCOVERED_TILE = "\u001B[37m#\u001B[0m";

    /**
     * Character for empty tiles
     */
    public static final String EMPTY_TILE = "\u001B[37m.\u001B[0m";

    /**
     * Character for the player
     */
    public static final String PLAYER_TILE = "\u001B[44m\u001B[37mP\u001B[0m\u001B[0m";

    /**
     * Character for enemy tile
     */
    public static final String ENEMY_TILE = "\u001B[31mE\u001B[0m";

    /**
     * Character for BOSS tile
     */
    public static final String BOSS_TILE = "\u001B[43m\u001B[31mB\u001B[0m\u001B[0m";

    /**
     * Character for healing tile
     */
    public static final String HEALING_TILE = "\u001B[32mH\u001B[0m";

    /**
     * Character for error tile
     */
    public static final String ERROR_TILE = "?";

    /**
     * The enemy on the tile
     */
    private AbstractEnemy enemy = null;

    /**
     * If true, player's hit points are healed by 20% of max hit points
     */
    private boolean isHealingTile = false;

    /**
     * If the player can see the tile content, i.e. if the player is at least 2 tiles near it
     */
    private boolean isDiscovered = false;

    /**
     * If the player is on this tile
     */
    private boolean isPlayerHere = false;

    /**
     * Constructor for blank tile
     */
    public MapTile() {}

    /**
     * Constructor for enemy tile
     * @param enemyParam
     */
    public MapTile(final AbstractEnemy enemyParam) {
        this.setEnemy(enemyParam);
    }

    /**
     * Constructor for healing tile
     * @param isHealingTileParam
     */
    public MapTile(final boolean isHealingTileParam) {
        this.setHealingTile(isHealingTileParam);
    }

    /**
     * Consume the healing to the hero
     * @param hero
     */
    public long consumeHealing(final AbstractHero hero) {
        if (this.isHealingTile) {
            long healedHP = (long) (hero.getMaxHitPoints().getRawStat() * .5);

            if (hero.getCurrentHitPoints().getRawStat() + healedHP > hero.getMaxHitPoints().getRawStat()) {
                healedHP = hero.getMaxHitPoints().getRawStat() - hero.getCurrentHitPoints().getRawStat();
            }

            hero.setCurrentHitPoints(new HitPoints(hero.getCurrentHitPoints().getRawStat() + healedHP));

            this.setHealingTile(false);

            return healedHP;
        } else {
            return 0;
        }
    }
    /**
     * Get the enemy
     * @return The enemy
     */
    public final AbstractEnemy getEnemy() { return this.enemy; }

    /**
     * Set the enemy
     * @param enemyParam
     */
    protected void setEnemy(final AbstractEnemy enemyParam) { this.enemy = enemyParam; }

    /**
     * Get the isHealingTile state
     * @return The isHealingTile state
     */
    public final boolean isHealingTile() { return this.isHealingTile; }

    /**
     * Set the isHealingTile state
     * @param healingTileParam
     */
    private void setHealingTile(final boolean healingTileParam) { this.isHealingTile = healingTileParam; }

    /**
     * Get isDiscovered tile state
     * @return The isDiscovered tile state
     */
    public boolean isDiscovered() { return this.isDiscovered; }

    /**
     * Set isDiscovered tile state
     * @param isDiscoveredParam
     */
    protected void setDiscovered(final boolean isDiscoveredParam) { isDiscovered = isDiscoveredParam; }

    /**
     * Get isPlayerHere state
     * @return The isPlayerHere state
     */
    public boolean isPlayerHere() { return this.isPlayerHere; }

    /**
     * Set isPlayerHere state
     * @param playerHereParam
     */
    public void setPlayerHere(final boolean playerHereParam) { this.isPlayerHere = playerHereParam; }

    /**
     * Removes the enemy from the tile if it is not alive
     */
    public void updateEnemy() {
        if (!this.getEnemy().isAlive()) {
            this.enemy = null;
        }
    }

    /**
     * Get the type of tile of @param mapTile
     * @param mapTile
     * @return The mapTile type
     */
    public static String getTileType(final MapTile mapTile) {
        final AbstractEnemy enemy = mapTile.getEnemy();

        if (mapTile.isPlayerHere()) {
            return PLAYER_TILE;
        } else if (!mapTile.isDiscovered()) {
            return UNDISCOVERED_TILE;
        } else if (enemy != null) {
            if (enemy.getName().contains("Boss")) {
                return BOSS_TILE;
            } else {
                return ENEMY_TILE;
            }
        } else if (mapTile.isHealingTile()) {
            return HEALING_TILE;
        } else {
            if (mapTile.isDiscovered()) {
                return EMPTY_TILE;
            } else {
                return ERROR_TILE;
            }
        }
    }
}
