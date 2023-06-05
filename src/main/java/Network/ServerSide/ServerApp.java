package Network.ServerSide;

import server.Controller.GameController;
import server.Model.GameModel;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

//classe che rappresenta il server vero e proprio: gestisce i client e dialoga direttamente con model e controller
public class ServerApp {

    private static final PrintStream out = System.out;
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args){
        //qui ci sarà il SocketServer

        try {
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
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
