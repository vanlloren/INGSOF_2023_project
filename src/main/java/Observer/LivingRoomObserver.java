package Observer;

import Util.CommonGoalType;
import client.view.TurnView;

public interface LivingRoomObserver {


    void onUpdatePickedTileFromLivingRoom(TurnView turnView, int x, int y);

}
