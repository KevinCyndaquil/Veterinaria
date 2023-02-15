package application.modelos.entidades;

import application.modelos.Tabla;

public class TipoProducto extends Tabla<Integer> {
    private String tipo;

    public TipoProducto(String tipo) {
        this.tipo = tipo;
    }

    public TipoProducto(Integer integer, String tipo) {
        super(integer);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
