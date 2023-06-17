
package Model;

import Network.ClientSide.RemoteClientImplementation;
import client.view.TUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.Model.GameModel;
import server.Model.Player;

import java.rmi.RemoteException;

public class PlayerTest {
    @Test
    public void testNotify(){

        try {
            RemoteClientImplementation client = new RemoteClientImplementation("localhost", 1099, new TUI());
            GameModel gameModel = new GameModel();
            Player player = new Player("lorenzo1", client, gameModel);
            player.addObserver(client);


            gameModel.setCurrPlayer(player);
            player.setStatusCommonGoal2();
            player.setStatusCommonGoal1();
            Assertions.assertTrue(player.getHasCommonGoal1());
            Assertions.assertTrue(player.getHasCommonGoal2());

            player.setNickname("lorenzo2");
            Assertions.assertEquals("lorenzo2", player.getNickname());

            player.setPoints(5);
            Assertions.assertEquals(5, (int) player.getPoints());


        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}