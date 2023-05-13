package server.Model;


import Observer.PlayerObservable;

import Observer.PlayerObserver;
import client.view.TurnView;
import server.Controller.RuleShelf;

import java.util.Vector;

public class Player extends PlayerObservable {
    private String nickname;

    private int maxTiles = 6;
    private Integer points;
    private final Shelf personalShelf = new Shelf();
    private RuleShelf ruleShelf;
    private final PersonalGoal personalGoal = new PersonalGoal();
    private boolean hasCommonGoal1;
    private boolean hasCommonGoal2;

    private boolean endgame = false;

    // costruttore player in cui passo i parametri principali passati dal controller che chiamerà dopo la ricezione di tutti i nickname da lato client
    public Player (String nickname, TurnView turnView){
        this.nickname = nickname;
        this.personalShelf.addObserver(turnView);
        this.personalGoal.addObserver(turnView);
    }

    public int getMaxTiles(){
        return this.maxTiles;
    }


    // questi tre metodi vengono chiamati dal controller quando il player ha soddisfatto gli obbiettivi carte

    public void setStatusCommonGoal1(){
        this.hasCommonGoal1 = true;
        notifyObservers(PlayerObserver::OnUpdateModelStatusCommonGoal1);
    }

    public void setStatusCommonGoal2(){
        this.hasCommonGoal2 = true;
        notifyObservers(PlayerObserver::OnUpdateModelStatusCommonGoal2);
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

    public void setPoints(Integer points) {
        this.points = points;
        notifyObservers(obs -> obs.OnUpdateModelPlayerPoint(points));
    }
    public boolean getEndgame(){
        return this.endgame;
    }
    public void setEndgame(boolean endgame){
        this.endgame = personalShelf.isFull();
        notifyObservers(obs ->obs.OnUpdateModelPlayerEndGame(endgame));
    }

    public boolean getHasCommonGoal1(){
        return this.hasCommonGoal1;
    }
    public boolean getHasCommonGoal2(){
        return this.hasCommonGoal2;
    }

    public void insertTile(int x, int y, PlayableItemTile tile) {
        int i = 0;
        Vector<Integer> position= new Vector<>();
        this.personalShelf.putTile(x,y,tile);
        maxTiles = ruleShelf.freeCellsInShelf(personalShelf.getStructure());
        //chiedi alfi come funziona questo metodo
        //metodo che gestisce inserimento in libreria
    }
}











