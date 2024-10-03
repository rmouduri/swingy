package com.rmouduri.swingy.model.environment;

public class PlayerMapPosition {
    /**
     * Player's x position on the map
     */
    private int x;

    /**
     * Player's y position on the map
     */
    private int y;

    /**
     * Constructor
     * @param xParam
     * @param yParam
     */
    public PlayerMapPosition(final int xParam, final int yParam) {
        this.x = xParam;
        this.y = yParam;
    }

    /**
     * Get the X position
     * @return The X position
     */
    public int getX() { return this.x; }

    /**
     * Set the X position
     * @param xParam
     */
    public void setX(final int xParam) { this.x = xParam; }

    /**
     * Get the Y position
     * @return The Y position
     */
    public int getY() { return this.y; }

    /**
     * Set the Y position
     * @param yParam
     */
    public void setY(final int yParam) { this.y = yParam; }
}
