package com.rmouduri.swingy.controller.database;

import com.rmouduri.swingy.model.entity.hero.AbstractHero;
import com.rmouduri.swingy.model.entity.hero.HeroFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    /**
     * Database path
     */
    private static final String DATABASE_URL = "jdbc:sqlite:" + System.getProperty("user.dir") +
        "/target/classes/data/swingy_database.db";

    /**
     * SQL script to create character's table
     */
    private static final String CREATE_TABLE_SQL = """
        CREATE TABLE IF NOT EXISTS character (
            name TEXT NOT NULL PRIMARY KEY,
            class TEXT NOT NULL,
            experience INT,
            weapon_tier INT,
            armor_tier INT,
            helmet_tier INT
        );
        """;

    /**
     * SQL script to select all characters in character table
     */
    private static final String SELECT_ALL_CHARACTERS_SQL = "SELECT * FROM character;";

    /**
     * SQL script to select a character by name in character table
     */
    private static final String SELECT_CHARACTER_BY_NAME_SQL =
        "SELECT * FROM character where name = '%s';";

    /**
     * SQL Script to insert a character in character table
     */
    private static final String INSERT_CHARACTER_SQL = """
            INSERT INTO character (name, class, experience, weapon_tier, armor_tier, helmet_tier)
            VALUES ('%s', '%s', %d, %s, %s, %s);
            """;

    /**
     * SQL Script to update a character by name in character table
     */
    private static final String UPDATE_CHARACTER_SQL = """
            UPDATE character
            SET experience = %d,
                weapon_tier = %s,
                armor_tier = %s,
                helmet_tier = %s
            WHERE
                name = '%s';
            """;

    /**
     * The connection to the database
     */
    private final Connection connection;

    /**
     * Constructor
     */
    public Database() {
        this.connection = this.connect();
    }

    /**
     * Connect to the database
     * @return The database connection
     */
    private Connection connect() {
        Connection tryConnection = null;

        try {
            Class.forName("org.sqlite.JDBC");

            tryConnection = DriverManager.getConnection(Database.DATABASE_URL);

            try (Statement stmt = tryConnection.createStatement()) {
                stmt.execute(Database.CREATE_TABLE_SQL);
            }

        } catch (SQLException | ClassNotFoundException e) {
            tryConnection = null;
        }

        return tryConnection;
    }

    /**
     * Get all characters in character table
     * @return The list of characters
     */
    public List<AbstractHero> getAllCharacters() {
        List<AbstractHero> charactersList = new ArrayList<>();
        ResultSet rs;

        try {
            Statement stmt = this.getConnection().createStatement();
            rs = stmt.executeQuery(Database.SELECT_ALL_CHARACTERS_SQL);
            while (rs.next()) {
                int weaponTier;
                int armorTier;
                int helmetTier;

                weaponTier = rs.getInt("weapon_tier");
                if (rs.wasNull()) {
                    weaponTier = -1;
                }

                armorTier = rs.getInt("armor_tier");
                if (rs.wasNull()) {
                    armorTier = -1;
                }

                helmetTier = rs.getInt("helmet_tier");
                if (rs.wasNull()) {
                    helmetTier = -1;
                }

                charactersList.add(HeroFactory.generateHero(
                    rs.getString("class"), rs.getString("name"),
                    rs.getLong("experience"), helmetTier, armorTier, weaponTier));
            }
        } catch (SQLException e) {
            return null;
        }

        return charactersList;
    }

    /**
     * Checks whether a hero of name @param name exists
     * @param name
     * @return True if a name exists, False otherwise
     */
    public boolean doesHeroExist(final String name) {
        ResultSet rs;

        if (name == null) {
            return false;
        }

        try {
            Statement stmt = this.getConnection().createStatement();
            rs = stmt.executeQuery(String.format(Database.SELECT_CHARACTER_BY_NAME_SQL, name));

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }

        return false;
    }

    /**
     * Add or Update existing hero
     * @param heroParam
     */
    public void addOrUpdateHero(final AbstractHero heroParam) {
        if (heroParam == null) {
            return;
        }

        if (this.doesHeroExist(heroParam.getName())) {
            try (Statement stmt = this.getConnection().createStatement()) {
                stmt.execute(
                    String.format(Database.UPDATE_CHARACTER_SQL,
                        heroParam.getExperience(),
                        heroParam.getWeapon() == null ? "NULL" : String.valueOf(heroParam.getWeapon().getTier()),
                        heroParam.getArmor() == null ? "NULL" : String.valueOf(heroParam.getArmor().getTier()),
                        heroParam.getHelmet() == null ? "NULL" : String.valueOf(heroParam.getHelmet().getTier()),
                        heroParam.getName()));
            } catch (SQLException e) {
                System.out.println("Erreur 1: " + e.getMessage());
                return;
            }
        } else {
            try (Statement stmt = this.getConnection().createStatement()) {
                stmt.execute(
                    String.format(Database.INSERT_CHARACTER_SQL,
                        heroParam.getName(),
                        heroParam.getEntityClass(),
                        heroParam.getExperience(),
                        heroParam.getWeapon() == null ? "NULL" : String.valueOf(heroParam.getWeapon().getTier()),
                        heroParam.getArmor() == null ? "NULL" : String.valueOf(heroParam.getArmor().getTier()),
                        heroParam.getHelmet() == null ? "NULL" : String.valueOf(heroParam.getHelmet().getTier())));
            } catch (SQLException e) {
                System.out.println("Erreur 2: " + e.getMessage());
                return;
            }
        }
    }

    /**
     * Get the connection to the database
     * @return The connection to the database
     */
    public Connection getConnection() { return this.connection; }
}
