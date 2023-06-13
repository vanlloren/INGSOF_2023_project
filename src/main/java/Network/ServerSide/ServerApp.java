package Network.ServerSide;

import server.Controller.GameController;
import server.Model.GameModel;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

//classe che rappresenta il server vero e proprio: gestisce i client e dialoga direttamente con model e controller
public class ServerApp {

    private static final PrintStream out = System.out;
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args){
        //qui ci sarà il SocketServer

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


        out.println("Welcome to MyShelfie, you're about to create a new Server which will run MyShelfie!");
        out.println("Please, decide on which port you would like to run the RMIServer:");
        int serverPort = scanner.nextInt();
        GameModel gameModel = new GameModel();
        GameController gameController = new GameController(gameModel);
        RMIServer RMIServerCreator = new RMIServer(serverPort, gameController);
        RemoteServerImplementation remoteServer = RMIServerCreator.startRMIServer();
        gameController.setRemoteServer(remoteServer);







        //stessa cosa per creare il SocketServer
    }

}
