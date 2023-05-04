package Network.ServerSide;

import server.Controller.GameController;
import server.Model.GameModel;

import java.io.PrintStream;
import java.util.Scanner;

//classe che rappresenta il server vero e proprio: gestisce i client e dialoga direttamente con model e controller
public class ServerApp {

    private static final PrintStream out = System.out;
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        RMIServer RemoteServer;
        //qui ci sarà il SocketServer

        out.println("Welcome to MyShelfie, you're about to create a new Server which will run MyShelfie!");
        out.println("Please, decide on which port you would like to run the RMIServer:");
        int serverPort = scanner.nextInt();
        GameModel gameModel = new GameModel();
        GameController gameController = new GameController(gameModel);
        RMIServer remoteServer = new RMIServer(serverPort, gameController);
        remoteServer.startRMIServer();


        //stessa cosa per creare il SocketServer
    }

}
