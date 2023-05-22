package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.finanzas.Puestos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;


@Getter
@Setter
@SqlEntity("empleados")
public class Empleados implements Entity {
    @SqlAttribute
    @SqlKey
    @SqlKey(SqlKey.FOREIGN_KEY)
    private Personas persona;
    @SqlAttribute
    private Date fecha_ini;
    @SqlAttribute
    private Time jor_ini;
    @SqlAttribute
    private Time jor_fin;
    @SqlAttribute
    private BigDecimal salario_neto;
    @SqlAttribute
    private Puestos puesto;

    @SqlInstance
    public Empleados(Personas persona, Date fecha_ini, Time jor_ini, Time jor_fin, BigDecimal salario_neto, String nombre_puesto, BigDecimal salario_bruto) {
        this.persona = persona;
        this.fecha_ini = fecha_ini;
        this.jor_ini = jor_ini;
        this.jor_fin = jor_fin;
        this.salario_neto = salario_neto;
        this.puesto = Puestos.from(nombre_puesto);
    }

    public Empleados(Integer id_persona, String rfc, String nombre, String apellido_p, String apellido_m, String no_cuenta, Date fecha_ini, Time jor_ini, Time jor_fin, BigDecimal salario_neto, Puestos puesto) {
        this.fecha_ini = fecha_ini;
        this.jor_ini = jor_ini;
        this.jor_fin = jor_fin;
        this.salario_neto = salario_neto;
        this.puesto = puesto;
        this.persona = new Personas(id_persona, rfc, nombre, apellido_p, apellido_m, no_cuenta);
    }

    public Empleados(String rfc, String nombre, String apellido_p, String apellido_m, String no_cuenta, Date fecha_ini, Time jor_ini, Time jor_fin, BigDecimal salario_neto, Puestos puesto) {
        this.fecha_ini = fecha_ini;
        this.jor_ini = jor_ini;
        this.jor_fin = jor_fin;
        this.salario_neto = salario_neto;
        this.puesto = puesto;
        this.persona = new Personas(rfc, nombre, apellido_p, apellido_m, no_cuenta);
    }

    public Empleados() {
    }
}
