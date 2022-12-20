package Beans;

public class Drink {
    private String name;
    private int price;
    private boolean isDelivered;

    public Drink(String name, int price) {
        this.name = name;
        this.price = price;
        this.isDelivered = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }
}
