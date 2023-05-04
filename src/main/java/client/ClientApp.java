package client;

import client.view.TUI;

public class ClientApp {
    public static void main(String[] args) {

        boolean cliSelector = true; // defualt è true, se vogliamo usare anche gui si può cambiare

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
            view.run();
            model.addObserver(view);
            view.addObserver(controller);
        } else {
            //eventuale gestione apertura GUI
        }

        //se si decide per socket + rmi si può aggiungere anche la scelta tra le due sempre da linea di comando
    }
}
