package server.Controller;
import Network.clientInterface;
import Network.message.*;
import Observer.Observer;
import Observer.ViewObserver;

import javax.swing.text.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClientServerController implements ViewObserver, Observer {

    private final View view;

    private clientInterface client;
    private String nickname;

    private final ExecutorService taskQueue;

    public ClientServerController(View view) {
        this.view = view;
        taskQueue = Executors.newSingleThreadExecutor();
    }

    @Override
    public void onUpdateNickname(String nickname) {
        this.nickname = nickname;
        client.sendMessage(new LoginRequestMessage(this.nickname));

    }
    public void onUpdatePlayersNumber(int playersNumber) {
        client.sendMessage(new PlayersNumberReplyMessage(this.nickname, playersNumber));
    }

    @Override
    public void update(Message message) {
        switch (message.getMessageEnumeration()) {
            case LOGIN_REPLY:
                LoginReplyMessage loginReplyMessage = (LoginReplyMessage) message;
                taskQueue.execute(()->view.showLoginResult(loginReplyMessage.isNicknameUniqueAccepted(), loginReplyMessage.isConnectionVerified(), this.nickname));
                break;
            case PLAYERNUMBER_REQUEST:
                taskQueue.execute(view::askPlayersNumber);
                break;
            case LOBBY:
                LobbyMessage lobbyMessage = (LobbyMessage) message;
                taskQueue.execute(() -> view.showLobby(lobbyMessage.getNicknameList(), lobbyMessage.getMaxPlayers()));
                break;
            default:
                break;
        }
    }
}
