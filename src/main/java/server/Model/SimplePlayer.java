package server.Model;

import java.io.Serializable;

public interface SimplePlayer extends Serializable {

    String getNickname();

    Shelf getPersonalShelf();
}
