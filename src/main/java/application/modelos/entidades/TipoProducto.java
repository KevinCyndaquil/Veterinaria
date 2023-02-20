package application.modelos.entidades;

import application.modelos.Tabla;

public class TipoProducto extends Tabla<Integer> {

    public TipoProducto() {
    }

    public TipoProducto(Integer llave, String nombre) {
        super(llave, nombre);
    }
}
