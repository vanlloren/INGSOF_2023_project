package Network.ServerSide;

import server.Controller.GameController;
import server.Model.GameModel;

import java.io.PrintStream;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * This Class is the launcher of the ServerSide part of <strong>MyShelfie</strong>
 * application. By running this Class a new <strong>MyShelfieServer</strong> will be created.
 */

public class ServerApp {

    private static final PrintStream out = System.out;
    private static final Scanner scanner = new Scanner(System.in);
    private static RemoteServerImplementation remoteServer;
    private static int serverPort;
    private static boolean isValid = false;

    /**
     * The root method of the process that has the responsibility of creating and realizing the correct
     * setup of the {@link RemoteServerImplementation RemoteServer}.
     * In detail, the method focuses on checking all the {@link NetworkInterface NetworkInterfaces} of the host
     * to set the correct hostname on which the {@link java.rmi.registry.Registry RMIRegistry} will be created.
     * The method skips all the virtual {@link NetworkInterface NetworkInterfaces} and the eventual {@link NetworkInterface NetworkInterfaces}
     * that are created on the host by software that provide Virtualization services.
     * This way it assures that the {@link java.rmi.registry.Registry RMIRegistry} is reachable by the
     * {@link Network.ClientSide.RemoteClientImplementation RemoteClients} and not created on a host-limited
     * internet connection.
     *
     * @param args an Array {@link String String} containing the eventual arguments
     */
    public static void main(String[] args){
        String catchAddress = null;

        try {
            boolean found = false;
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();

            while(interfaceEnumeration.hasMoreElements() && !found){
                NetworkInterface networkInterface = interfaceEnumeration.nextElement();

                if(!networkInterface.isVirtual() && !networkInterface.isLoopback() && networkInterface.isUp()) {
                    Enumeration<java.net.InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    int count = 0;
                    ArrayList<String> address = new ArrayList<>();
                    java.net.InetAddress inetAddress;
                    while (inetAddresses.hasMoreElements()) {
                        inetAddress = inetAddresses.nextElement();
                        address.add(inetAddress.getHostAddress());
                        if (count == 1) {
                            System.setProperty("java.rmi.server.hostname", address.get(0));
                            System.out.println("Indirizzo IP: " + address.get(0));
                            System.out.println("Indirizzo IP: " + address.get(1));
                            catchAddress = address.get(0);
                            found = true;
                        }
                        count++;
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }




        out.println("Benvenuto su MyShelfie! Stai per creare il server per la gestione della partita....");
        out.println("Inserisci il numero di porta da usare per il server:");
        do {
            String serverPortString = scanner.next();
            try {
                serverPort = Integer.parseInt(serverPortString);
                isValid = true;
            } catch (NumberFormatException ex) {
                out.println("Stringa non valida per favore inserisci valore numerico per il numero di porta");
            }
        }while (!isValid);
        GameModel gameModel = new GameModel();
        GameController gameController = new GameController(gameModel);
        RMIServer RMIServerCreator = new RMIServer(serverPort, gameController, catchAddress);
        remoteServer = RMIServerCreator.startRMIServer();

        new Thread(() -> {
            while (true) {
                try {
                    remoteServer.pingAllClient();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
