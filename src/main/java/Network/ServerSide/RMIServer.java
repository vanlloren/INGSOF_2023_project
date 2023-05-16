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


    RMIServer(int port, GameController gameController){
        this.serverRMIPort = port;
        this.gameController = gameController;
    }

    public RemoteServerImplementation startRMIServer(){
        try{
            RemoteServerImplementation remoteServer = new RemoteServerImplementation(this, gameController);
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
