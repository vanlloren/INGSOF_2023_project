package Network.ServerSide;

import Network.message.InsertionReplyMessage;
import Network.message.Message;
import Network.message.TileReplyMessage;
import server.Model.PlayableItemTile;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//interfaccia inviata al client per comunicare col server

public interface RemoteServerInterface extends Remote {

    //andranno inseriti tutti i metodi che il server mette
    //a disposizione del client perché li invochi

    void onMessage(Message message) throws RemoteException;

    void disconnect() throws RemoteException;

    TileReplyMessage onTilePickMessage(String nickname, int x, int y) throws RemoteException;

    InsertionReplyMessage ToPutTileRequestMessage(int xPos, int yPos, PlayableItemTile tile, int numOfTiles, ArrayList<PlayableItemTile> turnTiles) throws  RemoteException;
    InsertionReplyMessage ToPutTileRequestMessage(int xPos, PlayableItemTile tile, int numOfTiles, ArrayList<PlayableItemTile> turnTiles) throws  RemoteException;

}
