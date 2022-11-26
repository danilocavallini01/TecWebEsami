package Beans;

public class Ticket {
    private int id;
    private float prezzo;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public float getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Ticket(int id, float prezzo) {
        this.id = id;
        this.prezzo = prezzo;
    }
}
