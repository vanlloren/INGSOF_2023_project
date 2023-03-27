package Model;

import org.example.InvalidPlayableItemTileColourException;
import org.example.PlayableItemTile;
import org.example.PlayableItemTileFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//SEQUENZA CORRETTA GAMEBOARD<-->LIVINGROOM:
//-creazione
//-fillLivingRoom (al suo interno avrà multiple chiamate di getNextInGameTile e putNextInGameTile)
//-updateAvailability
//----1° turno----
//-hasAdjacentTiles--->fillLivingRoom--->updateAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-pickedTilesNum
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-pickedTilesNum
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----
//-hasAdjacentTiles--->fillLivingRoom--->updateAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----









<<<<<<< HEAD


public class ItemBag(){

    private List<PlayableItemTile> bag;



    public void putTiles(){
        bag = new ArrayList<PlayableItemTile>();
        PlayableItemTileFactory factory = new PlayableItemTileFactory();

        for(int i=0; i<132; i++){
            if(i<22){
                try {
                    bag.add(factory.createPlayableItemTile("GREEN", i + 1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<44){
                try{
                    bag.add(factory.createPlayableItemTile("WHITE", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<66){
                try{
                    bag.add(factory.createPlayableItemTile("YELLOW", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<88){
                try{
                    bag.add(factory.createPlayableItemTile("BLUE", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<110){
                try{
                    bag.add(factory.createPlayableItemTile("CYAN", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }else if(i<132){
                try{
                    bag.add(factory.createPlayableItemTile("PINK", i+1));
                }catch(InvalidPlayableItemTileColourException exc){

                }
            }
        }
    }

    //dovrebbe effettuare pick random di ItemTile da ItemBag
    public PlayableItemTile randPickTile(){
        final Random RAND = new Random();
        int index = RAND.nextInt(bag.size());
        PlayableItemTile helperTile = bag.get(index);
        bag.remove(index);
        return helperTile;
    }
}



<<<<<<< HEAD



