package server.Controller;
import Network.ServerSide.RemoteServerImplementation;
import Util.Colour;
import Util.RandCommonGoal;

import server.Model.*;


import java.rmi.RemoteException;
import java.util.*;

//All'interno di questa classe vi deve essere contenuta tutta la logica che sta dietro al gioco effettivo
//escluso il settaggio della lobby e la costruzione del gioco quindi solo il pescaggio, l'inserimento ed il conteggio sono le azioni da seguire e tenere sott'occhio
public class GameController {

    private  GameModel game;
  //  private boolean timeOut;
    private final GameBoardController gameBoardController; // gameBoardController è il tramite tra GameController e le classi GameBoard, LivingRoom e ItemBag

    private RemoteServerImplementation remoteServer;
    /*
    Il comando dichiara un campo della classe come transient,
    il che significa che il campo non sarà incluso nella serializzazione
    dell'oggetto.
    In questo caso, il campo virtualViewMap è una mappa che associa un nickname a una vista virtuale (VirtualView)
    e viene utilizzato dal controller per comunicare con le viste dei singoli giocatori.
    Poiché le viste devono essere ricreate ad
    ogni avvio del gioco e non devono essere incluse nella serializzazione,
     il campo viene dichiarato come transient.
     */

    boolean stopPicking = false;
    boolean moveOn = false;
    boolean stillGoing = true;
    boolean invalidTile = false;

    private int xPosCurrTile;
    private int yPosCurrTile;


    public GameController(GameModel game) {
        this.game = game;
        this.gameBoardController = new GameBoardController(this);
    }

    public GameModel getGame() {
        return this.game;
    }

    public void setRemoteServer(RemoteServerImplementation remoteServer){
        this.remoteServer = remoteServer;
    }

    public void setInvalidTile(){
        this.invalidTile=true;
    }
    public void resetInvalidTile(){
        this.invalidTile=false;
    }

    public void resetStillGoing(){
        this.stillGoing = false;
    }

    public void setStillGoing(){
        this.stillGoing = true;
    }

    public void setPosCurrTile(int x, int y){
        this.xPosCurrTile = x;
        this.yPosCurrTile = y;
    }


    public void setStopPicking(){stopPicking = true;}

    public void setMoveOn(){moveOn = true;}

    public GameBoardController getGameBoardController(){
        return this.gameBoardController;
    }




    /*
    the following method is called when the number of player has reached the requested number
    and as a consequence a game board with the right livingRoom (based on numOfPlayers) is created and the commonGoals are being set and prepared for the game
     */
    public void initGameBoard(){
        gameBoardController.gameBoardInit();  //inizializza itemBag e livingRoom
        game.setMyShelfie(gameBoardController.getControlledGameBoard());
        RandCommonGoal.setType(game.getMyShelfie().getLivingRoom().getCommonGoal1(), game.getMyShelfie().getLivingRoom().getCommonGoal2());
        game.getMyShelfie().getLivingRoom().getCommonGoal1().setTokens(game.getPlayersNumber());
        game.getMyShelfie().getLivingRoom().getCommonGoal2().setTokens(game.getPlayersNumber());

    }


    public ArrayList<PlayableItemTile> pickTilesArray (String nickname) {  //restituisce le 1/2/3 tiles prese dalla livingRoom dal player nel suo turno
            boolean finish=false;
            ArrayList<PlayableItemTile> tileArray = new ArrayList<PlayableItemTile>();
            moveOn = false;

        try {
            remoteServer.onPickTilesBegin(nickname);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


            while(!finish) {
                while(!moveOn){}
                if (gameBoardController.checkPickedTilesNum() && gameBoardController.getControlledGameBoard().getPickedTilesNum()<getGame().getCurrPlayer().getMaxTiles()) {



                    tileArray=gameBoardController.PickManager(xPosCurrTile, yPosCurrTile);

                    //chiedo al player se vuole smettere di pescare
                    moveOn=false;

                    if(!invalidTile){
                        if(stillGoing) {
                            try {
                                remoteServer.onUpdateAskKeepPicking(nickname);
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                        }





                        //stop=decisione player
                        if(stopPicking){
                            finish=true;
                            gameBoardController.getControlledLivingRoom().updateAvailability();
                            if(!gameBoardController.checkIfAdjacentTiles()){
                                gameBoardController.livingRoomFiller();
                                gameBoardController.getControlledLivingRoom().updateAvailability();
                            }
                        } else if (!stillGoing) {
                            setStillGoing();
                            finish=true;
                            gameBoardController.getControlledLivingRoom().updateAvailability();
                            if(!gameBoardController.checkIfAdjacentTiles()){
                                gameBoardController.livingRoomFiller();
                                gameBoardController.getControlledLivingRoom().updateAvailability();
                            }
                            try {
                                remoteServer.onMaxTilesPicked(nickname);
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }else{
                        try {
                            remoteServer.onInvalidTile(nickname);
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    //messaggio che informi che sono già state prese tre tessere
                    finish = true;
                    tileArray=gameBoardController.giveTileToPlayer();
                    try {
                        remoteServer.onMaxTilesPicked(nickname);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            gameBoardController.toPlayerTilesResetter();
            return tileArray;
    }

        public void InsertTileShelf(Player player,PlayableItemTile tile,int x, int y,int num){
        Shelf shelf = player.getPersonalShelf();
        shelf.putTile(x,y,tile);
        shelf.freeCellsInShelf();
        calculatePoint(player,player.getPersonalShelf().getStructure(),game.getMyShelfie().getLivingRoom());
        }

        public void nextTurn () {

        ArrayList<Player> listPLayer = game.getPlayersInGame();

        int index= listPLayer.indexOf(game.getCurrPlayer());

        if(!game.getEndGame()){
            if(game.getPlayersInGame().indexOf(game.getCurrPlayer())==game.getPlayersInGame().size()-1){
            game.setCurrPlayer(listPLayer.get(0));}
            else game.setCurrPlayer(listPLayer.get(index+1));
        }

        else {
            if(game.getPlayersInGame().indexOf(game.getCurrPlayer())==game.getPlayersInGame().indexOf(game.getChairOwner())-1){
                CalculateWinner(game.getPlayersInGame());
            }
            else {
                if(game.getPlayersInGame().indexOf(game.getCurrPlayer())==game.getPlayersInGame().size()){
                    game.setCurrPlayer(listPLayer.get(0));}
                else game.setCurrPlayer(listPLayer.get(index+1));
            }

        }


    }

        public void CalculateWinner(ArrayList<Player> playerArrayList){
        ArrayList<Integer> pointsList = new ArrayList<>();
        ArrayList<Player> Winners = new ArrayList<Player>();
            for (Player p: playerArrayList) {
                pointsList.add(p.getPoints());
            }
            Integer MaxPoint = Collections.max(pointsList);
            for (Player p: playerArrayList) {
                if(p.getPoints().equals(MaxPoint))
                Winners.add(p);
            }
            if(Winners.size()==1){
            game.setMatchWinner(Winners.get(0));
            }
            else //caso di parità multiple
            {
                Player Winner;
                Player chairOwner = game.getChairOwner();
                int i = 0;
                ArrayList<Integer> positions = new ArrayList<>();
                for (Player p: playerArrayList) {
                    if(p.getPoints().equals(MaxPoint))
                    positions.add(playerArrayList.indexOf(p));
                    i = positions.indexOf(playerArrayList.indexOf(chairOwner));
                    if(i>0){
                         Winner = playerArrayList.get(positions.get(i-1)) ;
                    }
                    else
                    {
                        Winner = playerArrayList.get(positions.get(positions.size())) ;
                    }
                    game.setMatchWinner(Winner);

                }

            }

        //DEVI SCRIVERE FUNZIONE CHE TROVA PLAYER CON MAX PUNTEGGIO
        // SE CI SONO PAREGGI SEGUO REGOLA DI DISTANZA MAGGIORE DA PLAYER CON LA CHAIR

        }


    public static HashMap<Colour, ArrayList<Integer>> findAdjGroups(PlayableItemTile[][] shelf) {
        HashMap<Colour, ArrayList<Integer>> adjGroups = new HashMap<>();

        PlayableItemTile[][] helperShelf = shelf;

        boolean[][] visited = new boolean[6][5];
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if(helperShelf[i][j] != null){
                    if (!visited[i][j]) {
                        Colour colore = helperShelf[i][j].getColour();
                        ArrayList<Integer> dimensioni = new ArrayList<>();
                        int dimension = findAdjGroupDim(helperShelf, visited, i, j, colore, dimensioni);

                        // Aggiungi il supergruppo alla mappa dei risultati
                        if (adjGroups.containsKey(colore)) {
                            adjGroups.get(colore).add(dimension);
                        }else {
                            ArrayList<Integer> nuovaLista = new ArrayList<>();
                            nuovaLista.add(dimension);
                            adjGroups.put(colore, nuovaLista);
                        }
                    }
                }
            }
        }

        return adjGroups;
    }

    public static int findAdjGroupDim(PlayableItemTile[][] structure, boolean[][] visitated, int i, int j, Colour colour, ArrayList<Integer> dimension) {
        if (i < 0 || i >= structure.length || j < 0 || j >= structure[0].length || structure[i][j] == null || visitated[i][j] || structure[i][j].getColour() != colour) {
            return 0;
        }

        visitated[i][j] = true;
        int dimensione = 1;

        for (Integer d : dimension) {
            if (d != null && d == dimensione) {
                dimensione += findAdjGroupDim(structure, visitated, i-1, j, colour, dimension); // Alto
                dimensione += findAdjGroupDim(structure, visitated, i+1, j, colour, dimension); // Basso
                dimensione += findAdjGroupDim(structure, visitated, i, j-1, colour, dimension); // Sinistra
                dimensione += findAdjGroupDim(structure, visitated, i, j+1, colour, dimension); // Destra
                return dimensione;
            }
        }

        dimension.add(dimensione);
        dimensione += findAdjGroupDim(structure, visitated, i-1, j, colour, dimension); // Alto
        dimensione += findAdjGroupDim(structure, visitated, i+1, j, colour, dimension); // Basso
        dimensione += findAdjGroupDim(structure, visitated, i, j-1, colour, dimension); // Sinistra
        dimensione += findAdjGroupDim(structure, visitated, i, j+1, colour, dimension); // Destra
        return dimensione;
    }

        public Integer AddAdjacencyPoint(HashMap<Colour, ArrayList<Integer>> adjacencyGroups){
            Set<Colour> keys = adjacencyGroups.keySet();
            Integer point = 0;
            for (Colour colour : keys) {
              ArrayList<Integer> counter = adjacencyGroups.get(colour);
              for(int i=0;i<counter.size();i++){
                  if(counter.get(i)==3){
                     point = point+2;
                  }
                  else if (counter.get(i)==4){
                     point = point+3;
                  }
                  else if (counter.get(i)==5){
                     point = point+5;
                  }
                  else if (counter.get(i)>6){
                     point = point+8;
                  }
              }
            }

            return point;
        }

        public void calculatePoint (Player player, ItemTile[][]structure, LivingRoom livingRoom) {
            if (!player.getHasCommonGoal1() && CheckCommonGoal.checkGoal(player.getPersonalShelf(), livingRoom.getCommonGoal1().getCommonGoalType())) {
                Integer i;
                i = player.getPoints();
                i = i + addPoint(livingRoom.getCommonGoal1());
                player.setStatusCommonGoal1();
                player.setPoints(i);
            }

            if (!player.getHasCommonGoal2() && CheckCommonGoal.checkGoal(player.getPersonalShelf(), livingRoom.getCommonGoal2().getCommonGoalType())) {
                Integer i;
                i = player.getPoints();
                i = i + addPoint(livingRoom.getCommonGoal2());
                player.setStatusCommonGoal2();
                player.setPoints(i);
            }
            if(player.getPersonalGoal().getPoint()<CheckPersonalGoal.calculatePoints(player.getPersonalGoal(), player.getPersonalShelf().getStructure())){
                Integer i;
                Integer x;
                Integer y;
                i = player.getPoints();
                x = CheckPersonalGoal.calculatePoints(player.getPersonalGoal(), player.getPersonalShelf().getStructure());
                y = player.getPersonalGoal().getPoint();
                i = i + x - y;
                player.getPersonalGoal().setPoint(x);
                player.setPoints(i);

            }
            if(player.getPersonalShelf().getPointsAdj()<AddAdjacencyPoint(findAdjGroups(player.getPersonalShelf().getStructure()))){
                int i = AddAdjacencyPoint(findAdjGroups(player.getPersonalShelf().getStructure()));
                int x = player.getPoints();
                int y = player.getPersonalShelf().getPointsAdj();
                player.getPersonalShelf().setPointsAdj(i);
                x = x+i-y;
                player.setPoints(x);

            }
        }


        public Integer addPoint (CommonGoal commonGoal){
            ArrayList<Integer> token_list = commonGoal.getToken_list();
            Integer i = 0;
            if (0 < token_list.size()) {
                i = token_list.get(token_list.size()-1);
                token_list.remove(token_list.size() - 1);
            }
            return i;
        }


//Sono interessato a ricevere notifiche solo dalla TextualUI/GraphicalUI
}

