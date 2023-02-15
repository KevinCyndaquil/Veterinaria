package application.modelos.entregas;

import application.modelos.Tabla;
import application.modelos.entidades.Proveedores;

import java.time.LocalDate;

public class Facturas extends Tabla <Integer> {
    private LocalDate fecha;
    private Double monto_total;
    private Proveedores proveedor;

    public Facturas(LocalDate fecha, Double monto_total, Proveedores proveedor) {
        this.fecha = fecha;
        this.monto_total = monto_total;
        this.proveedor = proveedor;
    }

    public Facturas(Integer integer, LocalDate fecha, Double monto_total, Proveedores proveedor) {
        super(integer);
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

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }
}
