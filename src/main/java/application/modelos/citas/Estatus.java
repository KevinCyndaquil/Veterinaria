package application.modelos.citas;

import application.modelos.Tabla;

public class Estatus extends Tabla <Integer> {
    private String estatus;

    public Estatus(String estatus) {
        this.estatus = estatus;
    }

    public Estatus(Integer integer, String estatus) {
        super(integer);
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
