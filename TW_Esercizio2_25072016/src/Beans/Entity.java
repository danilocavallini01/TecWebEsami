package Beans;

public class Entity {
    private int x;
    private int y;
    private String name;
    private String description;
    
    public Entity(int x, int y, String name, String description) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.description = description;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    
}
