package client;

import client.view.TUI;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class ClientApp {
    public static void main(String[] args) throws InterruptedException {

        boolean cliSelector = true; // default è true, se vogliamo usare anche gui si può cambiare

        try {
            boolean found = false;
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();

            while(interfaceEnumeration.hasMoreElements() && !found){
                NetworkInterface networkInterface = interfaceEnumeration.nextElement();

                if(!networkInterface.isVirtual() && !networkInterface.isLoopback() && networkInterface.isUp()) {
                    Enumeration<java.net.InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    int count = 0;
                    ArrayList<String> address = new ArrayList<>();
                    java.net.InetAddress inetAddress = null;
                    while (inetAddresses.hasMoreElements()) {
                        inetAddress = inetAddresses.nextElement();

                        address.add(inetAddress.getHostAddress());
                        if (count == 1) {
                            System.setProperty("java.rmi.server.hostname", address.get(0));
                            System.out.println("Indirizzo IP: " + address.get(0));
                            System.out.println("Indirizzo IP: " + address.get(1));
                            found = true;
                        }

                        count++;
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

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