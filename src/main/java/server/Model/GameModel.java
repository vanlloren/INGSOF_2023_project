package server.Model;


import Network.message.Message;
import Observer.GameModelObservable;
import Util.RandChairOwner;
import client.view.TurnView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This Class contains all the components of the Model of the Game.
 * It is the Class tha receives direct orders from the {@link Network.ServerSide.RemoteServerImplementation RemoteServer}
 * and which is in charge of beginning almost every process that modifies the Model of the Game.
 *
 * <i>Have a look at MyShelfie RuleBook for further information</i>
 */
public class GameModel extends GameModelObservable implements Serializable, SimpleGameModel {
    private static final long serialVersionUID = 44051L;


    private Player chosenChairOwner;
    private Integer chosenPlayersNumber = -1;

    public static final String Server_Nick = "Server";//C'è un collegamento al server per ogni giocatore.
    private Player currPlayer ;
    private String matchWinner;
    private GameBoard myShelfie ;
    private ArrayList<Player> playersInGame;
    private boolean endGame=false;
    private boolean GameOn = true;
    private String Chat;

    /**
     * This method creates an instance of {@link GameModel GameModel}.
     * It also creates a new instance of {@link GameBoard GameBoard} effectively binding
     * the {@link GameModel GameModel} to the {@link GameBoard GameBoard} which is its main component.
     */
    public GameModel(){
        this.myShelfie = new GameBoard(this);
        this.playersInGame = new ArrayList<>();
    }

    /**
     *
     * @return a {@link String String} containing the {@code nickname} of the {@link Player Player}
     * that has won the ongoing match
     */
    public String getMatchWinner(){
        return this.matchWinner;
    }

    /**
     * Sets the variable {@code endGame} to {@code false}.
     * <strong>For testing purposes only.</strong>
     */
    public void resetEndgame(){ //solo per i test
        this.endGame = false;
    }

    /**
     *
     * @return {@code true} if the current match has ended, {@code false} otherwise
     */
    public boolean getEndGame(){
        return endGame;
    }

    /**
     * Sets the variable {@code endGame} to {@code true}
     */
    public void setEndGame(){
        this.endGame = true;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelEndGame(new TurnView(this),this.getCurrPlayer(), this.endGame);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * Sets the {@link Player Player} that has won the game.
     * The method gets the {@code nickname} of the winning {@link Player Player} and
     * sets it into the variable {@code matchWinner}.
     *
     * @param player the {@link Player Player} that has won the current game
     */
    public void setMatchWinner(Player player){
        this.matchWinner = player.getNickname();
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelMatchWinner(new TurnView(this), matchWinner);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        GameTerminator();
    }

    /**
     * Method that controls the development of the chat and the chat {@link Message Messages} during the current match
     *
     * @param Nickname the {@code nickname} of the {@link  Player Player} that has sent the {@link Network.message.Message Message}
     * @param chat the {@link String String} containing the text of the {@link Network.message.Message Message}
     */
    public void setChat(String Nickname,String chat,String receiver) {
        this.Chat = chat;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelChat(new TurnView(this), Nickname, chat,receiver);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }


    /**
     * Sets the variable {@code gameOn} causing the termination of the
     * current match
     */
    public void GameTerminator() {
        this.GameOn = false;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelGameHasEnd(new TurnView(this));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     *
     * @return {@code true} if the current match is still ongoing,
     * {@code false} otherwise
     */
    public boolean getIsGameOn(){
        return this.GameOn;
    }

    /**
     *
     * @return the {@link Player Player} that has been chosen as the {@code chairOwner}
     */
    public Player getChairOwner() {
        return this.chosenChairOwner;
    }

    /**
     *
     * @return the {@link Player Player} who has currently been determined as {@code currPlayer}
     */
    public Player getCurrPlayer(){
        return this.currPlayer;
    }

    /**
     * Sets the actual {@link GameBoard GameBoard} of the current match
     *
     * @param gameBoard the instance of {@link GameBoard GameBoard} of the current match
     */
    public void setMyShelfie(GameBoard gameBoard){
        this.myShelfie = gameBoard;
    }

    /**
     *
     * @return the number of {@link Player Players} chosen for the current match
     */
    public int getPlayersNumber() {
        return this.chosenPlayersNumber;
    }

    /**
     * Sets the number of {@link Player Players} of the current match.
     * The choice is made by the first {@link Player Player} that has joined the match lobby.
     * The {@code playersNumber} can only be a number from 2 to 4.
     *
     * @param playersNumber the number of {@link Player Players} chosen for the current match
     */
    public void setPlayersNumber(int playersNumber){
        this.chosenPlayersNumber = playersNumber;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelPlayersNumber(new TurnView(this), playersNumber);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * Sets the {@link Player Player} that has become {@code currPlayer}
     * The selection is done automatically starting from the {@code chairOwner} and following the order
     * of the {@link Player Players} in the {@link ArrayList ArrayList} {@code playersInGame}.
     *
     * @param currPlayer the {@link Player Player} which has to become {@code currPlayer}
     */
    public void setCurrPlayer(Player currPlayer){
        this.currPlayer = currPlayer;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelCurrentPlayer(new TurnView(this), currPlayer);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

    }

    /**
     * Sets the {@link Player Player} that has become {@code chairOwner}.
     * This selection is made only once at random, at the beginning of the match, and is crucial
     * to determine the order of the turns of the {@link Player Players} during the match
     * and the {@code gameWinner} at the end of the match.
     *
     * @param player the {@link Player Player} which has to become {@code chairOwner}
     */
    public void setChairOwner(Player player) {
        this.chosenChairOwner = player;
        notifyObservers(obs -> {
            try {
                obs.onUpdateModelChairOwner(new TurnView(this), player);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });


    }


    /**
     *
     * @return an {@link ArrayList ArrayList} containing all the {@link Player Players} in the current match
     */
    public ArrayList<Player> getPlayersInGame() {
        return this.playersInGame;
    }

    /**
     * Adds a new {@link Player Player} to the {@link ArrayList ArrayList} containing all the
     * {@link Player Players} of the current match
     *
     * @param newPlayer the {@link Player Player} to add
     */
    public void setPlayersInGame(Player newPlayer) {
        if(chosenPlayersNumber.equals(-1)){
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> {
                try {
                    obs.onUpdateModelListPlayers(new TurnView(this), newPlayer);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else if (!chosenPlayersNumber.equals(-1)&&playersInGame.size()<chosenPlayersNumber-1){
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> {
                try {
                    obs.onUpdateModelListPlayers(new TurnView(this), newPlayer);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        else if(!chosenPlayersNumber.equals(-1)&&playersInGame.size()==chosenPlayersNumber-1){
            this.playersInGame.add(newPlayer);
            notifyObservers(obs -> {
                try {
                    obs.onUpdateModelListPlayers(new TurnView(this), newPlayer);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            notifyObservers(obs -> {
                try {
                    obs.OnUpdateModelCommonGoal(new TurnView(this), getMyShelfie().getLivingRoom().getCommonGoal1().getCommonGoalType(), getMyShelfie().getLivingRoom().getCommonGoal2().getCommonGoalType());
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            notifyObservers(obs -> {
                try {
                    obs.onUpdateModelGameHasStarted(new TurnView(this));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });

        }
    }


    /**
     *
     * @return the {@link GameBoard GameBoard} of the current match
     */
    public GameBoard getMyShelfie(){
        return myShelfie;
    }

    /**
     * This method checks whether the {@code nickname}, specified by a {@link Player Player} during the
     * login phase, is unique, and thus can be accepted, or not.
     *
     * @param nickname the {@code nickname} whose univocity needs to be checked
     * @return {@code true} if the {@code nickname} in unique, {@code false} otherwise
     */
    public boolean isNicknameAvailable(String nickname) {
        for (Player p: playersInGame
        ) {
            if(p.getNickname().equals(nickname)){
                return false;
            }
        }

        return true;
    }


}


