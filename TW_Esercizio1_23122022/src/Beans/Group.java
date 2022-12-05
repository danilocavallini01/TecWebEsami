package Beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Group implements Serializable{
    private int id;
    private Set<User> users;
    private Prodotto prodotto;
    private Prodotto acquisto;
    private int concluded;

    public Group(int id, Prodotto prodotto) {
        this.id = id;
        this.users = new HashSet<User>();
        this.prodotto = prodotto;
        this.concluded = 0;
    }


    public int getConcluded() {
        return concluded;
    }

    public void setConcluded(int concluded) {
        this.concluded = concluded;
    }

    public Prodotto getAcquisto() {
        return acquisto;
    }

    public void setAcquisto(Prodotto acquisto) {
        this.acquisto = acquisto;
    }


  
    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
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
