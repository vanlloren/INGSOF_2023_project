package Network.ServerSide;

import client.view.TurnView;
import server.Controller.GameController;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//
public class RMIServer {
    private final int serverRMIPort;
    private GameController gameController;

    private TurnView turnView;

    RMIServer(int port, GameController gameController, TurnView turnView){
        this.serverRMIPort = port;
        this.gameController = gameController;
        this.turnView = turnView;
    }

    public RemoteServerImplementation startRMIServer(){
        try{
            RemoteServerImplementation remoteServer = new RemoteServerImplementation(this, gameController, turnView);
            Registry registry = LocateRegistry.createRegistry(serverRMIPort);
            registry.bind("MyShelfieServer", remoteServer);
            System.out.println("Server RMI pronto");
            return remoteServer;
        }catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
