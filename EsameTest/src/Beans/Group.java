package Beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Group implements Serializable{
    private int id;
    private Set<User> users;
    private Tickets tickets;
    private int success;

    public Group(int id) {
        this.id = id;
        this.users = new HashSet<User>();
        this.tickets = new Tickets();
        this.success = 0;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public Tickets getTickets() {
        return tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
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
