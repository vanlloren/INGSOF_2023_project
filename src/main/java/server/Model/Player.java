package server.Model;


import Util.CommonGoalType;
public class Player {
    private String nickname;
    private Integer points;
    private Shelf personalShelf = new Shelf();
    private PersonalGoal personalGoal = new PersonalGoal();
    private LivingRoom livingRoom;
    private boolean hasCommonGoal1;
    private boolean hasCommonGoal2;
    private boolean hasPersonalGoal;
    private boolean hasPersonalGoal1;
    private boolean hasPersonalGoal2;
    private boolean hasPersonalGoal3;
    private boolean hasPersonalGoal4;
    private boolean hasPersonalGoal5;
    private boolean hasPersonalGoal6;
    private boolean hasPersonalGoal7;
    private boolean hasPersonalGoal8;
    private boolean hasPersonalGoal9;
    private boolean hasPersonalGoal10;
    private boolean hasPersonalGoal11;
    private boolean hasPersonalGoal12;

    // costruttore player in cui passo i parametri principali passati dal controller che chiamerà dopo la ricezione di tutti i nickname da lato client
    public Player (String nickname){
        this.nickname = nickname;
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

    public boolean getHasCommonGoal1(){
        return this.hasCommonGoal1;
    }
    public boolean getHasCommonGoal2(){
        return this.hasCommonGoal2;
    }
    public void insertTile(int x, int y, PlayableItemTile tile) { //parametri passati dal controller;chiamato dal controller quando il player da le istruzioni in cui dice dove mettere l item sulla shelf
        int i = 0;
        this.personalShelf.putTile(x,y,tile,i); //chiedi alfi come funziona questo metodo
        //metodo che gestisce inserimento in libreria
    }
    public boolean getHasPersonalGoal1(){
        return this.hasPersonalGoal1;
    }
    public boolean getHasPersonalGoal2(){
        return this.hasPersonalGoal2;
    }
    public boolean getHasPersonalGoal3(){
        return this.hasPersonalGoal3;
    }
    public boolean getHasPersonalGoal4(){
        return this.hasPersonalGoal4;
    }
    public boolean getHasPersonalGoal5(){
        return this.hasPersonalGoal5;
    }
    public boolean getHasPersonalGoal6(){
        return this.hasPersonalGoal6;
    }
    public boolean getHasPersonalGoal7(){
        return this.hasPersonalGoal7;
    }
    public boolean getHasPersonalGoal8(){
        return this.hasPersonalGoal8;
    }
    public boolean getHasPersonalGoal9(){
        return this.hasPersonalGoal9;
    }
    public boolean getHasPersonalGoal10(){
        return this.hasPersonalGoal10;
    }
    public boolean getHasPersonalGoal11(){
        return this.hasPersonalGoal11;
    }
    public boolean getHasPersonalGoal12(){
        return this.hasPersonalGoal12;
    }
}











