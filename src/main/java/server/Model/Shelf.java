package server.Model;


import Observer.ShelfObservable;
import server.Controller.RuleShelf;

import java.util.Vector;


public class Shelf extends ShelfObservable {

    private RuleShelf ruleShelf = new RuleShelf();
    private PlayableItemTile[][] structure = new PlayableItemTile[5][4];

    public PlayableItemTile[][] getStructure(){
        return this.structure;
    }

    public PlayableItemTile getShelfTile(int x ,int y){
        return this.structure[x][y];
    }

    public PlayableItemTile[][] getShelfie() {return this.structure;}

    public PlayableItemTile[][] setUpPersonalShelf(){
        for (int i= 0;i<6;i++){
            for ( int j=0;j<5;j++){
                this.structure[i][j] = new PlayableItemTile("VOID", 0);
            }
        }
        return this.structure;
    }

    public void putTile(int x, int y, PlayableItemTile Tile){
        this.structure[x][y] = Tile;

        notifyObservers(obs-> {
            obs.onUpdatePuttedTileIntoShelf(x,y,Tile);
        });
    }
}