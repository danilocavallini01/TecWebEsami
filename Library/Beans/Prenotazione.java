package Beans;

import java.util.Date;

public class Prenotazione {
    private int campo;
    private int giorno;
    private int orario;
    private User user1;
    private User user2;
    private boolean isTemp;
    private Date date;

    public Prenotazione(int campo, int giorno, int orario) {
        this.campo = campo;
        this.giorno = giorno;
        this.orario = orario;
        this.isTemp = false;
    }

    public boolean isTemp() {
        return isTemp;
    }

    public void setTemp(boolean isTemp) {
        this.isTemp = isTemp;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public int getCampo() {
        return campo;
    }

    public void setCampo(int campo) {
        this.campo = campo;
    }

    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public int getOrario() {
        return orario;
    }

    public void setOrario(int orario) {
        this.orario = orario;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
