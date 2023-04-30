package server.Model;


import Observer.Observable;
import Util.CommonGoalType;
import server.enumerations.SinglePlayerPhase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player extends Observable implements Serializable {

    private static final long serialVersionUID = 74L;
    private final String nickname;
    private Integer points;
    private  Shelf personalShelf;
    private PersonalGoal personalGoal;
    private LivingRoom livingRoom;
    private boolean hasCommonGoal1;
    private boolean hasCommonGoal2;
    private boolean hasPersonalGoal;

    private SinglePlayerPhase state;


    // costruttore player in cui passo i parametri principali passati dal controller che chiamerà dopo la ricezione di tutti i nickname da lato client
    public Player (String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){return this.nickname;}

    // questi tre metodi vengono chiamati dal controller quando il player ha soddisfatto gli obbiettivi carte

    public void setStatusCommonGoal1(){
        this.hasCommonGoal1 = true;
    }

    public void setStatusCommonGoal2(){
        this.hasCommonGoal2 = true;
    }

    public void setStatusPersonalGoal(){
        this.hasPersonalGoal = true;
    }

    //metodo che verra chiamato dal controller il quale preventivamente crea una living room per i giocatori che si sono collegati e assegna a tutti la STESSA LIVING ROOM
    public void setLivingRoom(LivingRoom livingRoom){ // metodo importante che serve ad assegnare ai giocatori la stessa plancia di gioco nel caso ci siano partite multiple da gestire
        this.livingRoom= livingRoom;
    }

    public Shelf getPersonalShelf(){ return this.personalShelf;}

    public void setPersonalShelf(Shelf personalShelf){ this.personalShelf = personalShelf;}

    public Integer getPoints(){
        return this.points;
    }

    public void setPersonalGoal(){ // questo viene propriamente inizializzato dal player mentre i commongoal appartengono alla living room
        personalGoal = new PersonalGoal();
        //funzione che assegna personalgoal//
    }

    public boolean getHasCommonGoal1(){
        return this.hasCommonGoal1;
    }
    public boolean getHasCommonGoal2(){
        return this.hasCommonGoal2;
    }
    /**
     * Return the State of the Player.
     *
     * @return State of the Player.
     */
    public SinglePlayerPhase getState() {
        return state;
    }

    /**
     * Set the State of the Player.
     *
     * @param state State of the Player.
     */
    public void setState(SinglePlayerPhase state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return nickname.equals(player.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }
}











