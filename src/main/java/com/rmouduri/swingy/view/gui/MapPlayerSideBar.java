package com.rmouduri.swingy.view.gui;

import com.rmouduri.swingy.model.entity.hero.AbstractHero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MapPlayerSideBar extends JPanel {
    private JButton northButton;
    private JButton southButton;
    private JButton eastButton;
    private JButton westButton;

    private JButton consoleModeButton;
    private JButton quitButton;

    private HeroStatsSidebarDisplay heroStatsSidebarDisplay;

    /**
     * Constructor
     */
    public MapPlayerSideBar() {
        super();
        this.setLayout(null);
        this.setSize((int) (SwingyWindow.WIDTH * .25), SwingyWindow.HEIGHT);
        this.setLocation(SwingyWindow.WIDTH - this.getWidth(), 0);
        this.setBackground(new Color(230,230,230));

        this.initDirectionButtons();
        this.initConsoleModeButton();
        this.initQuitButton();

        this.heroStatsSidebarDisplay = new HeroStatsSidebarDisplay();
        this.add(this.heroStatsSidebarDisplay);

        this.add(this.northButton);
        this.add(this.southButton);
        this.add(this.eastButton);
        this.add(this.westButton);

        this.add(this.consoleModeButton);
        this.add(this.quitButton);
    }

    /**
     * Initializes the directionButtons
     */
    private void initDirectionButtons() {
        this.northButton = new JButton("North");
        this.southButton = new JButton("South");
        this.eastButton = new JButton("East");
        this.westButton = new JButton("West");

        this.northButton.setFont(new Font("Arial", Font.PLAIN, 20));
        this.southButton.setFont(new Font("Arial", Font.PLAIN, 20));
        this.eastButton.setFont(new Font("Arial", Font.PLAIN, 20));
        this.westButton.setFont(new Font("Arial", Font.PLAIN, 20));

        this.northButton.setSize(100, 30);
        this.southButton.setSize(100, 30);
        this.eastButton.setSize(100, 30);
        this.westButton.setSize(100, 30);

        this.northButton.setLocation((this.getWidth() / 2) - (this.northButton.getWidth() / 2), 475);
        this.southButton.setLocation(this.northButton.getX(), 565);
        this.westButton.setLocation(this.northButton.getX() - (int) (this.southButton.getWidth() * .75), 520);
        this.eastButton.setLocation(this.getWidth() - this.eastButton.getWidth() - this.westButton.getX(), 520);
    }

    /**
     * Initialize the consoleModeButton
     */
    private void initConsoleModeButton() {
        this.consoleModeButton = new JButton("Console Mode");

        this.consoleModeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        this.consoleModeButton.setSize(200, 30);
        this.consoleModeButton.setLocation(
                (this.getWidth() / 2) - ((int) this.consoleModeButton.getSize().getWidth() / 2), 50);
    }

    /**
     * Initialize the quitButton
     */
    private void initQuitButton() {
        this.quitButton = new JButton("Quit map");

        this.quitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        this.quitButton.setSize(105, 25);
        this.quitButton.setLocation(
                this.getWidth() - this.quitButton.getWidth() - 18,
                this.getHeight() - this.quitButton.getHeight() - 50);
    }

    /**
     * Create a new HeroStatsSidebarDisplay with the hero @param heroParam
     * @param heroParam
     */
    public void addHero(final AbstractHero heroParam) {
        this.heroStatsSidebarDisplay.addHero(heroParam);
    }

    /**
     * Update the displayed hero's stats
     */
    public void updateHeroStats() {
        this.heroStatsSidebarDisplay.updateLabels();
    }

    /**
     * Set the QuitButtonListener
     * @param actionListener
     */
    public void setQuitButtonListener(final ActionListener actionListener) {
        this.quitButton.addActionListener(actionListener);
    }

    /**
     * Set the ConsoleModeButtonListener
     * @param actionListener
     */
    public void setConsoleModeButtonListener(final ActionListener actionListener) {
        this.consoleModeButton.addActionListener(actionListener);
    }

    /**
     * Set the northButtonListener
     * @param actionListener
     */
    public void setNorthButtonListener(final ActionListener actionListener) {
        this.northButton.addActionListener(actionListener);
    }

    /**
     * Set the southButtonListener
     * @param actionListener
     */
    public void setSouthButtonListener(final ActionListener actionListener) {
        this.southButton.addActionListener(actionListener);
    }

    /**
     * Set the eastButtonListener
     * @param actionListener
     */
    public void setEastButtonListener(final ActionListener actionListener) {
        this.eastButton.addActionListener(actionListener);
    }

    /**
     * Set the westButtonListener
     * @param actionListener
     */
    public void setWestButtonListener(final ActionListener actionListener) {
        this.westButton.addActionListener(actionListener);
    }

    /**
     * Set the northButton enabled state
     * @param enableParam
     */
    public void setNorthButtonEnabled(final boolean enableParam) {
        this.northButton.setEnabled(enableParam);
    }

    /**
     * Set the southButton enabled state
     * @param enableParam
     */
    public void setSouthButtonEnabled(final boolean enableParam) {
        this.southButton.setEnabled(enableParam);
    }

    /**
     * Set the eastButton enabled state
     * @param enableParam
     */
    public void setEastButtonEnabled(final boolean enableParam) {
        this.eastButton.setEnabled(enableParam);
    }

    /**
     * Set the westButton enabled state
     * @param enableParam
     */
    public void setWestButtonEnabled(final boolean enableParam) {
        this.westButton.setEnabled(enableParam);
    }
}
