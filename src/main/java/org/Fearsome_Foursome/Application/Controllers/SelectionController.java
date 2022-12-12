/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022* Instructor: Prof. Brian King
 *
 * Name: Jiasong Zhu
 * Section: 02, 11:00 AM
 * Date: 11/14/22
 * Time: 5:31 PM
 *
 * Project: csci205_final_project
 * Package: org.Fearsome_Foursome.Screens.View* Class: viewController
 *
 * Description:
 *
 * ****************************************
 */
package org.Fearsome_Foursome.Application.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.Fearsome_Foursome.Application.HelloPokemon;
import org.Fearsome_Foursome.Battle.Player;
import org.Fearsome_Foursome.Creatures.Creature;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import static org.Fearsome_Foursome.Battle.Arena.GLOBAL_ARENA;
import static org.Fearsome_Foursome.Battle.TurnTracker.GLOBAL_TURN_TRACKER;

public class SelectionController {

    @FXML
    private ImageView background;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSelect1;

    @FXML
    private Button btnSelect2;

    @FXML
    private Button btnSelect3;

    @FXML
    private Button btnSelect4;

    @FXML
    private Button btnSelect5;

    @FXML
    private Button btnSelect6;

    @FXML
    private ProgressBar currentHealth1;

    @FXML
    private ProgressBar currentHealth2;

    @FXML
    private ProgressBar currentHealth3;

    @FXML
    private ProgressBar currentHealth4;

    @FXML
    private ProgressBar currentHealth5;

    @FXML
    private ProgressBar currentHealth6;

    @FXML
    private Text health1;

    @FXML
    private Text health2;

    @FXML
    private Text health3;

    @FXML
    private Text health4;

    @FXML
    private Text health5;

    @FXML
    private Text health6;

    @FXML
    private HBox pokemon1;

    @FXML
    private HBox pokemon2;

    @FXML
    private HBox pokemon3;

    @FXML
    private HBox pokemon4;

    @FXML
    private HBox pokemon5;

    @FXML
    private HBox pokemon6;

    @FXML
    private ImageView sprite1;

    @FXML
    private ImageView sprite2;

    @FXML
    private ImageView sprite3;

    @FXML
    private ImageView sprite4;

    @FXML
    private ImageView sprite5;

    @FXML
    private ImageView sprite6;

    @FXML
    private Text textSelection;

    /**
     * To keep track of the friendly {@link ProgressBar}s on the Selection screen
     */
    private ProgressBar[] healthBars;

    /**
     * To keep track of the friendly {@link Creature}s
     */
    private Creature[] friendlyCreatures;

    @FXML
    void initialize() {
        // initialize the array of friendly Creatures
        this.friendlyCreatures = new Creature[]{
                GLOBAL_ARENA.getUser().getPokeCreature(0),
                GLOBAL_ARENA.getUser().getPokeCreature(1),
                GLOBAL_ARENA.getUser().getPokeCreature(2),
                GLOBAL_ARENA.getUser().getPokeCreature(3),
                GLOBAL_ARENA.getUser().getPokeCreature(4),
                GLOBAL_ARENA.getUser().getPokeCreature(5)
        };

        // initialize the array of ProgressBars
        this.healthBars = new ProgressBar[]{
                currentHealth1,
                currentHealth2,
                currentHealth3,
                currentHealth4,
                currentHealth5,
                currentHealth6
        };

        assert background != null : "fx:id=\"background\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert btnSelect1 != null : "fx:id=\"btnSelect1\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert btnSelect2 != null : "fx:id=\"btnSelect2\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert btnSelect3 != null : "fx:id=\"btnSelect3\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert btnSelect4 != null : "fx:id=\"btnSelect4\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert btnSelect5 != null : "fx:id=\"btnSelect5\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert btnSelect6 != null : "fx:id=\"btnSelect6\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert currentHealth1 != null : "fx:id=\"currentHealth1\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert currentHealth2 != null : "fx:id=\"currentHealth2\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert currentHealth3 != null : "fx:id=\"currentHealth3\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert currentHealth4 != null : "fx:id=\"currentHealth4\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert currentHealth5 != null : "fx:id=\"currentHealth5\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert currentHealth6 != null : "fx:id=\"currentHealth6\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert health1 != null : "fx:id=\"health1\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert health2 != null : "fx:id=\"health2\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert health3 != null : "fx:id=\"health3\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert health4 != null : "fx:id=\"health4\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert health5 != null : "fx:id=\"health5\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert health6 != null : "fx:id=\"health6\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert pokemon1 != null : "fx:id=\"pokemon1\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert pokemon2 != null : "fx:id=\"pokemon2\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert pokemon3 != null : "fx:id=\"pokemon3\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert pokemon4 != null : "fx:id=\"pokemon4\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert pokemon5 != null : "fx:id=\"pokemon5\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert pokemon6 != null : "fx:id=\"pokemon6\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert sprite1 != null : "fx:id=\"sprite1\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert sprite2 != null : "fx:id=\"sprite2\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert sprite3 != null : "fx:id=\"sprite3\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert sprite4 != null : "fx:id=\"sprite4\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert sprite5 != null : "fx:id=\"sprite5\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert sprite6 != null : "fx:id=\"sprite6\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
        assert textSelection != null : "fx:id=\"textSelection\" was not injected: check your FXML file 'pokemonSelection.fxml'.";
    }

    /**
     * Method to return to the arena screen if we are allowed to
     * @param mouseEvent - necessary {@link MouseEvent} parameter
     */
    public void returnToArenaIfAllowed(MouseEvent mouseEvent) {
        if (!ArenaController.playerCreatureJustDied){
            this.switchToArena(mouseEvent);
        }
        // otherwise, a Pokémon was killed and the user HAS to switch
    }

    /**
     * Method to switch back to the arena
     * @param ignoredMouseEvent - necessary {@link MouseEvent} parameter
     */
    public void switchToArena(MouseEvent ignoredMouseEvent) {
        Stage stage = (Stage) background.getScene().getWindow();
        HelloPokemon.loadScene(stage, HelloPokemon.GameScenes.POKEMON_ARENA);
        HelloPokemon.arenaController.setUpPokemon();
    }

    /**
     * Sets the {@link org.Fearsome_Foursome.Creatures.Creature} up front for the player to be the first {@link org.Fearsome_Foursome.Creatures.Creature}, if that {@link org.Fearsome_Foursome.Creatures.Creature} is alive
     * @param mouseEvent - necessary {@link MouseEvent} parameter
     */
    public void pick1(MouseEvent mouseEvent) {
        pickCreature(1, mouseEvent);
    }

    /**
     * Self-explanatory what this method should do
     * @param mouseEvent - necessary {@link MouseEvent} parameter
     */
    public void pick2(MouseEvent mouseEvent) {
        pickCreature(2, mouseEvent);
    }

    /**
     * Self-explanatory what this method should do
     * @param mouseEvent - necessary {@link MouseEvent} parameter
     */
    public void pick3(MouseEvent mouseEvent) {
        pickCreature(3, mouseEvent);
    }

    /**
     * Self-explanatory what this method should do
     * @param mouseEvent - necessary {@link MouseEvent} parameter
     */
    public void pick4(MouseEvent mouseEvent) {
        pickCreature(4, mouseEvent);
    }

    /**
     * Self-explanatory what this method should do
     * @param mouseEvent - necessary {@link MouseEvent} parameter
     */
    public void pick5(MouseEvent mouseEvent) {
        pickCreature(5, mouseEvent);
    }

    /**
     * Self-explanatory what this method should do
     * @param mouseEvent - necessary {@link MouseEvent} parameter
     */
    public void pick6(MouseEvent mouseEvent) {
        pickCreature(6, mouseEvent);
    }

    /**
     * Load up a certain creature
     * @param creatureNumber - corresponds to which {@link Creature} from the {@link Player} we want to retrieve
     * @param mouseEvent - necessary {@link MouseEvent} parameter
     */
    private void pickCreature(int creatureNumber, MouseEvent mouseEvent) {
        int userIndex = creatureNumber - 1;

        if (GLOBAL_ARENA.canSetUserCreatureUpFrontIndex(userIndex)) {
            // the user did not try to pick a dead Pokémon or the one that they already had

            if (ArenaController.playerCreatureJustDied) {
                // if a Creature just died, then the user gets to swap without using up a turn
                GLOBAL_ARENA.setUserCreatureUpFrontIndex(userIndex);
                HelloPokemon.arenaController.setUpPokemon();
                HelloPokemon.arenaController.setUserSwapBattleLog();

                // just go back to the arena
                this.switchToArena(mouseEvent);
                // maybe the round is over - then we need to wait for the user click
                // maybe the round is not over - then the enemy used up their turn or the player's creature would not be dead - and we need to wait for the user to click for their Pokémon to go

            } else {
                // then switching Pokémon will take a turn
                // if we made it to this stage in the conditional on the selection screen, the arena controller has it so that one of these WILL PASS

                // new round if the last round finished (one possibility for reaching this screen without death)
                GLOBAL_TURN_TRACKER.willDoNextRound();

                // either way, a turn is taken
                GLOBAL_TURN_TRACKER.takeTurnIfAllowed(GLOBAL_ARENA.getUser());

                // set up the  new user Creature up to bat
                GLOBAL_ARENA.setUserCreatureUpFrontIndex(userIndex);
                HelloPokemon.arenaController.setUpPokemon();

                // TODO - this text is not showing up and I don't know why
                HelloPokemon.arenaController.setUserSwapBattleLog();

                // go back to the arena
                this.switchToArena(mouseEvent);

                // if the enemy needs to go now, they will go
                HelloPokemon.arenaController.progressExchangeOfMoves(mouseEvent);
            }
        }
    }

    /**
     * Method to show the correct Pokémon for the given team
     */
    public void showPokemon() {
        try {
            File fileForSprite1 = new File("src/main/resources/Sprites/" + Creature.CREATURE_SPRITE_MAP.get(GLOBAL_ARENA.getUser().getCreatureArray()[0].getName())[1]);
            sprite1.setImage(new Image(fileForSprite1.toURI().toURL().toExternalForm()));

            File fileForSprite2 = new File("src/main/resources/Sprites/" + Creature.CREATURE_SPRITE_MAP.get(GLOBAL_ARENA.getUser().getCreatureArray()[1].getName())[1]);
            sprite2.setImage(new Image(fileForSprite2.toURI().toURL().toExternalForm()));

            File fileForSprite3 = new File("src/main/resources/Sprites/" + Creature.CREATURE_SPRITE_MAP.get(GLOBAL_ARENA.getUser().getCreatureArray()[2].getName())[1]);
            sprite3.setImage(new Image(fileForSprite3.toURI().toURL().toExternalForm()));

            File fileForSprite4 = new File("src/main/resources/Sprites/" + Creature.CREATURE_SPRITE_MAP.get(GLOBAL_ARENA.getUser().getCreatureArray()[3].getName())[1]);
            sprite4.setImage(new Image(fileForSprite4.toURI().toURL().toExternalForm()));

            File fileForSprite5 = new File("src/main/resources/Sprites/" + Creature.CREATURE_SPRITE_MAP.get(GLOBAL_ARENA.getUser().getCreatureArray()[4].getName())[1]);
            sprite5.setImage(new Image(fileForSprite5.toURI().toURL().toExternalForm()));

            File fileForSprite6 = new File("src/main/resources/Sprites/" + Creature.CREATURE_SPRITE_MAP.get(GLOBAL_ARENA.getUser().getCreatureArray()[5].getName())[1]);
            sprite6.setImage(new Image(fileForSprite6.toURI().toURL().toExternalForm()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.showProgressBars();
    }

    /**
     * Method to show the health of each of the Pokémon available for the player to select
     */
    public void showProgressBars() {
        // Adjust the progress bar to the remaining health for both Pokémon
        for (int i=0; i<this.friendlyCreatures.length; i++){
            ProgressBar healthBar = this.healthBars[i];
            Creature friendlyCreature = this.friendlyCreatures[i];
            healthBar.setProgress(1.0 * friendlyCreature.getHealth() / friendlyCreature.getMaxHealth());
            HelloPokemon.progressBarColor(healthBar);
        }
    }

}

