package application.modelos.entregas;

import application.modelos.Tabla;
import application.modelos.entidades.Alimentos;

public class AlimentosFactura extends Tabla <Integer> {
    private Double subtotal;
    private Integer cantidad;
    private Facturas factura;
    private Alimentos alimento;

    public AlimentosFactura(Double subtotal, Integer cantidad, Facturas factura, Alimentos alimento) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
        this.alimento = alimento;
    }

    public AlimentosFactura(Integer integer, Double subtotal, Integer cantidad, Facturas factura, Alimentos alimento) {
        super(integer);
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
        this.alimento = alimento;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public Alimentos getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimentos alimento) {
        this.alimento = alimento;
    }
}
