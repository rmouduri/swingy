package com.rmouduri.swingy.view.gui;

import com.rmouduri.swingy.model.entity.hero.AbstractHero;

import javax.swing.*;
import java.awt.*;

public class HeroStatsSidebarDisplay extends Container {
    /**
     * The hero
     */
    private AbstractHero hero;

    /**
     * The name Label
     */
    private JLabel nameLabel;

    /**
     * The classAndLevel Label
     */
    private JLabel classAndLevelLabel;

    /**
     * The experience Label
     */
    private JLabel experienceLabel;

    /**
     * The HitPoints label
     */
    private JLabel hpLabel;

    /**
     * The Attack label
     */
    private JLabel atkLabel;

    /**
     * The Defense label
     */
    private JLabel defLabel;

    /**
     * The AttackSpeed label
     */
    private JLabel asLabel;

    /**
     * The weaponLabel label
     */
    private JLabel weaponLabel;

    /**
     * The helmetLabel label
     */
    private JLabel helmetLabel;

    /**
     * The armorLabel label
     */
    private JLabel armorLabel;

    /**
     * Constructor
     */
    public HeroStatsSidebarDisplay() {
        this.setSize((int) (SwingyWindow.WIDTH * .25), 250);
        this.setLocation(0, 170);

        this.initLabels();
    }

    /**
     * Constructor
     * @param heroParam
     */
    public HeroStatsSidebarDisplay(final AbstractHero heroParam) {
        this.setSize((int) (SwingyWindow.WIDTH * .25), 250);
        this.setLocation(0, 200);

        this.initLabels();
        this.addHero(heroParam);
    }

    /**
     * Add a hero to display stats of
     * @param heroParam
     */
    public void addHero(final AbstractHero heroParam) {
        this.hero = heroParam;
        this.updateLabels();
    }

    /**
     * Initializes the labels
     */
    private void initLabels() {
        this.nameLabel = new JLabel();
        this.experienceLabel = new JLabel();
        this.classAndLevelLabel = new JLabel();
        this.hpLabel = new JLabel();
        this.atkLabel = new JLabel();
        this.defLabel = new JLabel();
        this.asLabel = new JLabel();
        this.weaponLabel = new JLabel();
        this.helmetLabel = new JLabel();
        this.armorLabel = new JLabel();

        this.nameLabel.setLocation(0, 0);
        this.classAndLevelLabel.setLocation(0, 30);
        this.experienceLabel.setLocation(0, 60);
        this.hpLabel.setLocation(0, 90);
        this.atkLabel.setLocation(0, 120);
        this.defLabel.setLocation(0, 150);
        this.asLabel.setLocation(0, 180);
        this.weaponLabel.setLocation(30, 210);
        this.helmetLabel.setLocation(125, 210);
        this.armorLabel.setLocation(215, 210);

        this.nameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.experienceLabel.setHorizontalAlignment(JLabel.CENTER);
        this.classAndLevelLabel.setHorizontalAlignment(JLabel.CENTER);
        this.hpLabel.setHorizontalAlignment(JLabel.CENTER);
        this.atkLabel.setHorizontalAlignment(JLabel.CENTER);
        this.defLabel.setHorizontalAlignment(JLabel.CENTER);
        this.asLabel.setHorizontalAlignment(JLabel.CENTER);

        this.nameLabel.setSize(this.getWidth(), 20);
        this.classAndLevelLabel.setSize(this.getWidth(), 20);
        this.experienceLabel.setSize(this.getWidth(), 20);
        this.hpLabel.setSize(this.getWidth(), 20);
        this.atkLabel.setSize(this.getWidth(), 20);
        this.defLabel.setSize(this.getWidth(), 20);
        this.asLabel.setSize(this.getWidth(), 20);
        this.weaponLabel.setSize(this.getWidth(), 18);
        this.helmetLabel.setSize(this.getWidth(), 18);
        this.armorLabel.setSize(this.getWidth(), 18);

        this.nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.classAndLevelLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.experienceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.hpLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.atkLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.defLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.asLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.weaponLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.helmetLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.armorLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        this.add(this.nameLabel);
        this.add(this.classAndLevelLabel);
        this.add(this.experienceLabel);
        this.add(this.hpLabel);
        this.add(this.atkLabel);
        this.add(this.defLabel);
        this.add(this.asLabel);
        this.add(this.weaponLabel);
        this.add(this.helmetLabel);
        this.add(this.armorLabel);
    }

    /**
     * Update the labels
     */
    public void updateLabels() {
        this.nameLabel.setText(String.format(
                "<html><span style='color:%s;'>%s</span></html>", "#1E90FF", this.hero.getName()));

        this.classAndLevelLabel.setText(String.format("Lvl %d %s", this.hero.getLevel(), this.hero.getEntityClass()));

        this.experienceLabel.setText(String.format(
                "<html>%s: <span style='color:%s;'>%d / %d</span></html>", "Exp", "#A54FB8",
                this.hero.getOverflowExperience(), AbstractHero.getXpPerLevel(this.hero.getLevel())));

        this.hpLabel.setText(String.format(
                "<html>%s: <span style='color:%s;'>%d / %d</span></html>", "HP", "#3CB371",
                this.hero.getCurrentHitPoints().getRawStat(), this.hero.getMaxHitPoints().getRawStat()));

        this.atkLabel.setText(String.format(
                "<html>%s: <span style='color:%s;'>%.1f</span></html>", "Atk", "red",
                this.hero.getAttack().getRawStat()));

        this.defLabel.setText(String.format(
                "<html>%s: <span style='color:%s;'>%.1f</span></html>", "Def", "blue",
                this.hero.getDefense().getRawStat()));

        this.asLabel.setText(String.format(
                "<html>%s: <span style='color:%s;'>%.1f</span></html>", "AS", "orange",
                this.hero.getAttackSpeed().getRawStat()));

        this.weaponLabel.setText(String.format(
                "<html>WPN: <span style='color:#B8634F;'>%s</span></html>",
                this.hero.getWeapon() == null ? "X" : "T" + this.hero.getWeapon().getTier()));

        this.helmetLabel.setText(String.format(
                "<html>HLM: <span style='color:#B8634F;'>%s</span></html>",
                this.hero.getHelmet() == null ? "X" : "T" + this.hero.getHelmet().getTier()));

        this.armorLabel.setText(String.format(
                "<html>ARM: <span style='color:#B8634F;'>%s</span></html>",
                this.hero.getArmor() == null ? "X" : "T" + this.hero.getArmor().getTier()));
    }
}
