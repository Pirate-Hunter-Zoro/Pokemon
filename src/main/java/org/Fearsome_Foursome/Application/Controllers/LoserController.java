package org.Fearsome_Foursome.Application.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.Fearsome_Foursome.Application.HelloPokemon;

import static org.Fearsome_Foursome.Battle.Arena.GLOBAL_ARENA;

public class LoserController {

    @FXML // fx:id="background"
    private ImageView background; // Value injected by FXMLLoader

    @FXML // fx:id="closeBtn"
    private Button closeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="gameOver"
    private ImageView gameOver; // Value injected by FXMLLoader

    @FXML // fx:id="pane"
    private AnchorPane pane; // Value injected by FXMLLoader

    @FXML // fx:id="question"
    private Label question; // Value injected by FXMLLoader

    @FXML // fx:id="sadPikachu"
    private ImageView sadPikachu; // Value injected by FXMLLoader

    @FXML // fx:id="startBtn"
    private Button startBtn; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert background != null : "fx:id=\"background\" was not injected: check your FXML file 'loserScreen.fxml'.";
        assert closeBtn != null : "fx:id=\"closeBtn\" was not injected: check your FXML file 'loserScreen.fxml'.";
        assert gameOver != null : "fx:id=\"gameOver\" was not injected: check your FXML file 'loserScreen.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'loserScreen.fxml'.";
        assert question != null : "fx:id=\"question\" was not injected: check your FXML file 'loserScreen.fxml'.";
        assert sadPikachu != null : "fx:id=\"sadPikachu\" was not injected: check your FXML file 'loserScreen.fxml'.";
        assert startBtn != null : "fx:id=\"startBtn\" was not injected: check your FXML file 'loserScreen.fxml'.";
    }

    /**
     * Quit the application
     * @param ignoredActionEvent - necessary {@link ActionEvent} parameter
     */
    @FXML
    public void handleCloseButtonAction(ActionEvent ignoredActionEvent) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Go to the arena
     * @param ignoredMouseEvent - necessary {@link MouseEvent} parameter
     */
    public void returnHome(MouseEvent ignoredMouseEvent) {
        // Get the Stage object of this button
        Stage stage = (Stage) startBtn.getScene().getWindow();
        HelloPokemon.loadScene(stage, HelloPokemon.GameScenes.POKEMON_MENU);
    }

}
