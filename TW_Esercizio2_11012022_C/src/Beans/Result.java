package Beans;

public class Result {
    private boolean ok;
    private int sum;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Result(boolean ok, int sum) {
        this.ok = ok;
        this.sum = sum;
    }

    public Result(boolean ok) {
        this.ok = ok;
    }

}
