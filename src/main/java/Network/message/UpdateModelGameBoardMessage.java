package Network.message;

import server.Model.GameBoard;


public class UpdateModelGameBoardMessage extends Message{
    private final GameBoard gameBoard;

    UpdateModelGameBoardMessage(String nickname,GameBoard gameBoard) {
        super(nickname, MessageEnumeration.UPDATE_MODEL_GAMEBOARD);
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return this.gameBoard;
    }
}
