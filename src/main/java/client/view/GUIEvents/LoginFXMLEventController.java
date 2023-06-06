package client.view.GUIEvents;

import client.view.EventController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginFXMLEventController implements GenericEventController{

    @FXML
    private TextField uniqueNicknameTextField;
    @FXML
    private Parent rootPane;
    @FXML
    private Button joinButton;
    @FXML
    private Button returnToMenuButton;

    @FXML
    public void initialize() {
        joinButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            try {
                onJoinBtnClick(event1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        returnToMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                onBackToMenuBtnClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onJoinBtnClick(MouseEvent event) throws IOException {
        joinButton.setDisable(true);
        String nickname = uniqueNicknameTextField.getText();
       // new Thread(() -> notifyObserver(obs -> obs.onUpdateNickname(nickname))).start();
        EventController.switchToPlayersNumberEvent(event);
    }

    private void onBackToMenuBtnClick(MouseEvent event) throws IOException {
        joinButton.setDisable(true);
        returnToMenuButton.setDisable(true);

        //new Thread(() -> notifyObserver(ViewObserver::onDisconnection)).start();
        EventController.switchToMenuEvent(event);
    }

}
