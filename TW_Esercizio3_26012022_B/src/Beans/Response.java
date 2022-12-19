package Beans;

import com.ibm.db2.jcc.am.bo;

public class Response {
    public boolean gameStarted;
    private boolean win;
    private String whoWin;

    public String[][] map;
    public String playerSymbol;

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public String[][] getMap() {
        return map;
    }

    public void setMap(String[][] map) {
        this.map = map;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public Response(boolean gameStarted, String[][] map, String playerSymbol) {
        this.gameStarted = gameStarted;
        this.map = map;
        this.playerSymbol = playerSymbol;
        this.win = false;
        this.whoWin = null;
    }

    public Response(boolean gameStarted, String[][] map, boolean win, String whoWin) {
        this.gameStarted = gameStarted;
        this.map = map;
        this.playerSymbol = null;
        this.win = win;
        this.whoWin = whoWin;
    }

}
