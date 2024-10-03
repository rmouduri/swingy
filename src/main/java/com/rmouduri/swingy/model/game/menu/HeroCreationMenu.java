package com.rmouduri.swingy.model.game.menu;

import com.rmouduri.swingy.model.entity.hero.*;
import com.rmouduri.swingy.model.game.Game;
import com.rmouduri.swingy.model.game.Startable;
import com.rmouduri.swingy.view.console.HeroCreationMenuDisplayer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.*;

public class HeroCreationMenu implements Startable {
    /**
     * List of available hero classes
     */
    private static final List<AbstractHero> heroClassesList = HeroCreationMenu.fillHeroClassesList();

    /**
     * Hero creation menu displayer
     */
    private final HeroCreationMenuDisplayer heroCreationMenuDisplayer;

    /**
     * User input scanner
     */
    private Scanner userInputScanner;

    /**
     * Hero class input
     */
    private String heroClassInput;

    /**
     * Hero name input
     */
    @NotEmpty(message = "Character's name cannot be empty.")
    @NotNull(message = "Character's name cannot be null.")
    @Size(min = 3, max = 14, message = "Character's name size must be between 3 and 14 characters.")
    private String heroNameInput;

    /**
     * The created hero
     */
    private AbstractHero createdHero;

    /**
     * The game
     */
    private final Game game;

    /**
     * Constructor
     */
    public HeroCreationMenu(final Game gameParam) {
        this.userInputScanner = new Scanner(System.in);
        this.heroNameInput = null;
        this.heroClassInput = null;
        this.createdHero = null;
        this.heroCreationMenuDisplayer = new HeroCreationMenuDisplayer(this);
        this.game = gameParam;

        this.initCreateHeroMenuButtons();
    }

    /**
     * Display Hero Creation Menu and handle user input
     */
    @Override
    public void start() {
        this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_CREATE_HERO_MENU);
        if (Game.CONSOLE_MODE.equals(Game.getDisplayMode())) {
            do {
                this.heroCreationMenuDisplayer.displayClassSelection();
                this.setHeroClassInput(this.getProcessedHeroClassInput());
            } while (Game.INVALID_USER_INPUT.equals(this.getHeroClassInput()));

            if (Game.USER_INPUT_BACK.equalsIgnoreCase(this.getHeroClassInput())) {
                this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_MAIN_MENU);
                this.setCreatedHero(null);
                return;
            } else if (Game.GUI_MODE.equalsIgnoreCase(this.getHeroClassInput())) {
                this.game.setDisplayMode(Game.GUI_MODE);
                return;
            }

            do {
                this.heroCreationMenuDisplayer.displayNameSelection();
                this.processHeroNameInput();
            } while (Game.INVALID_USER_INPUT.equals(this.getHeroNameInput()));

            this.createHero();
            this.game.initNewMapPlayer();
            this.game.setToBeDisplayed(Game.TO_BE_DISPLAYED_MAP);
        } else if (Game.GUI_MODE.equals(Game.getDisplayMode())) {
            this.game.getSwingyWindow().displayCreateHeroGui();
        }
    }

    /**
     * Process the user hero class input
     * @return The process user input
     */
    private String getProcessedHeroClassInput() {
        final String userInput;
        try {
            userInput = this.userInputScanner.nextLine();
        } catch (NoSuchElementException e) {
            this.userInputScanner = new Scanner(System.in);
            return Game.INVALID_USER_INPUT;
        }

        if (Game.USER_INPUT_BACK.equalsIgnoreCase(userInput)
                || Game.USER_INPUT_BACK.substring(0, 1).equalsIgnoreCase(userInput)) {
            return Game.USER_INPUT_BACK;
        } else if (Game.GUI_MODE.equalsIgnoreCase(userInput)
                || Game.GUI_MODE.substring(0, 1).equalsIgnoreCase(userInput)) {
            return Game.GUI_MODE;
        } else if (Archer.DEFAULT_HERO_CLASS_NAME.equalsIgnoreCase(userInput)) {
            return Archer.DEFAULT_HERO_CLASS_NAME;
        } else if (Warrior.DEFAULT_HERO_CLASS_NAME.equalsIgnoreCase(userInput)) {
            return Warrior.DEFAULT_HERO_CLASS_NAME;
        } else if (Wizard.DEFAULT_HERO_CLASS_NAME.equalsIgnoreCase(userInput)) {
            return Wizard.DEFAULT_HERO_CLASS_NAME;
        }

        Game.printErrorInvalidUserInput();
        return Game.INVALID_USER_INPUT;
    }

    /**
     * Process the user hero name with annotation based user input validation
     */
    private void processHeroNameInput() {
        try {
            this.setHeroNameInput(this.userInputScanner.nextLine());
        } catch (NoSuchElementException e) {
            this.userInputScanner = new Scanner(System.in);
            this.setHeroNameInput(Game.INVALID_USER_INPUT);
        }

        if (this.game.getDatabase().doesHeroExist(this.getHeroNameInput())) {
            this.heroCreationMenuDisplayer.printErrorHeroNameAlreadyExisting(this.getHeroNameInput());
            this.setHeroNameInput(Game.INVALID_USER_INPUT);
            return;
        }

        if (!this.isNameValid()) {
            System.err.println(String.format("Cannot name a hero: `%s`", this.getHeroNameInput()));
            this.setHeroNameInput(Game.INVALID_USER_INPUT);
            return;
        }

        final List<String> hibernateValidatorErrors = this.getHibernateValidationErrors();

        if (!hibernateValidatorErrors.isEmpty()) {
            this.setHeroNameInput(Game.INVALID_USER_INPUT);
            for (final String error : hibernateValidatorErrors) {
                System.err.println(error);
            }
        }
    }

    private List<String> getHibernateValidationErrors() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        final List<String> hibernateValidationErrors = new ArrayList<>();

        Set<ConstraintViolation<HeroCreationMenu>> violations = validator.validate(this);

        for (ConstraintViolation<HeroCreationMenu> violation : violations) {
            hibernateValidationErrors.add(violation.getMessage());
        }

        return hibernateValidationErrors;
    }

    /**
     * Check if a name @param name is valid
     * @return If the name is valid
     */
    public boolean isNameValid() {
        return !(Game.USER_INPUT_BACK.equalsIgnoreCase(this.getHeroNameInput())
            || Game.GUI_MODE.equalsIgnoreCase(this.getHeroNameInput())
            || this.getHeroNameInput().contains("<") || this.getHeroNameInput().contains("'"));
    }

    /**
     * Create a hero
     */
    private void createHero() {
        this.setCreatedHero(HeroFactory.generateHero(this.getHeroClassInput(), this.getHeroNameInput()));
        this.game.getDatabase().addOrUpdateHero(this.getCreatedHero());
        this.game.setHero(this.getCreatedHero());
        this.setHeroNameInput(null);
        this.setHeroClassInput(null);
    }

    /**
     * Fill the list with the available heroClasses
     */
    private static List<AbstractHero> fillHeroClassesList() {
        final List<AbstractHero> localHeroClassesList = new ArrayList<>(3);

        localHeroClassesList.add(HeroFactory.generateHero(Archer.DEFAULT_HERO_CLASS_NAME, Archer.DEFAULT_HERO_CLASS_NAME));
        localHeroClassesList.add(HeroFactory.generateHero(Warrior.DEFAULT_HERO_CLASS_NAME, Warrior.DEFAULT_HERO_CLASS_NAME));
        localHeroClassesList.add(HeroFactory.generateHero(Wizard.DEFAULT_HERO_CLASS_NAME, Wizard.DEFAULT_HERO_CLASS_NAME));

        return localHeroClassesList;
    }

    /**
     * Initialize create hero menu buttons for gui mode
     */
    private void initCreateHeroMenuButtons() {
        this.game.getSwingyWindow().getCreateHeroGui().setSelectButtonListener(e -> {
            final String heroName = this.game.getSwingyWindow().getCreateHeroGui().getTypedName();
            final String heroClass = this.game.getSwingyWindow().getCreateHeroGui().getSelectedClass();

            if (this.game.getSwingyWindow().getCreateHeroGui().getSelectedClass() == null) {
                this.game.getSwingyWindow().getCreateHeroGui().getNameErrorArea()
                        .setText("Please, select a class.");
            } else {
                this.setHeroNameInput(heroName);
                this.setHeroClassInput(heroClass);
                this.createHero();

                this.game.getSwingyWindow().getCreateHeroGui().getNameErrorArea().setText("");
                this.game.getSwingyWindow().getCreateHeroGui().clearNameTextArea();

                this.game.setHero(this.getCreatedHero());
                this.game.initNewMapPlayer();
                this.game.getMapPlayer().start();
            }
        });

        this.game.getSwingyWindow().getCreateHeroGui().setConsoleModeButtonListener(e -> {
            this.game.setDisplayMode(Game.CONSOLE_MODE);
        });

        this.game.getSwingyWindow().getCreateHeroGui().setBackButtonListener(e -> {
            this.game.getMainMenu().start();
        });

        this.game.getSwingyWindow().getCreateHeroGui().setHeroNameListener(new DocumentListener() {
            private void updateErrorName() {
                final String heroName = HeroCreationMenu.this.game.
                        getSwingyWindow().getCreateHeroGui().getTypedName();

                HeroCreationMenu.this.setHeroNameInput(heroName);
                List<String> hibernateValidatorErrors;

                if (!HeroCreationMenu.this.isNameValid()) {
                    HeroCreationMenu.this.game.getSwingyWindow().getCreateHeroGui().getNameErrorArea()
                            .setText(String.format("Cannot name a hero: `%s`", heroName));
                    HeroCreationMenu.this.game.getSwingyWindow().getCreateHeroGui().getCreateButton().setEnabled(false);
                } else if (HeroCreationMenu.this.game.getDatabase().doesHeroExist(heroName)) {
                    HeroCreationMenu.this.game.getSwingyWindow().getCreateHeroGui().getNameErrorArea()
                            .setText(String.format(HeroCreationMenuDisplayer.HERO_ALREADY_EXISTS, heroName));
                    HeroCreationMenu.this.game.getSwingyWindow().getCreateHeroGui().getCreateButton().setEnabled(false);
                } else if (!(hibernateValidatorErrors = HeroCreationMenu.this.getHibernateValidationErrors()).isEmpty()) {
                    HeroCreationMenu.this.game.getSwingyWindow().getCreateHeroGui().getNameErrorArea()
                            .setText(hibernateValidatorErrors.get(0));
                    HeroCreationMenu.this.game.getSwingyWindow().getCreateHeroGui().getCreateButton().setEnabled(false);
                } else {
                    HeroCreationMenu.this.game.getSwingyWindow().getCreateHeroGui().getNameErrorArea().setText("");
                    HeroCreationMenu.this.game.getSwingyWindow().getCreateHeroGui().getCreateButton().setEnabled(true);
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) { this.updateErrorName(); }

            @Override
            public void removeUpdate(DocumentEvent e) { this.updateErrorName(); }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    /**
     * Get the list of heroClasses
     * @return The list of heroClasses
     */
    public static List<AbstractHero> getHeroClassesList() { return HeroCreationMenu.heroClassesList; }

    /**
     * Get the HeroClassInput
     * @return The HeroClassInput
     */
    public String getHeroClassInput() { return this.heroClassInput; }

    /**
     * Set the HeroClassInput
     * @param heroClassInputParam
     */
    private void setHeroClassInput(final String heroClassInputParam) { this.heroClassInput = heroClassInputParam; }

    /**
     * Get the HeroNameInput
     * @return The HeroNameInput
     */
    public String getHeroNameInput() { return this.heroNameInput; }

    /**
     * Set the HeroNameInput
     * @param heroNameInputParam
     */
    private void setHeroNameInput(final String heroNameInputParam) { this.heroNameInput = heroNameInputParam; }

    /**
     * Get the CreatedHero
     * @return The CreatedHero
     */
    public AbstractHero getCreatedHero() { return this.createdHero; }

    /**
     * Set the CreatedHero
     * @param createdHeroParam
     */
    private void setCreatedHero(final AbstractHero createdHeroParam) { this.createdHero = createdHeroParam; }
}
