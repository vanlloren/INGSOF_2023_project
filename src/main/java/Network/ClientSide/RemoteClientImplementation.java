package Network.ClientSide;

import Network.ServerSide.RemoteServerInterface;
import Network.message.*;
import Observer.Observer;
import Observer.ViewObserver;
import client.view.TUI;
import client.view.View;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;

public class RemoteClientImplementation extends Client implements RemoteClientInterface, ViewObserver {

    private RemoteServerInterface server;
    private String nickname;


    RemoteClientImplementation(String address, int port, View userInterface) throws RemoteException {
        super(address, port, userInterface);
    }
    @Override
    public void connectionInit() throws Exception {
        Registry registry = LocateRegistry.getRegistry(getServerAddress(), getPortNum());
        server = (RemoteServerInterface) registry.lookup("MyShelfieServer");
        server.handShake(this);
    }

    @Override
    public void sendMessage(Message message) throws IOException {

    }

    @Override
    public void closeConnection() throws Exception {

    }

    @Override
    public void onMessage(Message message) throws RemoteException {
        switch (message.getMessageEnumeration()){
            case LOGIN_REPLY -> {
                LoginReplyMessage newMessage = (LoginReplyMessage)message;
                if(newMessage.isNicknameUniqueAccepted()) {
                    this.nickname = newMessage.getNickname();
                }
                this.userInterface.showLoginResults(newMessage.isNicknameUniqueAccepted(), newMessage.getNickname());
            }
            case PLAYERNUMBER_REQUEST -> {
                this.userInterface.askPlayersNumber();
                this.nickname = message.getNickname();
            }
        }
    }

    @Override
    public void disconnect() throws RemoteException {

    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void onUpdateServerInfo(Map<String, String> serverInfo) {

    }

    @Override
    public void onUpdateNickname(String nickname) throws RemoteException {
        server.onMessage(new LoginRequestMessage(nickname));
    }

    @Override
    public void onUpdateAskKeepPicking(String choice) throws RemoteException {
        server.onMessage(new KeepPickingMessage(nickname, choice));
    }

    @Override
    public void onUpdatePlayersNumber(int playersNumber) throws RemoteException {
        server.onMessage(new PlayersNumberReplyMessage(this.nickname, playersNumber));
    }

    @Override
    public void onUpdateFirstPlayer(String nickname) {

    }

    @Override
    public void onDisconnection() {

    }
}
