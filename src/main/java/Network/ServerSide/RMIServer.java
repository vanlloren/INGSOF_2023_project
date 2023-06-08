package Network.ServerSide;


import server.Controller.GameController;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.InetAddress;
import java.net.UnknownHostException;

//
public class RMIServer {
    private final int serverRMIPort;
    private final GameController gameController;


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
            try {
                // Ottieni il riferimento al Registro RMI
                Registry registry2 = LocateRegistry.getRegistry(1099);

                // Ottieni la lista dei nomi registrati nel Registro RMI
                String[] registeredNames = registry.list();

                if (registeredNames.length > 0) {
                    System.out.println("Nomi registrati nel Registro RMI:");
                    for (String name : registeredNames) {
                        System.out.println(name);
                    }
                } else {
                    System.out.println("Nessun nome registrato nel Registro RMI.");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            try {
                InetAddress localhost = InetAddress.getLocalHost();
                String hostname = localhost.getHostName();
                String ipAddress = localhost.getHostAddress();

                System.out.println("Hostname: " + hostname);
                System.out.println("Indirizzo IP: " + ipAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            return remoteServer;
        }catch (AlreadyBoundException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
