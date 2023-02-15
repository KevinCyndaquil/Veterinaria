package application.modelos.entregas;

import application.modelos.Tabla;
import application.modelos.entidades.Productos;

public class ProductosFactura extends Tabla <Integer> {
    private Double subtotal;
    private Integer cantidad;
    private Facturas factura;
    private Productos productos;

    public ProductosFactura(Double subtotal, Integer cantidad, Facturas factura, Productos productos) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
        this.productos = productos;
    }

    public ProductosFactura(Integer integer, Double subtotal, Integer cantidad, Facturas factura, Productos productos) {
        super(integer);
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
        this.productos = productos;
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

    public Productos getProducto() {
        return productos;
    }

    public void setProducto(Productos productos) {
        this.productos = productos;
    }
}
