package Beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Group implements Serializable {
    private Integer id;
    private Set<User> users;

    public Group(Integer id) {
        this.id = id;
        this.users = new HashSet<User>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
