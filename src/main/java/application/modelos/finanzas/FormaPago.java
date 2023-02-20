package application.modelos.finanzas;

import application.modelos.Tabla;

public class FormaPago extends Tabla<Integer> {
    public FormaPago() {
    }

    public FormaPago(Integer llave, String forma) {
        super(llave, forma);
    }
}
