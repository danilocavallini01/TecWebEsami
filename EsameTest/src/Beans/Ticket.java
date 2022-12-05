package Beans;

public class Ticket {
    Integer id;
    float price;
    int quantity;
    
    public Ticket(Integer id, float price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
