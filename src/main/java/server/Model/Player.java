package server.Model;


import Observer.PlayerObservable;
import Util.CommonGoalType;
import client.view.TurnView;

import java.util.Vector;

public class Player extends PlayerObservable {
    private String nickname;
    private Integer points;
    private Shelf personalShelf = new Shelf();
    private PersonalGoal personalGoal = new PersonalGoal();
    private LivingRoom livingRoom;
    private boolean hasCommonGoal1;
    private boolean hasCommonGoal2;
    private boolean hasPersonalGoal;

    // costruttore player in cui passo i parametri principali passati dal controller che chiamerà dopo la ricezione di tutti i nickname da lato client
    public Player (String nickname, TurnView turnView){
        this.nickname = nickname;
        this.personalShelf.addObserver(turnView);
    }



    // questi tre metodi vengono chiamati dal controller quando il player ha soddisfatto gli obbiettivi carte

    public void setStatusCommonGoal1(){
        this.hasCommonGoal1 = true;
        //notifica che la commmongoal è stata soddisfatta
    }

    public void setStatusCommonGoal2(){
        this.hasCommonGoal2 = true;
        //notifica che la commonGoal2 è stata soddisfatta
    }




    //metodo che verra chiamato dal controller il quale preventivamente crea una living room adatta al numero di giocatori che si sono collegati e assegna a tutti la STESSA LIVING ROOM
    public void setLivingRoom(LivingRoom livingRoom){
        this.livingRoom= livingRoom;
    }

    public Shelf getPersonalShelf(){

        return this.personalShelf;
    }
    public PersonalGoal getPersonalGoal(){
        return this.personalGoal;
    }

    public String getNickname(){
        return this.nickname;}

    public void setNickname (String nickname){ //il controller chiama questo metodo per aggiornare il valore dei nickName
        this.nickname= nickname;

    }

    public Integer getPoints(){
        return this.points;
    }




    public void setPersonalGoal(PersonalGoal personalGoal){ // questo viene propriamente inizializzato dal player mentre i commongoal appartengono alla living room
     this.personalGoal = personalGoal;
    }

    public boolean getHasCommonGoal1(){
        return this.hasCommonGoal1;
    }
    public boolean getHasCommonGoal2(){
        return this.hasCommonGoal2;
    }
    public boolean getHasPersonalGoal(){
        return this.hasPersonalGoal;
    }
    public void insertTile(int x, int y, PlayableItemTile tile) {
        int i = 0;
        Vector<Integer> position= new Vector<Integer>();
        this.personalShelf.putTile(x,y,tile,i,position); //chiedi alfi come funziona questo metodo
        //metodo che gestisce inserimento in libreria
    }
}











