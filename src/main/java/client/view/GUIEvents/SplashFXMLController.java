package client.view.GUIEvents;

import client.view.EventController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SplashFXMLController implements GenericEventController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Parent rootPane;
    @FXML
    private Button enterButton;
    @FXML
    private ImageView loadingBar;
    @FXML
    public void initialize() {
        final int[] i = {0};
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i[0] += 1;
                if (i[0] == 10) { // 10 seconds
                    enterButton.setDisable(false);
                    loadingBar.setVisible(false);
                    enterButton.setVisible(true);
                    ((Timer)e.getSource()).stop(); // Stop the timer after 10 seconds

                    try {
                        onEnterButtonClick(null); // Assuming you want to trigger the button click action programmatically
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        enterButton.setDisable(true); // Disable the button initially
        enterButton.setVisible(false); // Hide the button initially
        loadingBar.setVisible(true); // Hide the loading bar initially

        timer.start(); // Start the timer

        enterButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                onEnterButtonClick(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
    public void onEnterButtonClick(MouseEvent event) throws Exception {
        EventController.switchToServerInfoEvent(event);
    }

}
