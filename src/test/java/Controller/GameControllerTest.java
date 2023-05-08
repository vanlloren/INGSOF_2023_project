package Controller;

import Util.Colour;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
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

        structure[0][0] = new PlayableItemTile("PINK");
        structure[0][1] = new PlayableItemTile("PINK");
        structure[0][2] = new PlayableItemTile("PINK");
        structure[1][0] = new PlayableItemTile("PINK");
        structure[1][1] = new PlayableItemTile("PINK");
        structure[2][5] = new PlayableItemTile("CYAN");
    }
    @Test
    public void emptyShelftest() {
        Player player = new Player();
        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(player);
        System.out.println(result);
    }
    @Test
    public void singleobjShelftest(){
        Player player = new Player();
        PlayableItemTile[][] structure = new PlayableItemTile[6][5];
        structure[0][0] = new PlayableItemTile("PINK");
        player.getPersonalShelf().setStructure(structure);
        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(player);
        System.out.println(result);

    }
    @Test
    public void adjobjShelftest(){
        Player player = new Player();
        PlayableItemTile[][] structure = new PlayableItemTile[6][5];
        structure[0][0] = new PlayableItemTile("PINK");
        structure[0][1] = new PlayableItemTile("PINK");
        structure[0][2] = new PlayableItemTile("PINK");
        player.getPersonalShelf().setStructure(structure);
        HashMap<Colour, ArrayList<Integer>> result = findAdjGroups(player);
        System.out.println(result);

    }
    @Test
    public void emptyArraytest(){
        PlayableItemTile[][] structure = new PlayableItemTile[0][0];
        boolean[][] visited = new boolean[0][0];
        int result = findAdjGroupDim(structure, visited, 0, 0, "PINK", new ArrayList<>());
        System.out.println(result);  // Output: 0

    }
    @Test
    public void singleobjArraytest(){
        PlayableItemTile[][] structure = new PlayableItemTile[1][1];
        structure[0][0] = new PlayableItemTile("PINK");
        boolean[][] visited = new boolean[1][1];
        int result = findAdjGroupDim(structure, visited, 0, 0, "PINK", new ArrayList<>());
        System.out.println(result);  // Output: 1

    }
    @Test
    public void groupobjArraytest(){
        PlayableItemTile[][] structure = new PlayableItemTile[3][3];
        structure[0][0] = new PlayableItemTile("PINK");
        structure[0][1] = new PlayableItemTile("PINK");
        structure[1][0] = new PlayableItemTile("PINK");
        structure[1][1] = new PlayableItemTile("PINK");
        boolean[][] visited = new boolean[3][3];
        int result = findAdjGroupDim(structure, visited, 0, 0, "PINK", new ArrayList<>());
        System.out.println(result);  // Output: 4

    }

}