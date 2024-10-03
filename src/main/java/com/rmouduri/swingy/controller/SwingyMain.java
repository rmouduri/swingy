package com.rmouduri.swingy.controller;

import com.rmouduri.swingy.controller.database.Database;
import com.rmouduri.swingy.model.game.Game;
import com.rmouduri.swingy.view.gui.SwingyWindow;
import jakarta.validation.Validation;

public class SwingyMain {
    public static void main(String[] args) {
        if (args.length != 1 || (!Game.CONSOLE_MODE.equals(args[0]) && !Game.GUI_MODE.equals(args[0]))) {
            System.out.println("Invalid argument.");
            return;
        }

        Validation.buildDefaultValidatorFactory();
        Database database = new Database();

        if (database.getConnection() == null) {
            System.err.println("Database error.");
            return;
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        SwingyWindow swingyWindow = new SwingyWindow();
        swingyWindow.setVisible(Game.GUI_MODE.equals(args[0]));

        Game game = new Game(args[0], database, swingyWindow);
        game.start();

        swingyWindow.dispose();
    }
}
