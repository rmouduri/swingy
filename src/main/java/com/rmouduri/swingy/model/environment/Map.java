package com.rmouduri.swingy.model.environment;

import com.rmouduri.swingy.model.entity.enemy.*;
import com.rmouduri.swingy.view.console.MapDisplayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Map {
    /**
     * The map
     */
    private ArrayList<MapTile> mapTiles;

    /**
     * The level of the map
     */
    private int level;

    /**
     * The size of 1 line of the map, total size = mapLineSize * mapLineSize
     */
    private int mapLineSize;

    /**
     * The MapWriter
     */
    private final MapDisplayer mapDisplayer;

    /**
     * The player position
     */
    private PlayerMapPosition playerMapPosition;

    /**
     * Constructor
     * @param levelParam
     */
    public Map(final int levelParam) {
        this.setLevel(levelParam);
        this.setMapLineSize((this.getLevel() - 1) * 5 + 10 - (this.getLevel() % 2 == 0 ? 0 : 1));
        this.movePlayerTo(new PlayerMapPosition(this.getMapLineSize() / 2, this.getMapLineSize() / 2));
        this.initMapTiles();
        this.generateAllMapTiles();
        this.mapDisplayer = new MapDisplayer(this);
    }

    public void display() {
        this.mapDisplayer.display();
    }

    /**
     * Initializes the map with the correct size
     */
    private void initMapTiles() {
        this.mapTiles = new ArrayList<MapTile>(this.mapLineSize * this.mapLineSize);
        for (int i = 0; i < this.mapLineSize * this.mapLineSize; ++i) {
            this.mapTiles.add(null);
        }
    }

    /**
     * Randomly generates a MapTile
     * @param randInt
     * @param randomizedBosses
     * @return the randomly generated MapTile
     */
    private MapTile generateSingleMapTile(final int randInt, final String[] randomizedBosses) {
        if (randInt < 15) {
            return new MapTile(
                EnemyFactory.generateEnemy(
                    randomizedBosses[randInt % 4], this.getLevel() + (randInt % 3), false));
        } else if (randInt >= 90) {
            return new MapTile(true);
        } else {
            return new MapTile();
        }
    }

    /**
     * Randomly generates Map Tiles, except for predefined tiles
     */
    private void generateAllMapTiles() {
        final String[] randomizedBosses = {
                ArcherSkeleton.DEFAULT_ENEMY_CLASS_NAME, DarkSoulsDog.DEFAULT_ENEMY_CLASS_NAME,
                Goblin.DEFAULT_ENEMY_CLASS_NAME, Zombie.DEFAULT_ENEMY_CLASS_NAME};

        final ArrayList<Integer> bossTilesIndexes = new ArrayList<Integer>(Arrays.asList(
                0, this.getMapLineSize() - 1, this.getMapLineSize() * this.getMapLineSize() - this.getMapLineSize(),
                this.getMapLineSize() * this.getMapLineSize() - 1));

        final Random random = new Random();

        final int playerPosition = (this.getPlayerPosition().getY() * this.getMapLineSize())
            + this.getPlayerPosition().getX();

        this.mapTiles.set(playerPosition, new MapTile());
        this.mapTiles.get(playerPosition).setPlayerHere(true);
        this.mapTiles.get(playerPosition).setDiscovered(true);
        for (int tileIndex : bossTilesIndexes) {
            this.mapTiles.set(tileIndex, new MapTile(
                EnemyFactory.generateEnemy(
                    randomizedBosses[random.nextInt(4)],
                    this.getLevel(), true)));
        }

        for (int tileIndex = 0; tileIndex < this.getMapTiles().size(); ++tileIndex) {
            if (tileIndex != playerPosition && !bossTilesIndexes.contains(tileIndex)) {
                this.mapTiles.set(tileIndex, this.generateSingleMapTile(random.nextInt(100), randomizedBosses));
            }
        }
    }

    /**
     * Get the current map
     * @return The map
     */
    public final ArrayList<MapTile> getMapTiles() { return this.mapTiles; }

    /**
     * Set the map
     * @param mapParam
     */
    private void setMapTiles(final ArrayList<MapTile> mapParam) { this.mapTiles = mapParam; }

    /**
     * Get the map's level
     * @return The map's level
     */
    public final int getLevel() { return this.level; }

    /**
     * Set the map level
     * @param levelParam
     */
    private void setLevel(final int levelParam) { this.level = levelParam; }

    /**
     * Get the size of a line of the map
     * @return The size of a line of the map
     */
    public final int getMapLineSize() { return this.mapLineSize; }

    /**
     * Set the size of a line of the map
     * @param mapLineSizeParam
     */
    private void setMapLineSize(final int mapLineSizeParam) { this.mapLineSize = mapLineSizeParam; }

    /**
     * Get the player position
     * @return The player position
     */
    public PlayerMapPosition getPlayerPosition() { return this.playerMapPosition; }

    /**
     * Set the player position
     * @param playerMapPositionParam
     */
    private void movePlayerTo(final PlayerMapPosition playerMapPositionParam) { this.playerMapPosition = playerMapPositionParam; }

    /**
     * Set the player position
     * @param x
     * @param y
     */
    public void movePlayerTo(final int x, final int y) {
        this.getMapTiles().get(this.getMapLineSize() * this.getPlayerPosition().getY() + this.getPlayerPosition().getX())
                .setPlayerHere(false);

        this.playerMapPosition.setX(x);
        this.playerMapPosition.setY(y);

        this.getMapTiles().get(this.getMapLineSize() * this.getPlayerPosition().getY() + this.getPlayerPosition().getX())
                .setPlayerHere(true);
        this.getMapTiles().get(this.getMapLineSize() * this.getPlayerPosition().getY() + this.getPlayerPosition().getX())
                .setDiscovered(true);
    }

    /**
     * Get the tile at the player's position
     * @return The tile at the player's position
     */
    public MapTile getPlayerTile() {
        return this.getMapTiles().get(this.getPlayerPosition().getY() * this.getMapLineSize()
                + this.getPlayerPosition().getX());
    }
}
