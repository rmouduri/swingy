package com.rmouduri.swingy.view.gui;

import javax.swing.*;

public class MapPlayerGui extends JLayeredPane {
    private final MapPlayerSideBar sideBar;
    private final MapGui mapGui;

    /**
     * Constructor
     */
    public MapPlayerGui() {
        super();
        this.setLayout(null);

        this.mapGui = new MapGui();
        this.sideBar = new MapPlayerSideBar();

        this.add(mapGui, 1, 0);
        this.add(sideBar, 2, 0);
    }

    /**
     * Get the MapPlayerSideBar
     * @return The MapPlayerSideBar
     */
    public MapPlayerSideBar getSideBar() { return this.sideBar; }

    /**
     * Get the MapGui
     * @return The MapGui
     */
    public MapGui getMapGui() { return this.mapGui; }


}