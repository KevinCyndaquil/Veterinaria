package application.modelos.finanzas;

import application.modelos.Tabla;
import application.modelos.entidades.Empleado;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Nomina extends Tabla {
    @Getter @Setter
    private LocalDate fecha_inicio;
    @Getter @Setter
    private LocalDate fecha_fin;
    @Getter @Setter
    private Integer total_horas;
    @Getter @Setter
    private Double total_bono;
    @Getter @Setter
    private Empleado empleado;

    public Nomina(LocalDate fecha_inicio, LocalDate fecha_fin, Integer total_horas, Double total_bono, Empleado empleado) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.total_horas = total_horas;
        this.total_bono = total_bono;
        this.empleado = empleado;
    }

    public Nomina(Integer cns, LocalDate fecha_inicio, LocalDate fecha_fin, Integer total_horas, Double total_bono, Empleado empleado) {
        super(cns);
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.total_horas = total_horas;
        this.total_bono = total_bono;
        this.empleado = empleado;
    }
}
