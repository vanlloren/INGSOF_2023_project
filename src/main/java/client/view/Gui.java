package client.view;

import client.view.GUIEvents.MenuEventController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {
    private int itemsToLoad = 10000;
    private TilePane root;
    private Stage mainWindow;
    private Scene scene;
   @Override
    public void start(Stage stage) throws Exception {
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/menu_event.fxml"));
        Parent rootLayout = null;
        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            System.exit(1);
        }
        MenuEventController controller = loader.getController();
        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.setWidth(1280d);
        stage.setHeight(720d);
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setTitle("MyShelfie Board Game");
        stage.show();
        //new SplashScreen(stage,splash_image,()-> initializeGame(),()->showGame());
    }

    public void stop() {
        Platform.exit();
        System.exit(0);
    }
}