package application.modelos.entregas;

import application.modelos.Lista;
import application.modelos.Tabla;

import java.time.LocalDate;

public class Factura extends Lista <Integer> {
    private LocalDate fecha_factura;
    private Double monto_total;
    private Tabla <?> proveedor;

    public Factura(LocalDate fecha_factura, Double monto_total, Tabla <?> proveedor) {
        this.fecha_factura = fecha_factura;
        this.monto_total = monto_total;
        this.proveedor = proveedor;
    }

    public Factura(Integer llave, LocalDate fecha_factura, Double monto_total, Tabla <?> proveedor) {
        super(llave);
        this.fecha_factura = fecha_factura;
        this.monto_total = monto_total;
        this.proveedor = proveedor;
    }

    public LocalDate getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(LocalDate fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }

    public Tabla <?> getProveedor() {
        return proveedor;
    }

    public void setProveedor(Tabla <?> proveedor) {
        this.proveedor = proveedor;
    }
}
