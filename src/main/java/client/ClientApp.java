package client;

import client.view.TUI;
public class ClientApp {
    public static void main(String[] args) throws InterruptedException {

        boolean cliSelector = true; // default è true, se vogliamo usare anche gui si può cambiare

        for (String arg : args) {
            if (arg.equals("--cli")) {
                cliSelector = true;
                break;
            }
        }

        if (cliSelector) {
            TUI view = new TUI();
            view.init();
        } else {
            //eventuale gestione apertura GUI
        }

        //se si decide per socket + rmi si può aggiungere anche la scelta tra le due sempre da linea di comando
    }
}