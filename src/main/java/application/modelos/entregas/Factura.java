package application.modelos.entregas;

import application.modelos.Lista;
import application.modelos.entidades.Proveedor;

import java.time.LocalDate;

public class Factura extends Lista <Integer> {
    private LocalDate fecha;
    private Double monto_total;
    private Proveedor proveedor;

    public Factura(LocalDate fecha, Double monto_total, Proveedor proveedor) {
        this.fecha = fecha;
        this.monto_total = monto_total;
        this.proveedor = proveedor;
    }

    public Factura(Integer llave, LocalDate fecha, Double monto_total, Proveedor proveedor) {
        super(llave);
        this.fecha = fecha;
        this.monto_total = monto_total;
        this.proveedor = proveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
