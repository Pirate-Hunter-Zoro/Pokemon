/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022
 * Instructor: Prof. Brian King
 *
 * Group: Fearsome Foursome
 * Section: 02
 * Date: 11/10/22
 * Time: 9:34 PM
 *
 * Project: csci205_final_project
 * Package: org.Fearsome_Foursome.GameMVC.Model.Controller
 * Class: GameController
 *
 * Description: Controller component of the Game MVC
 *
 * *****************************************/

package org.Fearsome_Foursome.Application.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.Fearsome_Foursome.Application.HelloPokemon;
import org.Fearsome_Foursome.Creatures.Creature;

import static org.Fearsome_Foursome.Battle.Arena.GLOBAL_ARENA;
import static org.Fearsome_Foursome.Battle.TurnTracker.GLOBAL_TURN_TRACKER;

public class ArenaController {

    @FXML
    public ImageView attackMoveEnemy;

    @FXML
    public ImageView attackMovePlayer;

    @FXML
    public ImageView supportMoveEnemy;

    @FXML
    public ImageView supportMovePlayer;

    @FXML
    public ImageView enemyTackleSprite;

    @FXML
    public ImageView playerTackleSprite;

    @FXML
    public ImageView playerSprite;

    @FXML
    public ImageView enemySprite;

    @FXML
    private TextField playerName;

    @FXML
    private TextField enemyName;

    @FXML
    private Button btnQuit;

    @FXML
    private Button moveButton1;

    @FXML
    private Button moveButton2;

    @FXML
    private Button moveButton3;

    @FXML
    private Button moveButton4;

    @FXML
    private ProgressBar playerHealthProgressBar;

    @FXML
    private ProgressBar enemyHealthProgressBar;

    @FXML
    public TextArea userBattleTextLog;

    @FXML
    public TextArea enemyBattleTextLog;

    @FXML
    private Button swapPokemonButton;

    /**
     * Text that explains what is happening in the battle with the user
     */
    private String userBattleText = "";

    /**
     * Text that explains what is happening in the battle with the enemy
     */
    private String enemyBattleText = "";

    /**
     * Index of the most recently fainted Pokémon belonging to the user
     */
    public static int previousDeadUserPokemonIndex;

    /**
     * Index of the most recently fainted Pokémon belonging to the enemy
     */
    public static int previousDeadEnemyPokemonIndex;

    /**
     * Is the user playing against a smart opponent or a random opponent?
     */
    public static boolean hardMode;

    /**
     * Has a {@link Creature} from the user just been killed?
     */
    public static boolean playerCreatureJustDied;

    /**
     * Has a {@link Creature} from the enemy just been killed?
     */
    public static boolean enemyCreatureJustDied;

    @FXML
    void initialize() {
        assert attackMoveEnemy != null : "fx:id=\"attackMoveEnemy\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert attackMovePlayer != null : "fx:id=\"attackMovePlayer\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert userBattleTextLog != null : "fx:id=\"userBattleTextLog\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert enemyBattleTextLog != null : "fx:id=\"enemyBattleTextLog\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert enemyHealthProgressBar != null : "fx:id=\"enemyHealthProgressBar\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert enemyName != null : "fx:id=\"enemyName\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert enemySprite != null : "fx:id=\"enemySprite\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert enemyTackleSprite != null : "fx:id=\"enemyTackleSprite\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert moveButton1 != null : "fx:id=\"moveButton1\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert moveButton2 != null : "fx:id=\"moveButton2\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert moveButton3 != null : "fx:id=\"moveButton3\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert moveButton4 != null : "fx:id=\"moveButton4\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert playerHealthProgressBar != null : "fx:id=\"playerHealthProgressBar\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert playerName != null : "fx:id=\"playerName\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert playerSprite != null : "fx:id=\"playerSprite\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert playerTackleSprite != null : "fx:id=\"playerTackleSprite\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert supportMoveEnemy != null : "fx:id=\"supportMoveEnemy\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert supportMovePlayer != null : "fx:id=\"supportMovePlayer\" was not injected: check your FXML file 'pokemonArena.fxml'.";
        assert swapPokemonButton != null : "fx:id=\"swapPokemonButton\" was not injected: check your FXML file 'pokemonArena.fxml'.";
    }

    /**
     * Set up the 2 current Pokémon up front by displaying their associated name, health, sprite, and moves
     */
    public void setUpPokemon() {
        // we need to make the Move images hidden
        enemyTackleSprite.setVisible(false);
        playerTackleSprite.setVisible(false);
        attackMoveEnemy.setVisible(false);
        attackMovePlayer.setVisible(false);
        supportMoveEnemy.setVisible(false);
        supportMovePlayer.setVisible(false);

        // now set up the arena
        GLOBAL_ARENA.setUpCombatants();

        // Display the correct name, sprite, and health of both the player's and the enemy's Pokémon
        setUpNameSpriteHealth();

        // Display the correct moves of the player's Pokémon
        setUpMoves(GLOBAL_ARENA.getUserCreatureUpFront());
    }

    /**
     * Set up the name, sprite, and health bar of the current Pokémon in the Arena
     */
    public void setUpNameSpriteHealth() {
        Creature playerCreatureUpFront = GLOBAL_ARENA.getUserCreatureUpFront();
        Creature enemyCreatureUpFront = GLOBAL_ARENA.getEnemyCreatureUpFront();

        // Display the name for both Pokémon
        playerName.setText(playerCreatureUpFront.getName());
        enemyName.setText(enemyCreatureUpFront.getName());
        playerName.setEditable(false);
        enemyName.setEditable(false);

        // Display the sprite for both Pokémon
        playerSprite.setImage(new Image("Sprites/" + Creature.CREATURE_SPRITE_MAP.get(playerCreatureUpFront.getName())[0]));
        enemySprite.setImage(new Image("Sprites/" + Creature.CREATURE_SPRITE_MAP.get(enemyCreatureUpFront.getName())[1]));

        // Adjust the progress bar to the remaining health for both Pokémon
        playerHealthProgressBar.setProgress(1.0 * playerCreatureUpFront.getHealth() / playerCreatureUpFront.getMaxHealth());
        enemyHealthProgressBar.setProgress(1.0 * enemyCreatureUpFront.getHealth() / enemyCreatureUpFront.getMaxHealth());

        // Change the color of the progress bar depending on the percent health remaining for both Pokémon
        HelloPokemon.progressBarColor(playerHealthProgressBar);
        HelloPokemon.progressBarColor(enemyHealthProgressBar);
    }

    /**
     * Set up the name, color, and tooltip description for each of the 4 moves of the player's Pokémon
     */
    public void setUpMoves(Creature playerCreatureUpFront) {
        // Set the text, color, and tooltip with a description of each move
        moveButton1.setText(Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(0).getName());
        moveButton1.setStyle("-fx-background-color: " + Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(0).getColor());
        Tooltip moveTooltip1 = new Tooltip(Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(0).getDescription());
        moveButton1.setTooltip(moveTooltip1);

        moveButton2.setText(Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(1).getName());
        moveButton2.setStyle("-fx-background-color: " + Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(1).getColor());
        Tooltip moveTooltip2 = new Tooltip(Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(1).getDescription());
        moveButton2.setTooltip(moveTooltip2);

        moveButton3.setText(Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(2).getName());
        moveButton3.setStyle("-fx-background-color: " + Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(2).getColor());
        Tooltip moveTooltip3 = new Tooltip(Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(2).getDescription());
        moveButton3.setTooltip(moveTooltip3);

        moveButton4.setText(Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(3).getName());
        moveButton4.setStyle("-fx-background-color: " + Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(3).getColor());
        Tooltip moveTooltip4 = new Tooltip(Creature.CREATURE_MOVE_MAP.get(playerCreatureUpFront.getClass()).get(3).getDescription());
        moveButton4.setTooltip(moveTooltip4);
    }

    /**
     * When a move is clicked, a round is progressed. Both Pokémon up front target each other and attack in order of
     * speed. The player's Pokémon uses the selected move and the enemy's Pokémon uses a computer generated move.
     *
     * @param mouseEvent {@link MouseEvent}
     */
    public void progressExchangeOfMoves(MouseEvent mouseEvent) {
        // now, the attacking - whoever is allowed to go can go, favoring whoever is fastest
        if (GLOBAL_ARENA.getUserCreatureUpFront().getSpeed() >= GLOBAL_ARENA.getEnemyCreatureUpFront().getSpeed()) {
            tryUserFirst(mouseEvent);
        } else {
            tryEnemyFirst(mouseEvent);
        }
    }

    /**
     * Method to favor the user to go first (if allowed) because of their greater (or equal) speed
     * @param mouseEvent - {@link MouseEvent}
     */
    private static void tryUserFirst(MouseEvent mouseEvent) {
        if (GLOBAL_TURN_TRACKER.takeTurnIfAllowed(GLOBAL_ARENA.getUser())) {
            // user allowed to take a turn
            GLOBAL_ARENA.doPlayerTurn(mouseEvent);
            // that was a turn
        } else if (GLOBAL_TURN_TRACKER.takeTurnIfAllowed(GLOBAL_ARENA.getEnemy())) {
            // enemy allowed to take a turn
            GLOBAL_ARENA.doEnemyTurn(mouseEvent);
        }
    }

    /**
     * Method to favor the enemy to go first (if allowed) because of their greater speed
     * @param mouseEvent - {@link MouseEvent}
     */
    private static void tryEnemyFirst(MouseEvent mouseEvent) {
        if (GLOBAL_TURN_TRACKER.takeTurnIfAllowed(GLOBAL_ARENA.getEnemy())) {
            // enemy allowed to take a turn
            GLOBAL_ARENA.doEnemyTurn(mouseEvent);
            // that was a turn
        } else if (GLOBAL_TURN_TRACKER.takeTurnIfAllowed(GLOBAL_ARENA.getUser())) {
            // user allowed to take a turn
            GLOBAL_ARENA.doPlayerTurn(mouseEvent);
        }
    }

    /**
     * Method to load the winner screen
     */
    private void loadWinnerScreen() {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        HelloPokemon.loadScene(stage, HelloPokemon.GameScenes.WINNER_SCREEN);
    }

    /**
     * Method to load the loser screen
     */
    private void loadLoserScreen() {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        HelloPokemon.loadScene(stage, HelloPokemon.GameScenes.LOSER_SCREEN);
    }

    /**
     * User chooses Move 1 or 2 or 3 or 4 depending on which button is clicked
     * @param mouseEvent {@link MouseEvent}
     */
    public void chooseMoveOne(MouseEvent mouseEvent) {
        if (GLOBAL_TURN_TRACKER.willDoNextRound() || GLOBAL_TURN_TRACKER.wouldAllow(GLOBAL_ARENA.getUser())) {
            GLOBAL_ARENA.setUserMoveIndex(0);
            playerCreatureJustDied = false;
            this.progressExchangeOfMoves(mouseEvent);
        } else {
            GLOBAL_TURN_TRACKER.printRound();
        }
    }
    public void chooseMoveTwo(MouseEvent mouseEvent) {
        if (GLOBAL_TURN_TRACKER.willDoNextRound() || GLOBAL_TURN_TRACKER.wouldAllow(GLOBAL_ARENA.getUser())) {
            GLOBAL_ARENA.setUserMoveIndex(1);
            playerCreatureJustDied = false;
            this.progressExchangeOfMoves(mouseEvent);
        } else {
            GLOBAL_TURN_TRACKER.printRound();
        }
    }
    public void chooseMoveThree(MouseEvent mouseEvent) {
        if (GLOBAL_TURN_TRACKER.willDoNextRound() || GLOBAL_TURN_TRACKER.wouldAllow(GLOBAL_ARENA.getUser())) {
            GLOBAL_ARENA.setUserMoveIndex(2);
            playerCreatureJustDied = false;
            this.progressExchangeOfMoves(mouseEvent);
        } else {
            GLOBAL_TURN_TRACKER.printRound();
        }
    }
    public void chooseMoveFour(MouseEvent mouseEvent) {
        if (GLOBAL_TURN_TRACKER.willDoNextRound() || GLOBAL_TURN_TRACKER.wouldAllow(GLOBAL_ARENA.getUser())) {
            GLOBAL_ARENA.setUserMoveIndex(3);
            playerCreatureJustDied = false;
            this.progressExchangeOfMoves(mouseEvent);
        } else {
            GLOBAL_TURN_TRACKER.printRound();
        }
    }

    /**
     * If not in the middle of combat, the player can elect to switch Pokémon
     * @param ignoredMouseEvent - necessary {@link MouseEvent} parameter
     */
    public void letUserSwitch(MouseEvent ignoredMouseEvent) {
        if (GLOBAL_TURN_TRACKER.canDoNextRound() || GLOBAL_TURN_TRACKER.wouldAllow(GLOBAL_ARENA.getUser())){
            // new round - okay
            this.showSelectionScreen();
        } else {
            GLOBAL_TURN_TRACKER.printRound();
        }
    }

    /**
     * Method to only perform a scene switch to the selection screen
     * This is called when the user elects to switch Pokémon, and when the user is forced to because of a {@link Creature} death
     */
    private void showSelectionScreen() {
        Stage stage = (Stage) swapPokemonButton.getScene().getWindow();
        HelloPokemon.loadScene(stage, HelloPokemon.GameScenes.POKEMON_SELECTION);
    }

    /**
     * Return to home screen
     *
     * @param ignoredMouseEvent {@link MouseEvent}
     */
    public void goHome(MouseEvent ignoredMouseEvent) {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        HelloPokemon.loadScene(stage, HelloPokemon.GameScenes.POKEMON_MENU);
    }

    /**
     * Set the battle log for the very start of the battle
     */
    public void setInitialBattleTextLog() {
        userBattleText = "You sent out " +
                GLOBAL_ARENA.getUserCreatureUpFront().getName() + "!";
        userBattleTextLog.setText(userBattleText);

        enemyBattleText = "The opponent sent out " +
                GLOBAL_ARENA.getEnemyCreatureUpFront().getName() + "!";
        enemyBattleTextLog.setText(enemyBattleText);
    }

    /**
     * How should the controller respond when an enemy {@link Creature} dies?
     * @param mouseEvent - necessary in case a player {@link Creature} dies within this handling
     */
    public void handleEnemyCreatureDeath(MouseEvent mouseEvent){
        enemyCreatureJustDied = true;
        GLOBAL_ARENA.getEnemy().incrementDead();
        if (GLOBAL_ARENA.isCombatOver()) {
            // player must have won
            this.loadWinnerScreen();
        } else {
            previousDeadEnemyPokemonIndex = GLOBAL_ARENA.getEnemyCreatureUpFrontIndex();
            this.setEnemySwapBattleLog();
            // now, if the enemy needs to go, they will
            this.progressExchangeOfMoves(mouseEvent);
        }
    }

    /**
     * What should the {@link ArenaController} do when a player {@link Creature} dies?
     * @param ignoredMouseEvent - necessary for switching scenes
     */
    public void handlePlayerCreatureDeath(MouseEvent ignoredMouseEvent) {
        playerCreatureJustDied = true;
        GLOBAL_ARENA.getUser().incrementDead();
        if (GLOBAL_ARENA.isCombatOver()) {
            // player must have lost
            this.loadLoserScreen();
        } else {
            // player did not lose but must change Pokémon
            previousDeadUserPokemonIndex = GLOBAL_ARENA.getUserCreatureUpFrontIndex();
            // what about the player going?
            // that's handled by the SelectionController
            this.showSelectionScreen();
        }
    }

    /**
     * If either the user's Pokémon in the Arena faints or is swapped out, then display an appropriate message in the battle log
     * This method is ONLY ever called from the SelectionController class
     */
    public void setUserSwapBattleLog() {
        // If the user's Pokémon just died, tell the user which Pokémon died and which Pokémon is being swapped in
        if (playerCreatureJustDied) {
            // the switching was already taken care of by the SelectionController class

            // record the switch
            userBattleText = "Your " +
                    GLOBAL_ARENA.getUser().getPokeCreature(previousDeadUserPokemonIndex).getName() + " fainted!\nYou sent out " +
                    GLOBAL_ARENA.getUserCreatureUpFront().getName() + "!";
        }
        else {
            // user's Pokémon is not dead but the user is swapping to a different Pokémon
            userBattleText = "You sent out " +
                    GLOBAL_ARENA.getUserCreatureUpFront().getName() + "!";
        }

        // update the battle log with the correct message
        userBattleTextLog.setText(userBattleText);

        // update the view
        this.setUpPokemon();
    }

    /**
     * When the enemy either chooses or is forced to swap out due to a death, display an appropriate message in the battle log
     */
    public void setEnemySwapBattleLog() {
        if (enemyCreatureJustDied) {
            // enemy lost a Pokémon - have them switch
            GLOBAL_ARENA.switchEnemyPokemon();

            // record the switch
            enemyBattleText = "The opponent's " +
                    GLOBAL_ARENA.getEnemy().getPokeCreature(previousDeadEnemyPokemonIndex).getName() + " fainted!";
            enemyBattleText += "\nThe opponent sent out " +
                    GLOBAL_ARENA.getEnemyCreatureUpFront().getName() + "!";

            // update the enemy death status
            enemyCreatureJustDied = false;
        }
        else {
            // enemy CHOSE to switch Pokémon
            enemyBattleText = "The opponent sent out " +
                    GLOBAL_ARENA.getEnemyCreatureUpFront().getName() + "!";
        }

        // update the battle log with the correct message
        enemyBattleTextLog.setText(enemyBattleText);

        // update the view
        this.setUpPokemon();
    }
}