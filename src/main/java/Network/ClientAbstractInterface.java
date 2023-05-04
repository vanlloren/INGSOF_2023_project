package Network;

import Network.message.Message;
import Observer.Observable;
import java.util.logging.Logger;
/*
Classe astratta per la gestione della comunicazione tra server e client.
Tecnicamente ogni tipo di comunicazione deve comprendere tale classe.
 */
public abstract class ClientAbstractInterface extends Observable {
    public static final Logger LOGGER = Logger.getLogger(ClientAbstractInterface.class.getName());
    public abstract void sendMessage(Message message);
    public abstract void readMessage();

}
