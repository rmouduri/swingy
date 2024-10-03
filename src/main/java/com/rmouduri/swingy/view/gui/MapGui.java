package com.rmouduri.swingy.view.gui;

import com.rmouduri.swingy.model.environment.Map;
import com.rmouduri.swingy.model.environment.MapTile;
import com.rmouduri.swingy.model.environment.PlayerMapPosition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapGui extends JPanel {
    private static final int tileSize = SwingyWindow.HEIGHT / 10;
    private static final int mapXCenter = (int) (((SwingyWindow.WIDTH * .75) / 2) - tileSize * .75);
    private static final int mapYCenter = (SwingyWindow.HEIGHT / 2) - tileSize;
    private List<MapTileGui> mapGuiTiles;
    private Map map;

    /**
     * Constructor
     */
    public MapGui() {}

    /**
     * Set a new map and initializes it
     * @param mapParam
     */
    public void setMap(final Map mapParam) {
        this.map = mapParam;

        this.clear();
        this.setLayout(new GridLayout(this.map.getMapLineSize(), this.map.getMapLineSize(), 1, 1));
        this.setSize((int) (this.map.getMapLineSize() * tileSize),
                (int) (this.map.getMapLineSize() * tileSize));
        this.initMapGuiTiles();
        this.centerMap(this.map.getPlayerPosition());
    }

    /**
     * Initializes the map gui tiles
     */
    private void initMapGuiTiles() {
        this.mapGuiTiles = new ArrayList<>(this.map.getMapLineSize() * this.map.getMapLineSize());

        for (final MapTile mapTile : this.map.getMapTiles()) {
            final MapTileGui mapTileGui = new MapTileGui(MapTile.getTileType(mapTile));

            this.mapGuiTiles.add(mapTileGui);
            this.add(mapTileGui);
        }
    }

    /**
     * Update the map gui
     */
    public void updateMap() {
        final PlayerMapPosition playerMapPosition = this.map.getPlayerPosition();
        int index = playerMapPosition.getY() * this.map.getMapLineSize() + playerMapPosition.getX();

        ((MapTileGui) this.getComponents()[index])
                .updateTile(MapTile.getTileType(this.map.getPlayerTile()));

        if (playerMapPosition.getX() > 0) {
            index = playerMapPosition.getY() * this.map.getMapLineSize() + playerMapPosition.getX() - 1;
            ((MapTileGui) this.getComponents()[index])
                    .updateTile(MapTile.getTileType(this.map.getMapTiles().get(index)));
        }

        if (playerMapPosition.getX() < (this.map.getMapLineSize() - 1)) {
            index = playerMapPosition.getY() * this.map.getMapLineSize() + playerMapPosition.getX() + 1;
            ((MapTileGui) this.getComponents()[index])
                    .updateTile(MapTile.getTileType(this.map.getMapTiles().get(index)));
        }

        if (playerMapPosition.getY() > 0) {
            index = (playerMapPosition.getY() - 1) * this.map.getMapLineSize() + playerMapPosition.getX();
            ((MapTileGui) this.getComponents()[index])
                    .updateTile(MapTile.getTileType(this.map.getMapTiles().get(index)));
        }

        if (playerMapPosition.getY() < (this.map.getMapLineSize() - 1)) {
            index = (playerMapPosition.getY() + 1) * this.map.getMapLineSize() + playerMapPosition.getX();
            ((MapTileGui) this.getComponents()[index])
                    .updateTile(MapTile.getTileType(this.map.getMapTiles().get(index)));
        }

        this.centerMap(playerMapPosition);
        this.revalidate();
        this.repaint();
    }

    /**
     * Center the map on screen
     * @param playerMapPosition
     */
    private void centerMap(final PlayerMapPosition playerMapPosition) {
        this.setLocation(mapXCenter - (playerMapPosition.getX() * tileSize) + playerMapPosition.getX(),
                mapYCenter - (playerMapPosition.getY() * tileSize) + playerMapPosition.getY());
    }

    /**
     * Clear the MapGui
     */
    private void clear() {
        for (Component c : this.getComponents()) {
            this.remove(c);
        }
    }
}