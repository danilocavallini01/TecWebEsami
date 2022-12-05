package Beans;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

public class User {
    private String username;
    private String password;
    private int groupId;
    private HttpSession session;


    public User(String username, String password, int groupId) {
        this.username = username;
        this.password = password;
        this.session = null;
        this.groupId = groupId;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.session = null;
        this.groupId = -1;
    }


    public String getUsername() {
        return username;
    }
    
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
