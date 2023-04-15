package application.modelos.finanzas;

import application.modelos.entidades.Empleados;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Deducciones {
    private Integer id_tipo_deduccion;
    private String nombre;
    private String descripcion;
    private BigDecimal porcentaje;

    public Deducciones(Integer id_tipo_deduccion, String nombre, String descripcion, BigDecimal porcentaje) {
        this.id_tipo_deduccion = id_tipo_deduccion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
    }
}
