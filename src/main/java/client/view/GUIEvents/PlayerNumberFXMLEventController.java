package client.view.GUIEvents;

import client.view.EventController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PlayerNumberFXMLEventController implements GenericEventController{
    @FXML
    private Parent rootPane;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private Button returnButton;
    @FXML
    private RadioButton twoPlayerButton;
    @FXML
    private RadioButton threePlayerButton;
    @FXML
    private RadioButton fourPlayerButton;
    @FXML
    private Button enterLobbyButton;



    public void initialize() {
        twoPlayerButton.setOpacity(0.5);
        threePlayerButton.setOpacity(0.5);
        fourPlayerButton.setOpacity(0.5);
        enterLobbyButton.setVisible(false);

        twoPlayerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            onTwoPlayerButtonClick(event1);
        });

        threePlayerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            onThreePlayerButtonClick(event1);
        });

        fourPlayerButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            onFourPlayerButtonClick(event1);

        });

        enterLobbyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            onEnterLobbyButtonClick(event1);
        });
        returnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                onReturnButtonClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onFourPlayerButtonClick(MouseEvent event1) {
        fourPlayerButton.setOpacity(1);
        fourPlayerButton.setDisable(true);
        enterLobbyButton.setVisible(true);
        twoPlayerButton.setDisable(true);
        threePlayerButton.setDisable(true);
    }

    private void onThreePlayerButtonClick(MouseEvent event1) {
        threePlayerButton.setOpacity(1);
        threePlayerButton.setDisable(true);
        enterLobbyButton.setVisible(true);
        twoPlayerButton.setDisable(true);
        fourPlayerButton.setDisable(true);
    }

    private void onEnterLobbyButtonClick(MouseEvent event1) {
        enterLobbyButton.setDisable(true);
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        int playersNumber = Character.getNumericValue(selectedRadioButton.getText().charAt(0));
    }


    private void onTwoPlayerButtonClick(MouseEvent event1) {
        twoPlayerButton.setOpacity(1);
        twoPlayerButton.setDisable(true);
        enterLobbyButton.setVisible(true);
        threePlayerButton.setDisable(true);
        fourPlayerButton.setDisable(true);
    }

    private void onReturnButtonClick(MouseEvent event) throws IOException {
        EventController.switchToLoginEvent(event);
    }

}
