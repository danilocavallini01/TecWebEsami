package Beans;

public class Capitolo {
    int id;
    String content;
    int version;

    public Capitolo(int id) {
        this.id = id;
        this.content = "";
        this.version = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    public void addVersion() {
        this.version++;
    }
}
