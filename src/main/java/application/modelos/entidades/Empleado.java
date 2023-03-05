package application.modelos.entidades;

import application.modelos.finanzas.Nomina;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Empleado extends Persona{
    @Getter @Setter
    private LocalDate fecha_inicio;
    @Getter @Setter
    private LocalTime hora_inicio;
    @Getter @Setter
    private LocalTime hora_fin;
    @Getter @Setter
    private Puesto puesto;
    @Getter @Setter
    private List<Nomina> nominas;

    public Empleado(String rfc, String nombre, String apellido_paterno, String apellido_materno, LocalDate fecha_inicio, LocalTime hora_inicio, LocalTime hora_fin, Puesto puesto, List<Nomina> nominas) {
        super(rfc, nombre, apellido_paterno, apellido_materno);
        this.fecha_inicio = fecha_inicio;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.puesto = puesto;
        this.nominas = nominas;
    }

    public Empleado(Integer id, String rfc, String nombre, String apellido_paterno, String apellido_materno, LocalDate fecha_inicio, LocalTime hora_inicio, LocalTime hora_fin, Puesto puesto, List<Nomina> nominas) {
        super(id, rfc, nombre, apellido_paterno, apellido_materno);
        this.fecha_inicio = fecha_inicio;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.puesto = puesto;
        this.nominas = nominas;
    }
}
