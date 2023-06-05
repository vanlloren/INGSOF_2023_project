package Util;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Network.ClientSide.RemoteClientImplementation;
import Network.ClientSide.RemoteClientInterface;
import Network.message.Message;
import Util.Colour;
import Util.CommonGoalType;
import Util.PersonalGoalType;
import client.view.TUI;
import client.view.TurnView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import server.Model.CommonGoal;
import server.Model.GameModel;

public class RandChairOwnerTest {
    @Test
    public void testRandChairOwner(){
        int i = RandChairOwner.ChooseRand(2);
        Assert.assertTrue(i < 2);

    }
}
