package Beans;

import java.util.Date;

public class ArticoloAsta {
    private int price;
    private int offers;
    private String name;
    private Date exprire;

    private boolean isConclusa;
    private User winner;

    public ArticoloAsta(String name, Date exprire) {
        this.name = name;
        this.price = 0;
        this.offers = 0;
        this.exprire = exprire;
        this.isConclusa = false;
        this.winner = null;
    }

    public Date getExprire() {
        return exprire;
    }

    public void setExprire(Date exprire) {
        this.exprire = exprire;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOffers() {
        return offers;
    }

    public void setOffers(int offers) {
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConclusa() {
        return isConclusa;
    }

    public void setConclusa(boolean isConclusa) {
        this.isConclusa = isConclusa;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

}
