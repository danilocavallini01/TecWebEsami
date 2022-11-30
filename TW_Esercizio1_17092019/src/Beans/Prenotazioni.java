package Beans;

import java.io.Serializable;

public class Prenotazioni implements Serializable{
    private int id;
    private int checkin;
    private int checkout;
    private boolean isFinalized;
    private float price;

    public Prenotazioni(int id, int checkin, int checkout, float price) {
        this.id = id;
        this.checkin = checkin;
        this.checkout = checkout;
        this.price = price;
        this.isFinalized = false;
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

    
    
}
