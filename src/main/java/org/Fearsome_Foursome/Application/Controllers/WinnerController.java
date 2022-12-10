/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall2022* Instructor: Prof. Brian King
 *
 * Name: Jiasong Zhu
 * Section: 02, 11:00 AM
 * Date: 11/28/22
 * Time: 9:33 PM
 *
 * Project: csci205_final_project
 * Package: org.Fearsome_Foursome.Application.Controllers* Class: WinnerController
 *
 * Description:
 *
 * ****************************************
 */

package org.Fearsome_Foursome.Application.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.Fearsome_Foursome.Application.HelloPokemon;

public class WinnerController {

    @FXML // fx:id="background"
    private ImageView background; // Value injected by FXMLLoader

    @FXML // fx:id="closeBtn"
    private Button closeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="homeBtn"
    private Button homeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="title"
    private Text title; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert background != null : "fx:id=\"background\" was not injected: check your FXML file 'winnerScreen.fxml'.";
        assert closeBtn != null : "fx:id=\"closeBtn\" was not injected: check your FXML file 'winnerScreen.fxml'.";
        assert homeBtn != null : "fx:id=\"homeBtn\" was not injected: check your FXML file 'winnerScreen.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'winnerScreen.fxml'.";

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
     * Method to return to the home screen
     * @param ignoredMouseEvent - necessary {@link MouseEvent} parameter
     */
    public void returnHome(MouseEvent ignoredMouseEvent) {
        Stage stage = (Stage)background.getScene().getWindow();
        HelloPokemon.loadScene(stage, HelloPokemon.GameScenes.POKEMON_MENU);
    }
}


