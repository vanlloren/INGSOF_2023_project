package Observer;
import java.util.List;
import java.util.Map;
public abstract class ViewObserver {

    /**
     * Create a new connection to the server with the updated info.
     *
     * @param serverInfo a map of server address and server port.
     */
     void onUpdateServerInfo(Map<String, String> serverInfo){

     };

    /**
     * Sends a message to the server with the updated nickname.
     *
     * @param nickname the nickname to be sent.
     */
     void onUpdateNickname(String nickname){

     }

    /**
     * Sends a message to the server with the updated choice of keep picking tiles.
     *
     * @param choice the choice to be sent.
     */
     void onUpdateAskKeepPicking( String choice){

     }
    /**
     * Sends a message to the server with the player number chosen by the user.
     *
     * @param playersNumber the number of players.
     */
   void onUpdatePlayersNumber(int playersNumber){

   }

    /**
     * Sends a message to the server with the nickname of the first player chosen by the user.
     *
     * @param nickname the nickname of the first player.
     */
    void onUpdateFirstPlayer(String nickname){

    }

    /**
     * Handles a disconnection wanted by the user.
     * (e.g. a click on the back button into the GUI).
     */
    void onDisconnection(){

    };
}
