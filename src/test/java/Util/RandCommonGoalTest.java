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

public class RandCommonGoalTest {


    @Test
    public void testSetType(){
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                if(i!=j){
                    CommonGoal commonGoal1 = new CommonGoal(new GameModel());
                    CommonGoal commonGoal2 = new CommonGoal(new GameModel());
                    RandCommonGoal.setType(commonGoal1, commonGoal2, i, j);
                    Assert.assertNotEquals(commonGoal1.getCommonGoalType(), commonGoal2.getCommonGoalType());
                }
            }
        }

        CommonGoal commonGoal1 = new CommonGoal(new GameModel());
        CommonGoal commonGoal2 = new CommonGoal(new GameModel());
        RandCommonGoal.setType(commonGoal1, commonGoal2, 0, 0);
        Assert.assertNotEquals(commonGoal1.getCommonGoalType(), commonGoal2.getCommonGoalType());

    }
}
