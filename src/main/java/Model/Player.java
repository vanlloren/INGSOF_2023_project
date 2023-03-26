package Model;

import org.example.CommonGoal;
import org.example.LivingRoom;
import org.example.PersonalGoal;
import org.example.Shelf;

public class Player {
    private String nickname;
    private int points;
    private Shelf personalShelf = new Shelf();
    private PersonalGoal personalGoal;
    private org.example.CommonGoal commonGoal1 = new org.example.CommonGoal();
    private org.example.CommonGoal commonGoal2 = new org.example.CommonGoal();
    private org.example.LivingRoom livingRoom;
    private boolean hasCommonGoal1=false;
    private boolean hasCommonGoal2=false;


    public org.example.LivingRoom setLivingRoom(LivingRoom livingRoom){ // metodo importante che serve ad assegnare ai giocatori la stessa plancia di gioco nel caso ci siano partite multiple da gestire
        this.livingRoom= livingRoom;



    }

    public Shelf getPersonalShelf(){
        return this.personalShelf;
    }

    public String getName(){
        return this.nickname;}


// va nel controller
    public void calculatePoint(){
        if(!hasCommonGoal1&&commonGoal1.checkGoal()){

            this.points=this.points+commonGoal1.getPoint();
            hasCommonGoal1=true;

        }
        if(!hasCommonGoal2&&commonGoal2.checkGoal()) {
            this.points = this.points + commonGoal2.getPoint();
            hasCommonGoal2 = true;
            }
            if(personalGoal){
                this.points=this.points+personalGoal.getPoint();


            }}
    //
    public void setPersonalGoal(){ // questo viene propriamente inizializzato dal player mentre i commongoal appartengono alla living room
        personalGoal = new PersonalGoal();
        //funzione che assegna personalgoal//
        return personalGoal;
    }

    public void setCommonGoals(){
        commonGoal1 = livingRoom.getCommonGoal1();
        commonGoal2 = livingRoom.getCommonGoal2();
    }

    public org.example.CommonGoal getCommonGoal1(){
       return commonGoal1;

    }

    public CommonGoal getCommonGoal2(){
        return commonGoal2;
    }
    //controller
    public void insertTile(int x,int y) throws NotValidCoordinate,Notavailable{
        //metodo che gestisce inserimento in libreria
    }
    //
}


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








<<<<<<< HEAD



