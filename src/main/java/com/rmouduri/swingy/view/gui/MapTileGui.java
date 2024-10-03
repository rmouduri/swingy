package com.rmouduri.swingy.view.gui;

import com.rmouduri.swingy.model.environment.MapTile;

import javax.swing.*;
import java.awt.*;

public class MapTileGui extends JLabel {
    /**
     * Path of undiscovered tiles image
     */
    private static final String UNDISCOVERED_TILE_PATH = "";

    /**
     * Path of empty tiles image
     */
    private static final String EMPTY_TILE_PATH = "";

    /**
     * Path of the player image
     */
    private static final String PLAYER_TILE_PATH = "";

    /**
     * Path of enemy tile image
     */
    private static final String ENEMY_TILE_PATH = "";

    /**
     * Path of BOSS tile image
     */
    private static final String BOSS_TILE_PATH = "";

    /**
     * Path of healing tile image
     */
    private static final String HEALING_TILE_PATH = "";

    /**
     * Path of error tile image
     */
    private static final String ERROR_TILE_PATH = "";

    public MapTileGui(final String tileType) {
        super();
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("Arial", Font.PLAIN, 22));

        this.updateTile(tileType);
    }

    public void updateTile(final String tileType) {
        if (MapTile.PLAYER_TILE.equals(tileType)) {
            this.setBackground(Color.BLUE);
            this.setText("<html><span style='color:white;'>:)</span></html>");
        } else if (MapTile.UNDISCOVERED_TILE.equals(tileType)) {
            this.setBackground(Color.GRAY);
            this.setText("?");
        } else if (MapTile.BOSS_TILE.equals(tileType)) {
            this.setBackground(Color.ORANGE);
            this.setText(">:(");
        } else if (MapTile.ENEMY_TILE.equals(tileType)) {
            this.setBackground(Color.RED);
            this.setText(">:(");
        } else if (MapTile.HEALING_TILE.equals(tileType)) {
            this.setBackground(Color.GREEN);
            this.setText("H");
        } else if (MapTile.EMPTY_TILE.equals(tileType)) {
            this.setBackground(Color.WHITE);
            this.setText("");
        } else if (MapTile.ERROR_TILE.equals(tileType)) {
            this.setText("ERROR");
        }
    }
}
