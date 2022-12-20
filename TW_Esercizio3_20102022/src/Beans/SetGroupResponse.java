package Beans;

import java.util.List;

public class SetGroupResponse extends Response {
    private List<String> groupChat;
    private boolean success;
    private int id;

    public SetGroupResponse(List<String> groupChat, int id) {
        super("set_group_response");
        this.groupChat = groupChat;
        this.success = true;
        this.id = id;
    }

    public SetGroupResponse() {
        super("set_group_response");
        this.groupChat = null;
        this.success = false;
        this.id = -1;
    }

    public List<String> getGroupChat() {
        return groupChat;
    }

    public void setGroupChat(List<String> groupChat) {
        this.groupChat = groupChat;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
