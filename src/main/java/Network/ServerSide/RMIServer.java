package Network.ServerSide;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//
public class RMIServer {
    private final Server server;
    private final int serverRMIPort;

    RMIServer(Server server, int port){
        this.server = server;
        this.serverRMIPort = port;
    }

    public void startRMIServer(){
        try{
            RemoteServerImplementation remoteServer = new RemoteServerImplementation(server);
            Registry registry = LocateRegistry.createRegistry(serverRMIPort);
            registry.bind("MyShelfieServer", remoteServer);
        }catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
