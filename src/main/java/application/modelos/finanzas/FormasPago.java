package application.modelos.finanzas;

import application.modelos.Tabla;

public class FormasPago extends Tabla<Integer> {
    private String forma;

    public FormasPago(String forma) {
        this.forma = forma;
    }

    public FormasPago(Integer integer, String forma) {
        super(integer);
        this.forma = forma;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
}
