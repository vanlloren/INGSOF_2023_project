
package Model;
import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Network.ClientSide.RemoteClientImplementation;
import Network.ClientSide.RemoteClientInterface;
import Network.message.Message;
import Observer.PlayerObserver;
import Util.Colour;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TUI;
import client.view.TurnView;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import server.Model.GameModel;
import server.Model.Player;

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
            Assert.assertTrue(player.getHasCommonGoal1());
            Assert.assertTrue(player.getHasCommonGoal2());

            player.setNickname("lorenzo2");
            Assert.assertTrue(player.getNickname().equals("lorenzo2"));

            player.setPoints(5);
            Assert.assertTrue(player.getPoints() == 5);


        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}