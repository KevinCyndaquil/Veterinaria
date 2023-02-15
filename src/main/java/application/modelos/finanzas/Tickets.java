package application.modelos.finanzas;

import application.modelos.Tabla;

import java.time.LocalDate;
import java.time.LocalTime;

public class Tickets extends Tabla <Integer> {
    private Double monto_total;
    private LocalDate fecha_cobro;
    private LocalTime hora_cobro;

    public Tickets(Double monto_total, LocalDate fecha_cobro, LocalTime hora_cobro) {
        this.monto_total = monto_total;
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
    }

    public Tickets(Integer integer, Double monto_total, LocalDate fecha_cobro, LocalTime hora_cobro) {
        super(integer);
        this.monto_total = monto_total;
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }

    public LocalDate getFecha_cobro() {
        return fecha_cobro;
    }

    public void setFecha_cobro(LocalDate fecha_cobro) {
        this.fecha_cobro = fecha_cobro;
    }

    public LocalTime getHora_cobro() {
        return hora_cobro;
    }

    public void setHora_cobro(LocalTime hora_cobro) {
        this.hora_cobro = hora_cobro;
    }
}
