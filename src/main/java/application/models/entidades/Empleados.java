package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.finanzas.Puestos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@SqlEntity("empleados")
public class Empleados extends Personas implements Entity {
    @SqlAttribute
    private LocalDate fecha_ini;
    @SqlAttribute
    private LocalTime jor_ini;
    @SqlAttribute
    private LocalTime jor_fin;
    @SqlAttribute
    private BigDecimal salario_neto;
    @SqlAttribute
    private Puestos puesto;

    @SqlInstance
    public Empleados(Integer id_persona, String rfc, String nombre, String apellido_p, String apellido_m, String no_cuenta, LocalDate fecha_ini, LocalTime jor_ini, LocalTime jor_fin, BigDecimal salario_neto, Puestos puesto) {
        super(id_persona, rfc, nombre, apellido_p, apellido_m, no_cuenta);
        this.fecha_ini = fecha_ini;
        this.jor_ini = jor_ini;
        this.jor_fin = jor_fin;
        this.salario_neto = salario_neto;
        this.puesto = puesto;
    }

    public Empleados(String rfc, String nombre, String apellido_p, String apellido_m, String no_cuenta, LocalDate fecha_ini, LocalTime jor_ini, LocalTime jor_fin, BigDecimal salario_neto, Puestos puesto) {
        super(rfc, nombre, apellido_p, apellido_m, no_cuenta);
        this.fecha_ini = fecha_ini;
        this.jor_ini = jor_ini;
        this.jor_fin = jor_fin;
        this.salario_neto = salario_neto;
        this.puesto = puesto;
    }
}
