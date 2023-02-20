package application.modelos.entregas;

import application.modelos.Lista;
import application.modelos.entidades.Alimento;

public class AlimentoFactura extends Lista <Integer> {
    private Double subtotal;
    private Integer cantidad;
    private Factura factura;
    private Alimento alimento;

    public AlimentoFactura(Double subtotal, Integer cantidad, Factura factura, Alimento alimento) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
        this.alimento = alimento;
    }

    public AlimentoFactura(Integer llave, Double subtotal, Integer cantidad, Factura factura, Alimento alimento) {
        super(llave);
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }
}
