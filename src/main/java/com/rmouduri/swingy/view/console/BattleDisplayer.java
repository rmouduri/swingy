package com.rmouduri.swingy.view.console;

import com.rmouduri.swingy.model.game.battle.AttackSummary;
import com.rmouduri.swingy.model.game.battle.Battle;
import com.rmouduri.swingy.model.game.battle.BattleSummary;
import com.rmouduri.swingy.model.game.Game;

import javax.swing.*;

public class BattleDisplayer implements Displayable<Battle> {
    /**
     * The battle
     */
    private Battle battle;

    /**
     * The battle summary
     */
    private BattleSummary battleSummary;

    /**
     * Constructor
     */
    public BattleDisplayer(final Battle battleParam) {
        this.setDisplayableItem(battleParam);
        this.battleSummary = this.getBattle().getBattleSummary();
    }

    /**
     * Display the attack in AttackSummary @param as
     * @param as
     */
    public void displayAttackConsoleMode(final AttackSummary as) {
        System.out.println(String.format("%s attacks %s and deals %d damages !",
            as.getAttacker().getColoredName(), as.getDefender().getColoredName(), as.getDamageDealt()));
    }

    /**
     * Display the full battle
     */
    @Override
    public void display() {
        if (this.getBattleSummary() == null) {
            System.err.println(BattleDisplayer.class + ": BattleSummary is null.");
            return;
        }

        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            for (AttackSummary as : this.battleSummary.getAttackSummaries()) {
                this.displayAttackConsoleMode(as);
            }

            System.out.println(String.format("\n%s is dead.", this.battleSummary.getLoser().getColoredName()));
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            final StringBuilder battleStrings = new StringBuilder("<html>");

            for (AttackSummary as : this.battleSummary.getAttackSummaries()) {
                battleStrings.append(String.format(
                        "<span style='color:%s;'>%s</span> attacks <span style='color:%s;'>%s</span> " +
                                "and deals %d damages !<br>",
                        as.getAttacker().getHtmlColor(), as.getAttacker().getName(),
                        as.getDefender().getHtmlColor(), as.getDefender().getName(), as.getDamageDealt()));
            }
            battleStrings.append(String.format("<span style='color:%s;'>%s</span> is dead.",
                    this.battleSummary.getLoser().getHtmlColor(), this.battleSummary.getLoser().getName()));
            battleStrings.append("</html>");

            JOptionPane.showMessageDialog(this.battle.getGame().getSwingyWindow(),
                    battleStrings.toString(),"Battle", JOptionPane.PLAIN_MESSAGE);
        }


    }

    /**
     * Get the battle summary
     * @return The battle summary
     */
    public BattleSummary getBattleSummary() { return this.battleSummary; }

    public Battle getBattle() { return this.battle; }

    public void setDisplayableItem(final Battle battleParam) {
        this.battle = battleParam;
        this.battleSummary = this.battle.getBattleSummary();
    }
}
