package com.rmouduri.swingy.view.gui;

import com.rmouduri.swingy.model.statistic.Attack;
import com.rmouduri.swingy.model.statistic.AttackSpeed;
import com.rmouduri.swingy.model.statistic.Defense;
import com.rmouduri.swingy.model.statistic.HitPoints;

import javax.swing.*;
import java.awt.*;

public class HeroClassStatsDisplay extends Container {
    /**
     * The class name
     */
    final private String heroClass;
    /**
     * The class HitPoints
     */
    final private HitPoints hp;
    /**
     * The class Attack
     */
    final private Attack atk;
    /**
     * The class Defense
     */
    final private Defense def;
    /**
     * The class AttackSpeed
     */
    final private AttackSpeed as;

    /**
     * The stat title label
     */
    final private JLabel statTitleLabel;

    /**
     * The HitPoints label
     */
    final private JLabel hpLabel;

    /**
     * The Attack label
     */
    final private JLabel atkLabel;

    /**
     * The Defense label
     */
    final private JLabel defLabel;

    /**
     * The AttackSpeed label
     */
    final private JLabel asLabel;

    /**
     * Constructor
     * @param heroClassParam
     * @param hpParam
     * @param atkParam
     * @param defParam
     * @param asParam
     */
    public HeroClassStatsDisplay(String heroClassParam, HitPoints hpParam,
            Attack atkParam, Defense defParam, AttackSpeed asParam) {
        super();
        this.heroClass = heroClassParam;
        this.hp = hpParam;
        this.atk = atkParam;
        this.def = defParam;
        this.as = asParam;

        this.setSize(150, 150);

        this.statTitleLabel = new JLabel("Lvl 1 Stats :");
        this.hpLabel = new JLabel(String.format(
                "<html>%s: <span style='color:%s;'>%d</span></html>", "HP", "#3CB371", this.hp.getRawStat()),
                SwingConstants.CENTER);
        this.atkLabel = new JLabel(String.format(
                "<html>%s: <span style='color:%s;'>%.1f</span></html>", "Atk", "red", this.atk.getRawStat()),
                SwingConstants.CENTER);
        this.defLabel = new JLabel(String.format(
                "<html>%s: <span style='color:%s;'>%.1f</span></html>", "Def", "blue", this.def.getRawStat()),
                SwingConstants.CENTER);
        this.asLabel = new JLabel(String.format(
                "<html>%s: <span style='color:%s;'>%.1f</span></html>", "AS", "orange", this.as.getRawStat()),
                SwingConstants.CENTER);

        this.statTitleLabel.setLocation(0, 0);
        this.hpLabel.setLocation(0, 30);
        this.atkLabel.setLocation(0, 60);
        this.defLabel.setLocation(0, 90);
        this.asLabel.setLocation(0, 120);

        this.statTitleLabel.setSize(120, 20);
        this.hpLabel.setSize(100, 20);
        this.atkLabel.setSize(100, 20);
        this.defLabel.setSize(100, 20);
        this.asLabel.setSize(100, 20);

        this.statTitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.hpLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.atkLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.defLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.asLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        this.add(this.statTitleLabel);
        this.add(this.hpLabel);
        this.add(this.atkLabel);
        this.add(this.defLabel);
        this.add(this.asLabel);
    }
}
