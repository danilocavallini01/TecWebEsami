package Beans;

public class Result {
    private boolean success;
    private String winner;
    private int price;

    public Result(boolean success, String winner, int price) {
        this.success = success;
        this.winner = winner;
        this.price = price;
    }

    public Result(boolean success) {
        this.success = success;
        this.winner = null;
        this.price = 0;
    }
}