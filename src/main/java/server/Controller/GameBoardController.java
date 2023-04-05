package server.Controller;

import server.Model.GameBoard;

public class GameBoardController {
    private GameBoard controlledGameBoard;
    private LivingRoomController livingRoomController;

    public void getGameBoard(GameBoard gameBoard){
        this.controlledGameBoard = gameBoard;
    }

    public boolean checkPickedTilesNum() {//tiene conto del numero d' ItemTiles pescate ogni turno
        return controlledGameBoard.getPickedTilesNum() < 3;
    }

}
