package application.modelos.finanzas;

import application.modelos.Tabla;
import application.modelos.entidades.Empleado;

import java.time.LocalDate;
import java.time.LocalTime;

public class Nomina extends Tabla<Integer> {
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private LocalTime total_horas;
    private Double total_bono;
    private Empleado empleado; //en el DER es su entidad padre

    public Nomina(LocalDate fecha_inicio, LocalDate fecha_fin, LocalTime total_horas, Double total_bono, Empleado empleado) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.total_horas = total_horas;
        this.total_bono = total_bono;
        this.empleado = empleado;
    }

    public Nomina(Integer id, LocalDate fecha_inicio, LocalDate fecha_fin, LocalTime total_horas, Double total_bono, Empleado empleado) {
        super(id);
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.total_horas = total_horas;
        this.total_bono = total_bono;
        this.empleado = empleado;
    }
}
