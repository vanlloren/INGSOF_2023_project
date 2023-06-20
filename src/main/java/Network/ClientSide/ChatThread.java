package Network.ClientSide;

import client.view.View;

/**
 * This Class is used to create a new thread on the {@link RemoteClientImplementation RemoteClient}
 * which will take care of the delivering of the chat messages
 */
public class ChatThread extends Thread{
    private final View userInterface;

    /**
     * This method creates a new instance of {@link ChatThread ChatThread}
     * @param userInterface the {@link View View} linked to the user
     */
    public ChatThread(View userInterface){
        this.userInterface = userInterface;
    }
    public void run(){
        this.userInterface.WriteInChat();
    }
}
