package Beans;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

public class Prenotazione implements Serializable{
    private int id;
    private int checkin;
    private int checkout;
    private boolean isFinalized;
    private float price;
    private HttpSession session;

    public Prenotazione(int id, int checkin, int checkout, float price, HttpSession session) {
        this.id = id;
        this.checkin = checkin;
        this.checkout = checkout;
        this.price = price;
        this.isFinalized = false;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCheckin() {
        return checkin;
    }

    public void setCheckin(int checkin) {
        this.checkin = checkin;
    }

    public int getCheckout() {
        return checkout;
    }

    public void setCheckout(int checkout) {
        this.checkout = checkout;
    }

    public boolean isFinalized() {
        return isFinalized;
    }

    public void setFinalized(boolean isFinalized) {
        this.isFinalized = isFinalized;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    
    
}
