package Beans;

public class Result {
    private boolean ok;
    private int number;
    
    public boolean isOk() {
        return ok;
    }
    public void setOk(boolean ok) {
        this.ok = ok;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public Result(boolean ok, int number) {
        this.ok = ok;
        this.number = number;
    }
}
