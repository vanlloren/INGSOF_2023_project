package client.view.GUIEvents;

import client.view.EventController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuEventController implements GenericEventController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button playBtn;
    @FXML
    private Button exitButton;
    private Parent root;
    private static Scene scene;

    @FXML
    public void initialize() {
        playBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            try {
                onPlayBottonClick(event1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.exit(0));
    }
    public void onPlayBottonClick(MouseEvent event) throws Exception {
        EventController.switchToSplashFXML(event);
    }
}
