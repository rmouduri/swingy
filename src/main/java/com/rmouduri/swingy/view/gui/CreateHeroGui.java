package com.rmouduri.swingy.view.gui;

import com.rmouduri.swingy.model.artifact.weapon.Bow;
import com.rmouduri.swingy.model.artifact.weapon.Staff;
import com.rmouduri.swingy.model.artifact.weapon.Sword;
import com.rmouduri.swingy.model.entity.hero.Archer;
import com.rmouduri.swingy.model.entity.hero.Warrior;
import com.rmouduri.swingy.model.entity.hero.Wizard;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class CreateHeroGui extends Container {
    private JLabel menuTitle;
    private JLabel nameTitle;
    private JLabel nameError;

    private JButton createButton;
    private JButton consoleModeButton;
    private JButton backButton;

    private ButtonGroup buttonGroup;
    private JRadioButton archerRadio;
    private JRadioButton warriorRadio;
    private JRadioButton wizardRadio;

    private HeroClassStatsDisplay archerStats;
    private HeroClassStatsDisplay warriorStats;
    private HeroClassStatsDisplay wizardStats;

    private JTextArea nameTextArea;

    public CreateHeroGui() {
        super();
        this.setLayout(null);

        this.initMenuTitle();
        this.initNameTitle();
        this.initNameError();

        this.initRadioButtons();
        this.initClassStats();

        this.initCreateButton();
        this.initConsoleModeButton();
        this.initBackButton();
        this.initNameTextArea();

        this.add(this.menuTitle);
        this.add(this.nameTitle);
        this.add(this.nameError);

        this.add(this.archerStats);
        this.add(warriorStats);
        this.add(wizardStats);

        this.add(this.archerRadio);
        this.add(this.warriorRadio);
        this.add(this.wizardRadio);

        this.add(this.createButton);
        this.add(this.consoleModeButton);
        this.add(this.backButton);

        this.add(this.nameTextArea);
    }

    /**
     * Initializes the menuTitle
     */
    private void initMenuTitle() {
        final JLabel jLabel = new JLabel();

        jLabel.setSize(400, 100);
        jLabel.setLocation((SwingyWindow.WIDTH / 2) - (jLabel.getWidth() / 2), 30);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 55));
        jLabel.setText("Create a hero");

        this.menuTitle = jLabel;
    }

    /**
     * Initialize the createButton
     */
    private void initCreateButton() {
        final JButton button = new JButton("Create");

        button.setEnabled(false);
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setSize(180, 40);
        button.setLocation(
                SwingyWindow.WIDTH - button.getWidth() - 100,
                SwingyWindow.HEIGHT - button.getHeight() - 100);

        this.createButton = button;
    }

    /**
     * Initialize the consoleModeButton
     */
    private void initConsoleModeButton() {
        final JButton button = new JButton("Console Mode");

        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setSize(250, 40);
        button.setLocation(
                (SwingyWindow.WIDTH / 2) - ((int) button.getWidth() / 2),
                SwingyWindow.HEIGHT - button.getHeight() - 100);

        this.consoleModeButton = button;
    }

    /**
     * Initialize the backButton
     */
    private void initBackButton() {
        final JButton button = new JButton("Back");

        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.setSize(150, 40);
        button.setLocation(100, SwingyWindow.HEIGHT - button.getHeight() - 100);

        this.backButton = button;
    }

    /**
     * Initialize the nameTextArea
     */
    private void initNameTextArea() {
        final JTextArea textArea = new JTextArea();

        textArea.setFont(new Font("Arial", Font.PLAIN, 25));
        textArea.setColumns(14);
        textArea.setSize(330, 40);
        textArea.setLocation(
                (SwingyWindow.WIDTH / 2) - ((int) textArea.getWidth() / 2),
                SwingyWindow.HEIGHT - textArea.getHeight() - 200);

        this.nameTextArea = textArea;
    }

    /**
     * Initializes the nameTitle
     */
    private void initNameTitle() {
        final JLabel jLabel = new JLabel();

        jLabel.setSize(180, 30);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        jLabel.setLocation((SwingyWindow.WIDTH / 2) - ((int) jLabel.getWidth() / 2) - 75,
                SwingyWindow.HEIGHT - jLabel.getHeight() - 245);
        jLabel.setText("Hero's name :");

        this.nameTitle = jLabel;
    }

    /**
     * Initializes the nameError
     */
    private void initNameError() {
        final JLabel jLabel = new JLabel();

        jLabel.setSize(650, 30);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        jLabel.setForeground(Color.RED);
        jLabel.setLocation((SwingyWindow.WIDTH / 2) - 164,
                SwingyWindow.HEIGHT - jLabel.getHeight() - 165);
        jLabel.setText("");

        this.nameError = jLabel;
    }

    /**
     * Initializes the radioButtons and the group
     */
    private void initRadioButtons() {
        this.buttonGroup = new ButtonGroup();

        this.archerRadio = new JRadioButton(Archer.DEFAULT_HERO_CLASS_NAME);
        this.warriorRadio = new JRadioButton(Warrior.DEFAULT_HERO_CLASS_NAME);
        this.wizardRadio = new JRadioButton(Wizard.DEFAULT_HERO_CLASS_NAME);

        this.archerRadio.setSize(125, 20);
        this.warriorRadio.setSize(125, 20);
        this.wizardRadio.setSize(125, 20);

        this.archerRadio.setFont(new Font("Arial", Font.PLAIN, 20));
        this.warriorRadio.setFont(new Font("Arial", Font.PLAIN, 20));
        this.wizardRadio.setFont(new Font("Arial", Font.PLAIN, 20));

        this.archerRadio.setLocation((SwingyWindow.WIDTH - 100) / 4, SwingyWindow.HEIGHT - 325);
        this.warriorRadio.setLocation((SwingyWindow.WIDTH - 100) / 2, SwingyWindow.HEIGHT - 325);
        this.wizardRadio.setLocation(
                (SwingyWindow.WIDTH - 100) - ((SwingyWindow.WIDTH - 100) / 4),
                SwingyWindow.HEIGHT - 325);

        this.buttonGroup.add(this.archerRadio);
        this.buttonGroup.add(this.warriorRadio);
        this.buttonGroup.add(this.wizardRadio);
    }

    private void initClassStats() {
        this.archerStats = new HeroClassStatsDisplay(
                Archer.DEFAULT_HERO_CLASS_NAME,
                Archer.DEFAULT_HERO_CLASS_HIT_POINTS,
                Archer.DEFAULT_HERO_CLASS_ATTACK,
                Archer.DEFAULT_HERO_CLASS_DEFENSE,
                Bow.DEFAULT_ATTACK_SPEED);

        this.warriorStats = new HeroClassStatsDisplay(
                Warrior.DEFAULT_HERO_CLASS_NAME,
                Warrior.DEFAULT_HERO_CLASS_HIT_POINTS,
                Warrior.DEFAULT_HERO_CLASS_ATTACK,
                Warrior.DEFAULT_HERO_CLASS_DEFENSE,
                Sword.DEFAULT_ATTACK_SPEED);

        this.wizardStats = new HeroClassStatsDisplay(
                Wizard.DEFAULT_HERO_CLASS_NAME,
                Wizard.DEFAULT_HERO_CLASS_HIT_POINTS,
                Wizard.DEFAULT_HERO_CLASS_ATTACK,
                Wizard.DEFAULT_HERO_CLASS_DEFENSE,
                Staff.DEFAULT_ATTACK_SPEED);

        this.archerStats.setLocation(
                this.archerRadio.getX() + (this.archerRadio.getWidth() / 2) - (this.archerStats.getWidth() / 2) + 15,
                (int) (this.archerRadio.getY() - this.archerStats.getHeight() - 10));

        this.warriorStats.setLocation(
                this.warriorRadio.getX() + (this.warriorRadio.getWidth() / 2) - (this.warriorStats.getWidth() / 2) + 15,
                (int) (this.warriorRadio.getY() - this.warriorStats.getHeight() - 10));

        this.wizardStats.setLocation(
                this.wizardRadio.getX() + (this.wizardRadio.getWidth() / 2) - (this.wizardStats.getWidth() / 2) + 15,
                (int) (this.wizardRadio.getY() - this.wizardStats.getHeight() - 10));
    }

    /**
     * Get the typed name
     * @return The typed name
     */
    public String getTypedName() { return this.getNameTextArea().getText(); }

    /**
     * Get the selected class
     * @return The selected class
     */
    public String getSelectedClass() {
        for (Enumeration<AbstractButton> buttons = this.buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    /**
     * Set the SelectButtonListener
     * @param actionListener
     */
    public void setSelectButtonListener(final ActionListener actionListener) {
        this.createButton.addActionListener(actionListener);
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

    /**
     * Set the nameTextAreaListener
     * @param documentListener
     */
    public void setHeroNameListener(final DocumentListener documentListener) {
        this.nameTextArea.getDocument().addDocumentListener(documentListener);
    }

    /**
     * Get the Hero's Name TextArea
     * @return The Hero's Name TextArea
     */
    private JTextArea getNameTextArea() { return this.nameTextArea; }

    /**
     * Clear the hero's name
     */
    public void clearNameTextArea() { this.nameTextArea.setText(""); }

    /**
     * Get the Hero's Name Error Area
     * @return The Hero's Name Error Area
     */
    public JLabel getNameErrorArea() { return this.nameError; }

    /**
     * Get the create button
     * @return The create button
     */
    public JButton getCreateButton() { return this.createButton; }
}
