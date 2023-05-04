package server.Controller;

import client.view.VirtualView;
import server.Model.GameModel;
import server.enumerations.TypePhase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TurnController implements Serializable {

        private static final long serialVersionUID = -5987205913389392005L;
        private final GameModel game;
        private final List<String> nicknameTree;
        private String actualPlayer;


        transient Map<String, VirtualView> virtualViewMap;
        private TypePhase gamePhase;

        private final GameController gameController;

        public TurnController(Map<String, VirtualView> virtualViewMap, GameController gameController) {
            this.game = GameModel.getInstance();
            this.nicknameTree = new ArrayList<>(game.getPlayersNicknames());

            this.actualPlayer = nicknameTree.get(0); // set first active player
            this.virtualViewMap = virtualViewMap;
            this.gameController = gameController;
        }

        public String getActivePlayer() {
            return actualPlayer;
        }

        public void setActivePlayer(String actualPlayer) {
            this.actualPlayer = actualPlayer;
        }

        public void nextPlayer() {

            int currentActualPlayer = nicknameTree.indexOf(actualPlayer);
            if (currentActualPlayer + 1 < game.getPlayersNumber()) {
                currentActualPlayer = currentActualPlayer + 1;
            } else {
                currentActualPlayer = 0;
            }
            actualPlayer = nicknameTree.get(currentActualPlayer);
            gamePhase = TypePhase.YOUR_TURN;
        }
        public void setTypePhase(TypePhase turnPhaseType) {
            this.gamePhase = turnPhaseType;
        }

        public TypePhase getTypePhase() {
            return gamePhase;
        }

        public List<String> getNicknameTree() {
            return nicknameTree;
        }
        public void setVirtualViewMap(Map<String, VirtualView> virtualViewMap) {
            this.virtualViewMap = virtualViewMap;
        }

        public void newTurn() {
            turnControllerNotify("Turno di " + actualPlayer, actualPlayer);
            VirtualView vv = virtualViewMap.get(getActivePlayer());
            vv.showDefaultMessage("E'il tuo turno finalmente!");
            /*
            Andrà inserrita una funzione che permetta di chiedere al giocatore quale tile prendere dal tabellone di gioco
             */

            setTypePhase(TypePhase.YOUR_TURN);
        }


        /**
         * Chiede al giocatore attuale dove inserire la tile appena pescata dal tabellone
         *
         */
        public void movePhase(boolean skipEffect) {
            setTypePhase(TypePhase.YOUR_TURN);

        }

        public void turnControllerNotify(String messageToNotify, String excludeNickname) {
            virtualViewMap.values().forEach(vv -> vv.showMatchSituation(null, null,  actualPlayer));
            virtualViewMap.entrySet().stream()
                    .filter(entry -> !excludeNickname.equals(entry.getKey()))
                    .map(Map.Entry::getValue)
                    .forEach(vv -> vv.showDefaultMessage(messageToNotify));
        }

}
