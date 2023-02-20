package application.modelos.citas;

import application.modelos.Tabla;

public class Estatus extends Tabla <Integer> {

    public Estatus() {
        super();
    }

    public Estatus(Integer llave, String estatus) {
        super(llave, estatus);
    }

}
