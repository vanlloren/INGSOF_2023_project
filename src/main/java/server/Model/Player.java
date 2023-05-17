package server.Model;


import Network.ClientSide.RemoteClientInterface;
import Observer.PlayerObservable;

import Observer.PlayerObserver;
import client.view.TurnView;
import server.Controller.RuleShelf;

import java.io.Serial;
import java.io.Serializable;

public class Player extends PlayerObservable implements Serializable, SimplePlayer {
    @Serial
    private static final long serialVersionUID = 1303503671022200446L;
    private String nickname;

    private int maxTiles = 6;
    private Integer points;
    private final Shelf personalShelf;
    private RuleShelf ruleShelf;
    private GameModel gameModel;
    private final PersonalGoal personalGoal;
    private boolean hasCommonGoal1;
    private boolean hasCommonGoal2;

    private boolean endgame = false;

    // costruttore player in cui passo i parametri principali passati dal controller che chiamerà dopo la ricezione di tutti i nickname da lato client
    public Player (String nickname, RemoteClientInterface client, GameModel gameModel){
        this.nickname = nickname;
        this.gameModel = gameModel;
        this.personalShelf = new Shelf(gameModel);
        this.personalShelf.addObserver(client);
        this.personalGoal = new PersonalGoal(gameModel);
        this.personalGoal.addObserver(client);
    }

    public int getMaxTiles(){
        return this.maxTiles;
    }


    // questi tre metodi vengono chiamati dal controller quando il player ha soddisfatto gli obbiettivi carte

    public void setStatusCommonGoal1(){
        this.hasCommonGoal1 = true;
        notifyObservers(obs -> obs.OnUpdateModelStatusCommonGoal1(new TurnView(gameModel)));
    }

    public void setStatusCommonGoal2(){
        this.hasCommonGoal2 = true;
        notifyObservers(obs -> obs.OnUpdateModelStatusCommonGoal2(new TurnView(gameModel)));
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
        notifyObservers(obs -> obs.OnUpdateModelPlayerPoint(new TurnView(gameModel), points));
    }
    public boolean getEndgame(){
        return this.endgame;
    }
    public void setEndgame(boolean endgame){
        this.endgame = personalShelf.isFull();
        notifyObservers(obs ->obs.OnUpdateModelPlayerEndGame(new TurnView(gameModel), endgame));
    }
    public boolean getHasCommonGoal1(){
        return this.hasCommonGoal1;
    }
    public boolean getHasCommonGoal2(){
        return this.hasCommonGoal2;
    }
/*
    public void insertTile(int x, int y, PlayableItemTile tile) {
        int i = 0;
        Vector<Integer> position= new Vector<>();
        this.personalShelf.putTile(x,y,tile);
        maxTiles = ruleShelf.freeCellsInShelf(personalShelf.getStructure());
        //chiedi alfi come funziona questo metodo
        //metodo che gestisce inserimento in libreria
    }*/
}











