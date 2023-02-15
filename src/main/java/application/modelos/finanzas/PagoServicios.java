package application.modelos.finanzas;

import application.modelos.Tabla;

import java.time.LocalDate;

public class PagoServicios extends Tabla<Integer> {
    private LocalDate fecha;
    private Double monto;
    private Servicios servicio;

    public PagoServicios(LocalDate fecha, Double monto, Servicios servicio) {
        this.fecha = fecha;
        this.monto = monto;
        this.servicio = servicio;
    }

    public PagoServicios(Integer integer, LocalDate fecha, Double monto, Servicios servicio) {
        super(integer);
        this.fecha = fecha;
        this.monto = monto;
        this.servicio = servicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }
}
