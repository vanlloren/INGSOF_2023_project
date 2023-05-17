package server.Model;


import Observer.ShelfObservable;
import client.view.TurnView;
import server.Controller.RuleShelf;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;
import Util.Colour;

public class Shelf extends ShelfObservable implements Serializable, SimpleShelf {
    private int columnChosen;
    private int pointsAdj = 0;
    private GameModel gameModel;


    @Serial
    private static final long serialVersionUID = -5591053634616843792L;
    private RuleShelf ruleShelf = new RuleShelf();
    private PlayableItemTile[][] structure = new PlayableItemTile[5][4];

    public Shelf(GameModel gameModel){
        this.gameModel = gameModel;
    }

    public PlayableItemTile[][] getStructure(){
        return this.structure;
    }

    public PlayableItemTile getShelfTile(int x ,int y){
        return this.structure[x][y];
    }

    public PlayableItemTile[][] getShelfie() {return this.structure;}

    public int getPointsAdj() {
        return this.pointsAdj;
    }

    public void setPointsAdj(int pointsAdj) {
        this.pointsAdj = pointsAdj;
    }


    public PlayableItemTile[][] setUpPersonalShelf(){
        for (int i= 0;i<6;i++){
            for ( int j=0;j<5;j++){
                this.structure[i][j] = new PlayableItemTile("VOID", -1);
            }
        }
        return this.structure;
    }

    public void putTile(int x, int y, PlayableItemTile Tile){
        this.structure[x][y] = Tile;
        notifyObservers(obs-> {
            obs.onUpdatePuttedTileIntoShelf(new TurnView(gameModel), x,y,Tile);
        });
    }
    public int freeCellsInShelf(){
        //Useful method to check also the maximum number of tiles that could be picked in the livingRoom
        List<Integer> list = new Vector<Integer>();
        int count=0;
        for(int j= 0; j<5; j++){
            for(int i= 0; j<6; j++){
                if(this.structure[i][j].getIdCode() !=0){
                    count++;
                }
            }
            list.add(count);
        }
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int valore = list.get(i);
            if (valore > max) {
                max = valore;
            }
        }
        return max;
    }
    public boolean isFull() {
        for (int j = 0; j < 5; j++) {
            if (this.structure[0][j].getColour() == Colour.VOID) {
                return false;
            }
        }
        return true;
    }

    public  int getColumnChosen(){
        return this.columnChosen;
    }
    public void setColumnChosen(int columnChosen){
        this.columnChosen = columnChosen;
    }
}