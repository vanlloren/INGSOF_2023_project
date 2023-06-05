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
public class CommonGoalTest {

    @Test
    public void testSetTokens(){
        CommonGoal commonGoal = new CommonGoal(new GameModel());
        commonGoal.setTokens(2);
        ArrayList<Integer> token_list = new ArrayList<>();
        token_list.add(4);
        token_list.add(8);
        Assert.assertEquals(token_list, commonGoal.getToken_list());


        commonGoal = new CommonGoal(new GameModel());
        commonGoal.setTokens(3);
        token_list = new ArrayList<>();
        token_list.add(4);
        token_list.add(6);
        token_list.add(8);
        Assert.assertEquals(token_list, commonGoal.getToken_list());


        commonGoal = new CommonGoal(new GameModel());
        commonGoal.setTokens(4);
        token_list = new ArrayList<>();
        token_list.add(2);
        token_list.add(4);
        token_list.add(6);
        token_list.add(8);
        Assert.assertEquals(token_list, commonGoal.getToken_list());
    }
}
