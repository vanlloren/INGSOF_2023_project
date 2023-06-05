package client.view.GUIEvents;

import client.view.EventController;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ServerInfoEventController implements GenericEventController{
    private final PseudoClass ERROR_PSEUDO_CLASS = PseudoClass.getPseudoClass("ERROR");
    @FXML
    private Parent rootPane;
    @FXML
    private TextField serverAddressTextField;
    @FXML
    private TextField serverPortTextField;

    @FXML
    private Button connectionButton;
    @FXML
    private Button returnButton;

    @FXML
    public void initialize() {
        connectionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            try {
                onConnectionButtonClick(event1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        returnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                onReturnButtonClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onConnectionButtonClick(MouseEvent event) throws IOException {
        String address = serverAddressTextField.getText();
        int port = serverPortTextField.getText().equals("") ? 0 : Integer.parseInt(serverPortTextField.getText());

        //boolean isValidIpAddress = TUI.checkAddressValidity(address);
        //boolean isValidPort = TUI.checkPortValidity(port);
        boolean isValidIpAddress = true;
        boolean isValidPort= true;
        serverAddressTextField.pseudoClassStateChanged(ERROR_PSEUDO_CLASS, !isValidIpAddress);
        serverPortTextField.pseudoClassStateChanged(ERROR_PSEUDO_CLASS, !isValidPort);

        if (true) { //isValidIpAddress && isValidPort
            returnButton.setDisable(true);
            connectionButton.setDisable(true);

            //Map<String, String> serverInfo = Map.of("address", address, "port", port);
            //new Thread(() -> notifyObserver(obs -> obs.onUpdateServerInfo(serverInfo))).start();
            EventController.switchToLoginEvent(event);
        }
    }

    /**
     * Handle the click on the back button.
     *
     * @param event the mouse click event.
     */
    private void onReturnButtonClick(MouseEvent event) throws IOException {
        returnButton.setDisable(true);
        connectionButton.setDisable(true);
        serverAddressTextField.setDisable(true);
        serverPortTextField.setDisable(true);
        EventController.switchToMenuEvent(event);
    }
}
