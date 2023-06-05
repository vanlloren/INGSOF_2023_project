package server.Model;


import Network.ClientSide.RemoteClientInterface;
import Observer.PlayerObservable;

import Observer.PlayerObserver;
import client.view.TurnView;
import server.Controller.RuleShelf;

import java.io.Serial;
import java.io.Serializable;
import java.rmi.RemoteException;

public class Player extends PlayerObservable implements Serializable, SimplePlayer {
    @Serial
    private static final long serialVersionUID = 1303503671022200446L;
    private String nickname;
    private Integer points=0;
    private final Shelf personalShelf;

    private GameModel gameModel;
    private final PersonalGoal personalGoal;
    private boolean hasCommonGoal1 = false;
    private boolean hasCommonGoal2 = false;

    private boolean endgame = false;


    public Player (String nickname, RemoteClientInterface client, GameModel gameModel){
        this.nickname = nickname;
        this.gameModel = gameModel;
        this.personalShelf = new Shelf(gameModel);
        this.personalShelf.setUp();
        this.personalShelf.addObserver(client);
        this.personalGoal = new PersonalGoal(gameModel);
        this.personalGoal.addObserver(client);
    }

    public int getMaxTiles(){
        return personalShelf.countMaxVoidTilesShelfColumns();
    }


    // questi tre metodi vengono chiamati dal controller quando il player ha soddisfatto gli obbiettivi carte

    public void setStatusCommonGoal1(){
        this.hasCommonGoal1 = true;
        notifyObservers(obs -> {
            try {
                obs.OnUpdateModelStatusCommonGoal1(new TurnView(gameModel));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setStatusCommonGoal2(){
        this.hasCommonGoal2 = true;
        notifyObservers(obs -> {
            try {
                obs.OnUpdateModelStatusCommonGoal2(new TurnView(gameModel));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
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
        notifyObservers(obs -> {
            try {
                obs.OnUpdateModelPlayerPoint(new TurnView(gameModel), points);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public boolean getHasCommonGoal1(){
        return this.hasCommonGoal1;
    }
    public boolean getHasCommonGoal2(){
        return this.hasCommonGoal2;
    }

}











