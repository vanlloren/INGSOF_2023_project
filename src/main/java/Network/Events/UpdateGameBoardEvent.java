package Network.Events;

import server.Model.GameBoard;

public class UpdateGameBoardEvent extends Event{
    private GameBoard gameBoard;

    public UpdateGameBoardEvent(GameBoard gameBoard) {
        super(EventEnum.UPDATE_GAME_BOARD);
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard(){
        return this.gameBoard;
    }
}
