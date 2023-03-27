package application.modelos.finanzas;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

public class FormaPago extends Tabla {
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private Double comision;
    public FormaPago(String nombre, Double comision) {
        this.nombre = nombre;
        this.comision = comision;
    }

    public FormaPago(Integer id, String nombre, Double comision) {
        super(id);
        this.nombre = nombre;
        this.comision = comision;
    }
}
