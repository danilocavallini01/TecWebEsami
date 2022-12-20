package Beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

public class Tavolo {
    private Set<HttpSession> users;
    private int number;
    private List<Drink> drinks;
    private int totalPrice;

    public Tavolo(int number) {
        this.number = number;
        this.users = new HashSet<HttpSession>();
        this.drinks = new ArrayList<Drink>();
        this.totalPrice = 0;
    }

    public Set<HttpSession> getUsers() {
        return users;
    }

    public void setUsers(Set<HttpSession> users) {
        this.users = users;
    }

    public int getNumber() {
        return number;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

}
