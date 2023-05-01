package server.Model;

import Observer.Observable;
import Observer.Observer;

import java.util.ArrayList;
//Implementa una vista del model con metodi di sola lettura: corrisponde al TurnView di Damiani
// con cui viene restituita un'immagine del model.
public class GameModelView extends Observable<GameModel.Event> implements Observer<GameModel, GameModel.Event> {
    private final GameModel model;

    //static final long serialVersionUID = 1L;
    public GameModelView(GameModel model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.model = model;
        model.addObserver(this);
    }


    public Player getChairOwner() {
        return this.model.getChairOwner();
    }

    public Integer getPlayersNumber() {
        return this.model.getPlayersNumber();
    }

    public Player getCurrPlayer() {
        return this.model.getCurrPlayer();
    }

    public ArrayList<Player> getPlayersInGame(){return this.model.getPlayersInGame();}

    public GameBoard getMyShelfie(){return this.model.getMyShelfie();}

    public boolean getEndGame(){return this.model.getEndGame();}

    @Override
    public void update(GameModel o, GameModel.Event arg) {
        setChanged();
        notifyObservers(arg);
    }

}

