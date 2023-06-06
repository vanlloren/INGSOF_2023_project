package client.view.GUIEvents;

import client.view.EventController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LobbyFXMLEventController implements  GenericEventController{
    @FXML
    private Parent rootPane;
    @FXML
    private Button exitButton;
    @FXML
    private Button startGameButton;
    
    public void initialize() {
        startGameButton.setOpacity(0.5);
        startGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onStartGameButtonClick);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                onExitButtonClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onStartGameButtonClick(MouseEvent event1) {
    }

    private void onExitButtonClick(MouseEvent event) throws IOException {
        exitButton.setDisable(true);
        EventController.switchToMenuEvent(event);
    }

}
