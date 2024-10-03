package com.rmouduri.swingy.view.gui;

import com.rmouduri.swingy.model.entity.hero.AbstractHero;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SwingyWindow extends JFrame {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    /**
     * The MainMenuGui
      */
    private final MainMenuGui mainMenuGui;

    /**
     * The CreateHeroGui
      */
    private final CreateHeroGui createHeroGui;

    /**
     * The SelectHeroGui
      */
    private final SelectHeroGui selectHeroGui;

    /**
     * The MapGui
      */
    private final MapPlayerGui mapPlayerGui;

    /**
     * Constructor
     */
    public SwingyWindow() {
        super("Swingy");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.mainMenuGui = new MainMenuGui();
        this.createHeroGui = new CreateHeroGui();
        this.selectHeroGui = new SelectHeroGui();
        this.mapPlayerGui = new MapPlayerGui();
    }

    /**
     * Display the MainMenuGui
     */
    public void displayMainMenuGui() {
        this.setContentPane(this.mainMenuGui);
        this.updateDisplay();
    }

    /**
     * Display the CreateHeroGui
     */
    public void displayCreateHeroGui() {
        this.createHeroGui.clearNameTextArea();
        this.setContentPane(this.createHeroGui);
        this.updateDisplay();
    }

    /**
     * Display the SelectHeroGui
     */
    public void displaySelectHeroGui(final List<AbstractHero> heroesList) {
        this.selectHeroGui.updateHeroesList(heroesList);
        this.setContentPane(this.selectHeroGui);
        this.updateDisplay();
    }

    /**
     * Display the MapGui
     */
    public void displayMapGui() {
        this.setContentPane(this.mapPlayerGui);
        this.updateDisplay();
    }

    /**
     * Update the display
     */
    private void updateDisplay() {
        this.revalidate();
        this.repaint();
    }

    /**
     * Get the mainMenuGui
     * @return The mainMenuGui
     */
    public MainMenuGui getMainMenuGui() { return this.mainMenuGui; }

    /**
     * Get the createHeroGui
     * @return The createHeroGui
     */
    public CreateHeroGui getCreateHeroGui() { return this.createHeroGui; }

    /**
     * Get the selectHeroGui
     * @return The selectHeroGui
     */
    public SelectHeroGui getSelectHeroGui() { return this.selectHeroGui; }

    /**
     * Get the mapGui
     * @return The mapGui
     */
    public MapPlayerGui getMapPlayerGui() { return this.mapPlayerGui; }
}
