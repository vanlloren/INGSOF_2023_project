package Controller;

import Util.Colour;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import server.Model.PlayableItemTile;
import server.Model.Player;


import java.util.ArrayList;
import java.util.HashMap;

import static server.Controller.GameController.findAdjGroupDim;
import static server.Controller.GameController.findAdjGroups;


public class GameControllerTest extends TestCase {
    private PlayableItemTile[][] structure;

    @BeforeEach
    public void initialize() {

        //expected PINK=5
        structure[0][0] = new PlayableItemTile("PINK");
        structure[0][1] = new PlayableItemTile("PINK");
        structure[0][2] = new PlayableItemTile("PINK");
        structure[1][0] = new PlayableItemTile("PINK");
        structure[1][1] = new PlayableItemTile("PINK");
        //expected CYAN = 3 && 1
        structure[2][5] = new PlayableItemTile("CYAN");
        structure[2][6] = new PlayableItemTile("CYAN");
        structure[1][6] = new PlayableItemTile("CYAN");
        structure[6][6] = new PlayableItemTile("CYAN");
        //expected WHITE=2
        structure[5][5] = new PlayableItemTile("WHITE");
        structure[4][5] = new PlayableItemTile("WHITE");
        //expected YELLOW=0
        //expected GREEN=0
        //expected BLUE=0
    }
    @Test
    public void emptyShelfTest() {
        PlayableItemTile[][] structure1 = new PlayableItemTile[6][5];
        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure1);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        Assertions.assertEquals(result, compare);
    }
    @Test
    public void singleobjtest(){
        PlayableItemTile[][] structure1 = new PlayableItemTile[6][5];
        structure1[0][0] = new PlayableItemTile("PINK");
        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure1);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        compare.put(Colour.PINK, list);
        Assertions.assertEquals(compare, result);
    }
    @Test
    public void groupObjTest3(){
        PlayableItemTile[][] structure1 = new PlayableItemTile[6][5];
        structure1[0][0] = new PlayableItemTile("PINK");
        structure1[0][1] = new PlayableItemTile("PINK");
        structure1[0][2] = new PlayableItemTile("PINK");

        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure1);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        ArrayList<Integer> list1 = new ArrayList<>();

        list1.add(3);
        compare.put(Colour.PINK, list1);

        Assertions.assertEquals(compare, result);

    }
    @Test
    public void groupObjTest4(){
        PlayableItemTile[][] structure1 = new PlayableItemTile[6][5];
        structure1[0][0] = new PlayableItemTile("PINK");
        structure1[0][1] = new PlayableItemTile("PINK");
        structure1[1][0] = new PlayableItemTile("PINK");
        structure1[1][1] = new PlayableItemTile("PINK");

        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure1);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        compare.put(Colour.PINK, list);
        Assertions.assertEquals(compare, result);
    }

    @Test
    public void generalTest(){
        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(structure);
        HashMap<Colour, ArrayList<Integer>> compare = new HashMap<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();

        list2.add(3);
        list2.add(1);
        list3.add(2);
        list1.add(5);
        compare.put(Colour.PINK, list1);
        compare.put(Colour.CYAN, list2);
        compare.put(Colour.WHITE, list3);
        Assertions.assertEquals(compare, result);

    }

}