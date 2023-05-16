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

    private ProxyObserver proxyObserver;

    RMIServer(int port, GameController gameController, ProxyObserver proxyObserver){
        this.serverRMIPort = port;
        this.gameController = gameController;
        this.proxyObserver = proxyObserver;
    }

    public RemoteServerImplementation startRMIServer(){
        try{
            RemoteServerImplementation remoteServer = new RemoteServerImplementation(this, gameController, proxyObserver);
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
