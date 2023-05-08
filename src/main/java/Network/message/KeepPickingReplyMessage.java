package Network.message;

public class KeepPickingReplyMessage extends Message{
    boolean keepPicking;

    public KeepPickingReplyMessage(String nickname, String choice) {
        super(nickname, MessageEnumeration.KEEP_PICKING_REPLY);
        if(choice == "Y"){
            keepPicking = true;
        }else{
            keepPicking = false;
        }
    }

    public boolean getKeepPicking(){
        return this.keepPicking;
    }
}
