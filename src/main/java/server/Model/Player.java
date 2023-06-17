package server.Model;


import Network.ClientSide.RemoteClientInterface;
import Observer.PlayerObservable;
import Util.Colour;
import client.view.TurnView;
import java.io.Serial;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * This Class represents a {@link Player Player} in the current game.
 * There can be 2 to 4 players in a game. Each player, identified with a unique {@code nickname}
 * within the game, has a {@link Shelf Shelf} assigned to him to be filled with {@link PlayableItemTile PlayableItemTiles}
 * and a specific {@link PersonalGoal PersonalGoal} that he will have to try to complete.
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public class Player extends PlayerObservable implements Serializable, SimplePlayer {
    @Serial
    private static final long serialVersionUID = 1303503671022200446L;
    private String nickname;
    private Integer points=0;
    private final Shelf personalShelf;

    private final GameModel gameModel;
    private final PersonalGoal personalGoal;
    private boolean hasCommonGoal1 = false;
    private boolean hasCommonGoal2 = false;
    /**
     * This method creates an instance of {@link Player Player} also binding it with the
     * actual {@link GameModel GameModel}
     *
     * @param nickname the unique {@code nickname} that will identify the {@link Player Player}
     * @param client the {@link Network.ClientSide.RemoteClientImplementation} through which the {@link Player Player}
     * has the possibility to interact with the {@link GameModel} and its components
     * @param gameModel the {@link GameModel GameModel} to bind
     */
    public Player (String nickname, RemoteClientInterface client, GameModel gameModel){
        this.nickname = nickname;
        this.gameModel = gameModel;
        this.personalShelf = new Shelf(gameModel);
        this.personalShelf.setUp();
        this.personalShelf.addObserver(client);
        this.personalGoal = new PersonalGoal(gameModel);
        this.personalGoal.addObserver(client);
    }

    /**
     *
     * @return the maximum number of {@link PlayableItemTile PlayableItemTiles} that a {@link Player Player}
     * can pick from the {@link LivingRoom LivingRoom} during a turn
     */
    public int getMaxTiles(){
        return personalShelf.countMaxVoidTilesShelfColumns();
    }


    // questi tre metodi vengono chiamati dal controller quando il player ha soddisfatto gli obbiettivi carte

    /**
     * Sets the completion state of {@link CommonGoal CommonGoal1} by the {@link Player Player}
     */
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

    /**
     * Sets the completion state of {@link CommonGoal CommonGoal2} by the {@link Player Player}
     */
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

    /**
     *
     * @return the {@link Shelf Shelf} possessed by the {@link Player Player}
     */
    public Shelf getPersonalShelf(){
        return this.personalShelf;
    }

    /**
     *
     * @return the {@link PersonalGoal PersonalGoal} possessed by the {@link Player Player}
     */
    public PersonalGoal getPersonalGoal(){
        return this.personalGoal;
    }

    /**
     *
     * @return the unique {@code nickname} assigned to the {@link Player Player}
     */
    public String getNickname(){
        return this.nickname;}

    /**
     * Sets the unique {@code nickname} of the {@link Player Player}
     *
     * @param nickname a {@link String String} containing the {@code nickname} to assign
     */
    public void setNickname (String nickname){ //il controller chiama questo metodo per aggiornare il valore dei nickName
        this.nickname= nickname;

    }

    /**
     *
     * @return the amount of points achieved by the {@link Player Player}
     */
    public Integer getPoints(){
        return this.points;
    }

    /**
     * Sets the amount of points achieved by the {@link Player Player}.
     * The points can be awarded after completing one of the {@link CommonGoal CommonGoals}, after fulfilling
     * the whole or a part of the {@link PersonalGoal PersonalGoal} or after placing groups of specific
     * sizes of adjacent {@link PlayableItemTile PlayableItemTiles} of the same {@link Colour Colour}
     * inside the {@link Shelf Shelf}.
     *
     * @param points the amount of points to award to the {@link Player Player}
     */
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

    /**
     *
     * @return {@code true} if the {@link Player Player} has completed
     * {@link CommonGoal CommonGoal1}, {@code false} otherwise
     */
    public boolean getHasCommonGoal1(){
        return this.hasCommonGoal1;
    }

    /**
     *
     * @return {@code true} if the {@link Player Player} has completed
     * {@link CommonGoal CommonGoal2}, {@code false} otherwise
     */
    public boolean getHasCommonGoal2(){
        return this.hasCommonGoal2;
    }

}








