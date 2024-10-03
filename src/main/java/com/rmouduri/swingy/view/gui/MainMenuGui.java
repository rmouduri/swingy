package com.rmouduri.swingy.view.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenuGui extends Container {
    private JButton selectButton;
    private JButton createButton;
    private JButton consoleModeButton;
    private JButton quitButton;

    /**
     * Constructor
     */
    public MainMenuGui() {
        super();
        this.setLayout(null);

        try {
            final BufferedImage myPicture = ImageIO.read(new File(System.getProperty("user.dir") +
                "/target/classes/data/SwingyLogo.png"));
            final JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setSize(myPicture.getWidth(), myPicture.getHeight());
            picLabel.setLocation((SwingyWindow.WIDTH / 2) - ((int) picLabel.getSize().getWidth() / 2), 50);
            this.add(picLabel);
        } catch (IOException e) {
            System.err.println("Swing Logo Error: " + e.getMessage());
        }

        this.initSelectButton();
        this.initCreateButton();
        this.initConsoleModeButton();
        this.initQuitButton();

        this.add(this.selectButton);
        this.add(this.createButton);
        this.add(this.consoleModeButton);
        this.add(this.quitButton);
    }

    /**
     * Initialize the selectButton
     */
    private void initSelectButton() {
        this.selectButton = new JButton("Select");

        this.selectButton.setFont(new Font("Arial", Font.PLAIN, 30));
        this.selectButton.setSize(180, 40);
        this.selectButton.setLocation(
                (SwingyWindow.WIDTH / 2) - ((int) this.selectButton.getSize().getWidth() / 2), 370);
    }

    /**
     * Initialize the createButton
     */
    private void initCreateButton() {
        this.createButton = new JButton("Create");

        this.createButton.setFont(new Font("Arial", Font.PLAIN, 30));
        this.createButton.setSize(180, 40);
        this.createButton.setLocation(
                (SwingyWindow.WIDTH / 2) - ((int) this.createButton.getSize().getWidth() / 2), 430);
    }

    /**
     * Initialize the consoleModeButton
     */
    private void initConsoleModeButton() {
        this.consoleModeButton = new JButton("Console Mode");

        this.consoleModeButton.setFont(new Font("Arial", Font.PLAIN, 30));
        this.consoleModeButton.setSize(250, 40);
        this.consoleModeButton.setLocation(
                (SwingyWindow.WIDTH / 2) - ((int) this.consoleModeButton.getSize().getWidth() / 2), 490);
    }

    /**
     * Initialize the quitButton
     */
    private void initQuitButton() {
        this.quitButton = new JButton("Quit");

        this.quitButton.setFont(new Font("Arial", Font.PLAIN, 30));
        this.quitButton.setSize(150, 40);
        this.quitButton.setLocation(
                (SwingyWindow.WIDTH / 2) - ((int) this.quitButton.getSize().getWidth() / 2), 550);
    }

    /**
     * Set the SelectButtonListener
     * @param actionListener
     */
    public void setSelectButtonListener(final ActionListener actionListener) {
        this.selectButton.addActionListener(actionListener);
    }

    /**
     * Set the CreateButtonListener
     * @param actionListener
     */
    public void setCreateButtonListener(final ActionListener actionListener) {
        this.createButton.addActionListener(actionListener);
    }

    /**
     * Set the SwitchToConsoleButtonListener
     * @param actionListener
     */
    public void setConsoleModeButtonListener(final ActionListener actionListener) {
        this.consoleModeButton.addActionListener(actionListener);
    }

    /**
     * Set the QuitButtonListener
     * @param actionListener
     */
    public void setQuitButtonListener(final ActionListener actionListener) {
        this.quitButton.addActionListener(actionListener);
    }
}
