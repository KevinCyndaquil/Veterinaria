package application.modelos.finanzas;

import application.modelos.entidades.Empleados;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Nominas {
    private Integer cns_nomina;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Integer total_horas;
    private Double total_bono;
    private Empleados empleado;
    private List<Deducciones> deducciones;

    public Nominas(LocalDate fecha_inicio, LocalDate fecha_fin, Integer total_horas, Double total_bono, Empleados empleado, List<Deducciones> deducciones) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.total_horas = total_horas;
        this.total_bono = total_bono;
        this.empleado = empleado;
        this.deducciones = deducciones;
    }

    public Nominas(Integer cns, LocalDate fecha_inicio, LocalDate fecha_fin, Integer total_horas, Double total_bono, Empleados empleado, List<Deducciones> deducciones) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.total_horas = total_horas;
        this.total_bono = total_bono;
        this.empleado = empleado;
        this.deducciones = deducciones;
    }
}
