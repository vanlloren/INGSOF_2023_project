package Model;

import org.example.CommonGoalType;
import org.example.ScoringTokensType;

import java.util.ArrayList;


//SEQUENZA CORRETTA GAMEBOARD<-->LIVINGROOM:
//-creazione
//-fillLivingRoom (al suo interno avrà multiple chiamate di getNextInGameTile e putNextInGameTile)
//-updateAvailability
//----1° turno----
//-hasAdjacentTiles--->fillLivingRoom--->updateAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-pickedTilesNum
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-pickedTilesNum
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----
//-hasAdjacentTiles--->fillLivingRoom--->updateAvailability
//-getToPlayerFirstTile
//-checkAdjAvailability
//-fineTurno/getToPlayerAnotherTile
//-checkAdjAvailability
//-fineTurno/getToPlayerAnotherTile
//-fineTurno
//-updateAvailability
//----turno player succ----


public class CommonGoal {
    CommonGoalType commonGoalType;
    private ArrayList<ScoringTokensType> token_list = new ArrayList<ScoringTokensType>();


    public void setTokens(int playersNumber){
        if(playersNumber==2){
            token_list.add('TOKEN4');
            token_list.add('TOKEN8');
        }
        if(playersNumber==3){
            token_list.add('TOKEN4');
            token_list.add('TOKEN6')
            token_list.add('TOKEN8');
        }
        if(playersNumber==4) {
            token_list.add('TOKEN2')
            token_list.add('TOKEN4');
            token_list.add('TOKEN6')
            token_list.add('TOKEN8');

        }
    }

//questo va messo nel controller
    public boolean checkGoal() {
        switch (commonGoalType) {
            case COMMONGOAL02:
                return ruleCommonGoal.checkCorner();
                break;
            case COMMONGOAL01:
                return ruleCommonGoal.checkSixCouples();
                break;
            case CommonGOAL03:
                return ruleCommonGoal.checkFourGroups();
                break;
            case COMMONGOAL04:
                return ruleCommonGoal.checkSquare();
                break;
            case COMMONGOAL05:
                return ruleCommonGoal.CheckColumn1();
                break;
            case COMMONGOAL06:
                return ruleCommonGoal.checkEight();
                break;
                case COMMONGOAL07:
                return ruleCommonGoal.checkDiagonal();
            break;
            case COMMONGOAL08:
                return ruleCommonGoal.CheckLine1();
            break;
            case COMMONGOAL09:
                return ruleCommonGoal.CheckColumn2();
            break;
            case COMMONGOAL10:
                return ruleCommonGoal.CheckLine2();
            break;
            case COMMONGOAL11:
                return ruleCommonGoal.checkCrux();
            break;
            case COMMONGOAL12:
                return ruleCommonGoal.checkStair();
            break;
        }
        }

        public void algorithmCommonGoal(){
        //metodo che ho messo per vedere se lasciare la classe rule common goal o semplicemente nel controller usare direttamente un metodo



        }

//anche questo va nel controller
    public int getPoint(){

        public final int i=0;
        if() // metodo che gestisce lista dei punti token sopra
            // le common goal e quando uno ottiene per primo il token prende i punti e si toglie token dalla lisa



            if(token_card.is){



            }
    }}









<<<<<<< HEAD








<<<<<<< HEAD



