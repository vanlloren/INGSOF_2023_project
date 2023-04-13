package server.Model;


import Util.CommonGoalType;
public class Player {
    private String nickname;
    private Integer points;
    private Shelf personalShelf;
    private PersonalGoal personalGoal;
    private CommonGoal commonGoal1;
    private CommonGoal commonGoal2;
    private LivingRoom livingRoom;
    private boolean hasCommonGoal1;
    private boolean hasCommonGoal2;
    private boolean hasPersonalGoal;

    // costruttore player in cui passo i parametri principali passati dal controller che chiamerà dopo la ricezione di tutti i nickname da lato client
    public Player (String nickname, CommonGoal commonGoal1, CommonGoal commonGoal2, PersonalGoal personalGoal){
        this.nickname = nickname;
        this.commonGoal1 = commonGoal1;
        this.commonGoal2 = commonGoal2;
        this.personalGoal = personalGoal;
    }



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

    public Shelf getPersonalShelf(){ // metodo che verrà chiamato dal controller per poter accedere alla libreria
        //personale del giocatore e avviare tutti i check che controllano se gli obbiettivi sono stati soddisfatti
        return this.personalShelf;
    }

    public String getNickname(){
        return this.nickname;}

    public void setNickname (String nickname){ //il controller chiama questo metodo per settare nicknames
        this.nickname= nickname;

    }

    public Integer getPoints(){
        return this.points;
    }




    public void setPersonalGoal(){ // questo viene propriamente inizializzato dal player mentre i commongoal appartengono alla living room
        personalGoal = new PersonalGoal();
        //funzione che assegna personalgoal//
    }


    public void setCommonGoals(){ //chiamato da controller a cascata quando chiamo il generatore di common goal dalla living room
        this.commonGoal1 = this.livingRoom.getCommonGoal1();
        this.commonGoal2 = this.livingRoom.getCommonGoal2();
    }
    public boolean getHasCommonGoal1(){
        return this.hasCommonGoal1;
    }
    public boolean getHasCommonGoal2(){
        return this.hasCommonGoal2;
    }
    public void insertTile(int x, int y, ItemTile tile) throws NotValidCoordinate,Notavailable{ //parametri passati dal controller;chiamato dal controller quando il player da le istruzioni in cui dice dove mettere l item sulla shelf
        this.personalShelf.putTile(x,y,tile,); //chiedi alfi come funziona questo metodo
        //metodo che gestisce inserimento in libreria
    }
}











