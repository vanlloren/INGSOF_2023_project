package Observer;

import Network.message.Message;

public interface Observer {
    void update(Message message);
}
