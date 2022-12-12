/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022* Instructor: Prof. Brian King
 *
 * Name: Jiasong Zhu
 * Section: 02, 11:00 AM
 * Date: 11/12/22
 * Time: 11:05 PM
 *
 * Project: csci205_final_project
 * Package: org.Fearsome_Foursome.Screens.Menu* Class: MenuController
 *
 * Description:
 *
 * ****************************************
 */
package org.Fearsome_Foursome.Application.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.Fearsome_Foursome.Application.HelloPokemon;

import javax.sound.sampled.*;

import static org.Fearsome_Foursome.Battle.Arena.GLOBAL_ARENA;
import static org.Fearsome_Foursome.Battle.TurnTracker.GLOBAL_TURN_TRACKER;

public class MenuController {

    @FXML
    private Text author;

    @FXML
    private Button btnNormalMode;

    @FXML
    private Button btnHardMode;

    @FXML
    private Button closeBtn;

    @FXML
    private ImageView bird;

    @FXML
    private ImageView dragonite;

    @FXML
    private ImageView grass1;

    @FXML
    private ImageView grass2;

    @FXML
    private ImageView grass3;

    @FXML
    private ImageView pikachu;

    @FXML
    private Label title;

    @FXML
    private Text version;

    /** Do we play music or not? */
    public static boolean playMusic = false;


    @FXML
    void initialize() {
        assert author != null : "fx:id=\"author\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert closeBtn != null : "fx:id=\"closeBtn\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert bird != null : "fx:id=\"bird\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert dragonite != null : "fx:id=\"dragonite\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert grass1 != null : "fx:id=\"grass1\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert grass2 != null : "fx:id=\"grass2\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert grass3 != null : "fx:id=\"grass3\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert pikachu != null : "fx:id=\"pikachu\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert version != null : "fx:id=\"version\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert btnHardMode != null : "fx:id=\"btnHardMode\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
        assert btnNormalMode != null : "fx:id=\"btnNormalMode\" was not injected: check your FXML file 'pokemonMenu.fxml'.";
    }

    /**
     * Go to the arena
     */
    public void showArena() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Randomize the teams and play battle music
        GLOBAL_ARENA.pickNewPlayers();
        GLOBAL_TURN_TRACKER.refresh();

        if (playMusic) {
            HelloPokemon.playMusic("BattleMusic.wav");
        }

        // Get the Stage object of this button
        Stage stage = (Stage) btnNormalMode.getScene().getWindow();
        HelloPokemon.loadScene(stage, HelloPokemon.GameScenes.POKEMON_ARENA);
//        HelloPokemon.arenaController.setUpPokemon();
//        HelloPokemon.arenaController.setInitialBattleTextLog();
    }

    /**
     * Quit the application
     * @param ignoredEvent - necessary dummy {@link ActionEvent} parameter
     */
    @FXML
    public void handleCloseButtonAction(ActionEvent ignoredEvent) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Set the {@link ArenaController} static hard mode boolean
     * @param ignoredMouseEvent - necessary {@link MouseEvent} for mouse response
     * @throws UnsupportedAudioFileException - possible {@link Exception} from trying to play music
     * @throws LineUnavailableException - another possible {@link Exception} from trying to play music
     * @throws IOException - a third possible {@link Exception} from trying to play music
     */
    public void setHard(MouseEvent ignoredMouseEvent) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        ArenaController.hardMode = true;
        this.showArena();
    }

    /**
     * Set the {@link ArenaController} static hard mode boolean
     * @param ignoredMouseEvent - necessary {@link MouseEvent} for mouse response
     * @throws UnsupportedAudioFileException - possible {@link Exception} from trying to play music
     * @throws LineUnavailableException - another possible {@link Exception} from trying to play music
     * @throws IOException - a third possible {@link Exception} from trying to play music
     */
    public void setNormal(MouseEvent ignoredMouseEvent) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        ArenaController.hardMode = false;
        this.showArena();
    }

}
