package Beans;

public class MapResponse extends Response {
    public boolean gameStarted;
    public String[][] map;
    public String playerSymbol;
    public String playerTurn;

    public MapResponse(boolean gameStarted, String[][] map, String playerSymbol, String playerTurn) {
        super("map_response");
        this.gameStarted = gameStarted;
        this.map = map;
        this.playerSymbol = playerSymbol;
        this.playerTurn = playerTurn;
    }

    public MapResponse(String[][] map, String playerTurn) {
        super("map_response");
        this.gameStarted = true;
        this.map = map;
        this.playerSymbol = null;
        this.playerTurn = playerTurn;
    }


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

   
}
