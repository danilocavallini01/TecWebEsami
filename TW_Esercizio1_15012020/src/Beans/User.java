package Beans;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private int groupId;

    private Date lastModifyOnPsw;
    private boolean valid;
    private int tryiedPsw;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.lastModifyOnPsw = new Date();
        this.valid = true;
        this.groupId = -1;
        this.tryiedPsw = 0;
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

    public Date getLastModifyOnPsw() {
        return lastModifyOnPsw;
    }


    public void setLastModifyOnPsw(Date lastModifyOnPsw) {
        this.lastModifyOnPsw = lastModifyOnPsw;
    }


    public boolean isValid() {
        return valid;
    }


    public void setValid(boolean valid) {
        this.valid = valid;
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


    public int getTryiedPsw() {
        return tryiedPsw;
    }


    public void setTryiedPsw(int tryiedPsw) {
        this.tryiedPsw = tryiedPsw;
    }

        
}
