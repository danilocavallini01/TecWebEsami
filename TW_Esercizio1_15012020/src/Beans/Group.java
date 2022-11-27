package Beans;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private int id;
    private Set<User> users;

    public Group(int id) {
        this.id = id;
        this.users = new HashSet<User>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User... users) {
        for ( User user : users) {
            this.users.add(user);
        }
    }
}
