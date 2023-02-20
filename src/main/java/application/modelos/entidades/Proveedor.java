package application.modelos.entidades;

import application.modelos.Tabla;

public class Proveedor extends Tabla <Integer> {

    public Proveedor() {
    }

    public Proveedor(Integer llave, String nombre) {
        super(llave, nombre);
    }

}
