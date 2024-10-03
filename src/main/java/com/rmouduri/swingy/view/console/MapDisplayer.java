package com.rmouduri.swingy.view.console;

import com.rmouduri.swingy.model.entity.enemy.*;
import com.rmouduri.swingy.model.environment.Map;
import com.rmouduri.swingy.model.environment.MapTile;

public class MapDisplayer implements Displayable<Map> {
    /**
     * Character that separates tiles
     */
    private static final String TILE_SEPARATOR = " ";

    /**
     * Map to display
     */
    private Map map;

    /**
     * Constructor
     * @param mapParam
     */
    public MapDisplayer(final Map mapParam) {
        this.setDisplayableItem(mapParam);
    }

    /**
     * Displays the map
     */
    @Override
    public void display() {
        StringBuilder mapDisplay = new StringBuilder();

        final int mapLineSize = this.map.getMapLineSize();

        mapDisplay.append("\u001B[0m");
        for (int tileIndex = 0; tileIndex < mapLineSize * mapLineSize; ++tileIndex) {
            mapDisplay.append(TILE_SEPARATOR);
            mapDisplay.append(MapTile.getTileType(this.map.getMapTiles().get(tileIndex)));

            if (tileIndex % mapLineSize == mapLineSize - 1) {
                mapDisplay.append(TILE_SEPARATOR + "\n");
            }
        }
        System.out.print(mapDisplay);
    }

    public Map getMap() {
        return this.map;
    }

    @Override
    public void setDisplayableItem(final Map map) { this.map = map; }
}