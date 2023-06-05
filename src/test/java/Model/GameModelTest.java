package Model;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Network.ClientSide.RemoteClientImplementation;
import Network.ClientSide.RemoteClientInterface;
import Network.message.Message;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TUI;
import client.view.TurnView;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;


import server.Model.*;


public class GameModelTest {
    private GameModel model;

    @BeforeEach
    public void setup(){
        GameModel model = new GameModel();
        this.model=model;
    }

    @Test
    public void testIsNicknameAvailable(){


        try {
            model.getPlayersInGame().add(new Player("Lorenzo1", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel()));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        Assert.assertFalse(model.isNicknameAvailable("Lorenzo1"));
        Assert.assertTrue(model.isNicknameAvailable("lorenzo1"));
    }

    @Test
    public void testSetPlayersInGame(){
        try {
            Player player = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());

            RemoteClientImplementation client = new RemoteClientImplementation("localhost", 1099, new TUI());
            model.addObserver(client);
            model.getMyShelfie().setItemBag();
            model.getMyShelfie().setLivingRoom(4);
            model.setPlayersInGame(player);

            Assert.assertEquals(player, model.getPlayersInGame().get(0));

            model.setPlayersNumber(4);

            Player player2 = new Player("lorenzo2", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            Player player3 = new Player("lorenzo3", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            Player player4 = new Player("lorenzo4", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());

            model.setPlayersInGame(player2);
            model.setPlayersInGame(player3);
            model.setPlayersInGame(player4);

            Assert.assertEquals(player2, model.getPlayersInGame().get(1));
            Assert.assertEquals(player3, model.getPlayersInGame().get(2));
            Assert.assertEquals(player4, model.getPlayersInGame().get(3));

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSetEndGame(){
        RemoteClientImplementation client = null;
        try {
            client = new RemoteClientImplementation("localhost", 1099, new TUI());
            model.getMyShelfie().setItemBag();
            model.getMyShelfie().setLivingRoom(4);
            Player player = new Player("lorenzo", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            model.setPlayersInGame(player);

            Assert.assertEquals(player, model.getPlayersInGame().get(0));

            model.setPlayersNumber(4);

            Player player2 = new Player("lorenzo2", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            Player player3 = new Player("lorenzo3", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());
            Player player4 = new Player("lorenzo4", new RemoteClientImplementation("localhost", 1099, new TUI()), new GameModel());

            model.setPlayersInGame(player2);
            model.setPlayersInGame(player3);
            model.setPlayersInGame(player4);

            Assert.assertEquals(player2, model.getPlayersInGame().get(1));
            Assert.assertEquals(player3, model.getPlayersInGame().get(2));
            Assert.assertEquals(player4, model.getPlayersInGame().get(3));
            model.addObserver(client);

            model.setCurrPlayer(player2);

            model.setEndGame();

            Assert.assertTrue(model.getEndGame());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }
}
