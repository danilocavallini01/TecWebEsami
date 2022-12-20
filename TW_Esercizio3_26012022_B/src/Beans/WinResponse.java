package Beans;

public class WinResponse extends Response {
    private boolean win;
    private String whoWin;

    public WinResponse(boolean win, String whoWin) {
        super("win_response");
        this.win = win;
        this.whoWin = whoWin;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getWhoWin() {
        return whoWin;
    }

    public void setWhoWin(String whoWin) {
        this.whoWin = whoWin;
    }

}
