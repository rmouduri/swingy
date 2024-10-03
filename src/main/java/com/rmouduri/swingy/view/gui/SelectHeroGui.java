package com.rmouduri.swingy.view.gui;

import com.rmouduri.swingy.model.entity.hero.AbstractHero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class SelectHeroGui extends Container {
    private JLabel menuTitle;
    private JList<String> heroesList;
    private JScrollPane scrollPane;
    private JButton selectButton;
    private JButton consoleModeButton;
    private JButton backButton;

    public SelectHeroGui() {
        super();
        this.setLayout(null);

        this.initTitle();

        this.initSelectButton();
        this.initConsoleModeButton();
        this.initQuitButton();

        this.initHeroesList();
        this.initHeroesListScrollPane();

        this.add(this.menuTitle);
        this.add(this.scrollPane);
        this.add(this.selectButton);
        this.add(this.consoleModeButton);
        this.add(this.backButton);
    }

    /**
     * Initializes the menuTitle
     */
    private void initTitle() {
        final JLabel jLabel = new JLabel();

        jLabel.setSize(400, 100);
        jLabel.setLocation((SwingyWindow.WIDTH / 2) - (jLabel.getWidth() / 2), 30);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 55));
        jLabel.setText("Select a hero");

        this.menuTitle = jLabel;
    }

    /**
     * Initializes the heroesList
     */
    private void initHeroesList() {
        this.heroesList = new JList<>();

        this.heroesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.heroesList.setFont(new Font("Arial", Font.PLAIN, 18));
        this.heroesList.setLayoutOrientation(JList.VERTICAL);

        this.heroesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                this.selectButton.setEnabled(this.getSelectedHeroValue() != null);
            }
        });
    }

    /**
     * Get the selected hero raw html string
     * @return The selected hero raw html string
     */
    public String getSelectedHeroValue() { return this.heroesList.getSelectedValue(); }

    /**
     * Initializes the HeroesListScrollPane
     */
    private void initHeroesListScrollPane() {
        final JScrollPane jScrollPane;

        jScrollPane = new JScrollPane();
        jScrollPane.setSize(1050, 400);
        jScrollPane.setLocation(
                (SwingyWindow.WIDTH / 2) - (jScrollPane.getWidth() / 2), 150);
        jScrollPane.setViewportView(this.heroesList);

        this.scrollPane = jScrollPane;
    }

    /**
     * Initialize the selectButton
     */
    private void initSelectButton() {
        final JButton button = new JButton("Select");

        button.setEnabled(false);
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setSize(180, 40);
        button.setLocation(
                SwingyWindow.WIDTH - button.getWidth() - 100,
                SwingyWindow.HEIGHT - button.getHeight() - 100);

        this.selectButton = button;
    }

    /**
     * Initialize the consoleModeButton
     */
    private void initConsoleModeButton() {
        final JButton button = new JButton("Console Mode");

        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setSize(250, 40);
        button.setLocation(
                (SwingyWindow.WIDTH / 2) - ((int) button.getSize().getWidth() / 2),
                SwingyWindow.HEIGHT - button.getHeight() - 100);

        this.consoleModeButton = button;
    }

    /**
     * Initialize the quitButton
     */
    private void initQuitButton() {
        final JButton button = new JButton("Back");

        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setSize(150, 40);
        button.setLocation(100, SwingyWindow.HEIGHT - button.getHeight() - 100);

        this.backButton = button;
    }

    /**
     * Set the SelectButtonListener
     * @param actionListener
     */
    public void setSelectButtonListener(final ActionListener actionListener) {
        this.selectButton.addActionListener(actionListener);
    }

    /**
     * Set the ConsoleModeButton
     * @param actionListener
     */
    public void setConsoleModeButtonListener(final ActionListener actionListener) {
        this.consoleModeButton.addActionListener(actionListener);
    }

    /**
     * Set the QuitButtonListener
     * @param actionListener
     */
    public void setBackButtonListener(final ActionListener actionListener) {
        this.backButton.addActionListener(actionListener);
    }

    protected void updateHeroesList(final List<AbstractHero> heroesList) {
        final Vector<String> heroesNamesList = new Vector<>(heroesList.size());

        for (final AbstractHero hero : heroesList) {
            heroesNamesList.add(hero.toHtmlString());
        }

        this.heroesList.setListData(heroesNamesList);
    }
}
