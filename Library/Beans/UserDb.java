package Beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserDb implements Serializable{
    private Map<String, User> users;

    public UserDb() {
        this.users = new HashMap<String, User>();
    }

    
    public User add(User user) {
        return this.users.put(user.getUsername(), user);
    }

    public User remove(String user) {
        return this.users.remove(user);
    }

    public User replace(User user) {
        return this.users.replace(user.getUsername(),user);
    }

    public boolean containsKey(String group) {
        return this.users.containsKey(group);
    }

    public boolean contains(User user) {
        return this.users.containsKey(user.getUsername());
    }

    public User get(String user) {
        return this.users.get(user);
    }

    public Collection<User> values() {
        return this.users.values();
    }
    
    public void empty() {
        this.users.clear();
    }


    public Map<String, User> getGroups() {
        return users;
    }

    public void setGroups(Map<String, User> users) {
        this.users = users;
    }
}
