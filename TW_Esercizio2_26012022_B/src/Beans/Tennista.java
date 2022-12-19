package Beans;

public class Tennista {
    private int rankingATP;
    private int titols;
    private int wins;
    private int lose;
    private String surname;

    public int getRankingATP() {
        return rankingATP;
    }

    public void setRankingATP(int rankingATP) {
        this.rankingATP = rankingATP;
    }

    public int getTitols() {
        return titols;
    }

    public void setTitols(int titols) {
        this.titols = titols;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Tennista(int rankingATP, int titols, int wins, int lose, String surname) {
        this.rankingATP = rankingATP;
        this.titols = titols;
        this.wins = wins;
        this.lose = lose;
        this.surname = surname;
    }

}
