package Network.ClientSide;

import client.view.View;

import java.util.Scanner;

public class ChatThread extends Thread{
    private final View userInterface;
    private final RemoteClientImplementation clientImplementation;
    private final Scanner scanner = new Scanner(System.in);

    public ChatThread(View userInterface, RemoteClientImplementation clientImplementation){
        this.userInterface = userInterface;
        this.clientImplementation = clientImplementation;
    }
    public void run(){
        this.userInterface.WriteInChat();
    }
}
