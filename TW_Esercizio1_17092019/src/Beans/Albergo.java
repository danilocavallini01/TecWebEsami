package Beans;

import java.io.Serializable;

public class Albergo implements Serializable {
    private int id;
    private int camereTotali;
    private float prezzoStatico;

    public Albergo(int id, int camereTotali, float prezzoStatico) {
        this.id = id;
        this.camereTotali = camereTotali;
        this.prezzoStatico = prezzoStatico;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCamereTotali() {
        return camereTotali;
    }
    public void setCamereTotali(int camereTotali) {
        this.camereTotali = camereTotali;
    }
    public float getPrezzoStatico() {
        return prezzoStatico;
    }
    public void setPrezzoStatico(float prezzoStatico) {
        this.prezzoStatico = prezzoStatico;
    }

    
}
