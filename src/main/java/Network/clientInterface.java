package Network;

import Network.message.Message;
import Observer.Observable;

import java.util.logging.Logger;

public abstract class clientInterface extends Observable {
    public static final Logger LOGGER = Logger.getLogger(clientInterface.class.getName());

    /**
     * Sends a message to the server.
     *
     * @param message the message to be sent.
     */
    public abstract void sendMessage(Message message);

    /**
     * Asynchronously reads a message from the server and notifies the ClientController.
     */
    public abstract void readMessage();

}
