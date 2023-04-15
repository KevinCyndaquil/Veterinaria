package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
public class Empleados extends Personas {
    private LocalDate fecha_ini;
    private LocalTime jor_ini;
    private LocalTime jor_fin;
    private BigDecimal salario_bruto;
    private BigDecimal salario_neto;
    private Puestos puesto;

    public Empleados(Integer id_persona, String nombre, String apellido_p, LocalDate fecha_ini, LocalTime jor_ini, LocalTime jor_fin, Puestos puesto,BigDecimal salario_neto) {
        super(id_persona, nombre, apellido_p);
        this.fecha_ini = fecha_ini;
        this.jor_ini = jor_ini;
        this.jor_fin = jor_fin;
        this.puesto = puesto;
        this.salario_bruto = BigDecimal.valueOf(puesto.getSalario());
        this.salario_neto = salario_neto;
    }

    public Empleados(String nombre, String apellido_p, LocalDate fecha_ini, LocalTime jor_ini, LocalTime jor_fin, Puestos puesto, BigDecimal salario_neto) {
        super(nombre, apellido_p);
        this.fecha_ini = fecha_ini;
        this.jor_ini = jor_ini;
        this.jor_fin = jor_fin;
        this.puesto = puesto;
        this.salario_bruto = BigDecimal.valueOf(puesto.getSalario());
        this.salario_neto = salario_neto;
    }
}
