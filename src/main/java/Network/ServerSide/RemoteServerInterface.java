package Network.ServerSide;

import java.rmi.Remote;

//interfaccia inviata al client per comunicare col server

public interface RemoteServerInterface extends Remote {

    //andranno inseriti tutti i metodi che il server mette
    //a disposizione del client perché li invochi
}
