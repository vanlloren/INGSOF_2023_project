package client;

import client.view.Gui;
import client.view.TUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientApp {
    public static void main(String[] args) {

        boolean cliSelector = false; // defualt è true, se vogliamo usare anche gui si può cambiare

        for (String arg : args) {
            if (arg.equals("--cli")) {
                cliSelector = true;
                break;
            }
        }

        if (cliSelector) {
            TUI view = new TUI();
            // ClientController clientcontroller = new ClientController(view);
            // view.addObserver(clientcontroller);
            view.init();
        } else {
           Application.launch(Gui.class, args);
        }

        //se si decide per socket + rmi si può aggiungere anche la scelta tra le due sempre da linea di comando
    }

}
