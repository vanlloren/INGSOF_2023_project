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
}
