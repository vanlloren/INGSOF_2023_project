package Network.ServerSide;

import Network.message.InsertionReplyMessage;
import Network.message.Message;
import Network.message.TileReplyMessage;
import server.Model.PlayableItemTile;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * This Class is the {@link Network.ServerSide.RemoteServerImplementation RemoteServer} Interface sent
 * to the {@link Network.ClientSide.RemoteClientImplementation RemoteClients} as a stub.
 * Through this Interface the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} can invoke
 * the available methods of the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
 * using the RMI Communication Protocol
 */
public interface RemoteServerInterface extends Remote {

    /**
     * This method allows a {@link Network.ClientSide.RemoteClientImplementation RemoteClient} to
     * send any tipe of {@link Message Message} to the {@link RemoteServerImplementation RemoteServer}
     *
     * @param message the {@link Message Message} to be sent
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void onMessage(Message message) throws RemoteException;

    /**
     * This method allows a {@link Network.ClientSide.RemoteClientImplementation RemoteClient} to
     * verify if the {@link RemoteServerImplementation RemoteServer} is still on and running
     *
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    void verifyStillConnected() throws RemoteException;

    /**
     * This method allows a {@link Network.ClientSide.RemoteClientImplementation RemoteClient} to
     * ask the {@link RemoteServerImplementation RemoteServer} to pick a {@link PlayableItemTile PlayableItemTile}
     * from the {@link server.Model.LivingRoom LivingRoom}
     *
     * @param nickname a {@link String String} containing the {@code nickname} of the {@link server.Model.Player Player}
     *                 that is asking to pick a {@link server.Model.PlayableItemTile PlayableItemTile}
     * @param x the 'x' position in the {@link server.Model.LivingRoom LivingRoom} of
     *          the {@link PlayableItemTile PlayableItemTile} to pick in the
     *          {@link server.Model.LivingRoom LivingRoom}
     * @param y the 'y' position in the {@link server.Model.LivingRoom LivingRoom} of
     *          the {@link PlayableItemTile PlayableItemTile} to pick
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    TileReplyMessage onTilePickMessage(String nickname, int x, int y) throws RemoteException;

    /**
     * This method allows a {@link Network.ClientSide.RemoteClientImplementation RemoteClient} to
     * ask the {@link RemoteServerImplementation RemoteServer} to put the first picked {@link PlayableItemTile PlayableItemTile}
     * into the {@link server.Model.Shelf Shelf}
     *
     * @param xPos the 'x' position in the {@link server.Model.Shelf Shelf} of
     *          the {@link PlayableItemTile PlayableItemTile} will be put
     * @param yPos the 'y' position in the {@link server.Model.Shelf Shelf} where the
     *          {@link PlayableItemTile PlayableItemTile} will be put
     * @param tile the {@link PlayableItemTile PlayableItemTile} to put into the {@link server.Model.Shelf Shelf}
     * @param numOfTiles the total number of {@link PlayableItemTile PlayableItemTiles} to put into the {@link server.Model.Shelf Shelf}
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    InsertionReplyMessage ToPutTileRequestMessage(int xPos, int yPos, PlayableItemTile tile, int numOfTiles) throws  RemoteException;

    /**
     * This method allows a {@link Network.ClientSide.RemoteClientImplementation RemoteClient} to
     * ask the {@link RemoteServerImplementation RemoteServer} to put the second or third picked {@link PlayableItemTile PlayableItemTile}
     * into the {@link server.Model.Shelf Shelf}
     *
     * @param xPos the 'x' position in the {@link server.Model.Shelf Shelf} of
     *          the {@link PlayableItemTile PlayableItemTile} will be put
     * @param tile the {@link PlayableItemTile PlayableItemTile} to put into the {@link server.Model.Shelf Shelf}
     * @param numOfTiles the total number of {@link PlayableItemTile PlayableItemTiles} to put into the {@link server.Model.Shelf Shelf}
     * @throws RemoteException exception that underlines problems with the RMI Connection
     */
    InsertionReplyMessage ToPutTileRequestMessage(int xPos, PlayableItemTile tile, int numOfTiles) throws  RemoteException;

}