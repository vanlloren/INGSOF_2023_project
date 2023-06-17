package Network.ClientSide;

import client.view.View;

public class ChatThread extends Thread{
    private final View userInterface;

    public ChatThread(View userInterface){
        this.userInterface = userInterface;
    }
    public void run(){
        this.userInterface.WriteInChat();
    }
}
