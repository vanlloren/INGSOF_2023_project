package client.view;

import client.view.GUIEvents.GenericEventController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class EventController {
    private static GenericEventController actualController;
    private static Scene actualScene;
    private static Stage stage;

    private Parent root;
    public static Scene getActualScene() {
        return actualScene;
    }

    public static GenericEventController getActualController() {
        return actualController;
    }
    public static <T> T switchToMenuEvent(MouseEvent event) throws IOException {
        Scene scene = ((Node) event.getSource()).getScene();
        T controller = null;
        FXMLLoader loader = new FXMLLoader(EventController.class.getResource("/fxml/menu_event.fxml"));
        Parent root = loader.load();
        actualScene = scene;
        actualController = (GenericEventController) loader.getController();
        actualScene.setRoot(root);
        return controller;
    }

    public static <T> T switchToServerInfoEvent(MouseEvent event) throws IOException {
        Scene scene = ((Node) event.getSource()).getScene();
        T controller = null;
        FXMLLoader loader = new FXMLLoader(EventController.class.getResource("/fxml/ServerInfoEvent.fxml"));
        Parent root = loader.load();
        actualScene = scene;
        actualController = (GenericEventController) loader.getController();
        actualScene.setRoot(root);
        return controller;
    }

    public static <T> T switchToLoginEvent(MouseEvent event) throws IOException {
        Scene scene = ((Node) event.getSource()).getScene();
        T controller = null;
        FXMLLoader loader = new FXMLLoader(EventController.class.getResource("/fxml/LoginFXMLEvent.fxml"));
        Parent root = loader.load();
        actualScene = scene;
        actualController = (GenericEventController) loader.getController();
        actualScene.setRoot(root);
        return controller;
    }

    public static <T> T switchToPlayersNumberEvent(MouseEvent event) throws IOException {
        Scene scene = ((Node) event.getSource()).getScene();
        T controller = null;
        FXMLLoader loader = new FXMLLoader(EventController.class.getResource("/fxml/PlayerNumberFXMLEvent.fxml"));
        Parent root = loader.load();
        actualScene = scene;
        actualController = (GenericEventController) loader.getController();
        actualScene.setRoot(root);
        return controller;

    }
    public static <T> T switchToLobbyFXML(MouseEvent event) throws IOException {
        Scene scene = ((Node) event.getSource()).getScene();
        T controller = null;
        FXMLLoader loader = new FXMLLoader(EventController.class.getResource("/fxml/LobbyFXMLEvent.fxml"));
        Parent root = loader.load();
        actualScene = scene;
        actualController = (GenericEventController) loader.getController();
        actualScene.setRoot(root);
        return controller;

    }

    public static <T> T switchToSplashFXML(MouseEvent event) throws IOException {
        Scene scene = ((Node) event.getSource()).getScene();
        T controller = null;
        FXMLLoader loader = new FXMLLoader(EventController.class.getResource("/fxml/SplashFXML.fxml"));
        Parent root = loader.load();
        actualScene = scene;
        actualController = (GenericEventController) loader.getController();
        actualScene.setRoot(root);
        return controller;

    }

}