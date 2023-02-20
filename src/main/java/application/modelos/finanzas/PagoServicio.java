package application.modelos.finanzas;

import application.modelos.Lista;

import java.time.LocalDate;

public class PagoServicio extends Lista <Integer> {
    private LocalDate fecha;
    private Double monto;
    private Servicio servicio;

    public PagoServicio(LocalDate fecha, Double monto, Servicio servicio) {
        this.fecha = fecha;
        this.monto = monto;
        this.servicio = servicio;
    }

    public PagoServicio(Integer llave, LocalDate fecha, Double monto, Servicio servicio) {
        super(llave);
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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
