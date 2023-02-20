package application.modelos.entregas;

import application.modelos.Lista;
import application.modelos.entidades.Producto;

public class ProductoFactura extends Lista <Integer> {
    private Double subtotal;
    private Integer cantidad;
    private Factura factura;
    private Producto producto;

    public ProductoFactura(Double subtotal, Integer cantidad, Factura factura, Producto producto) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
        this.producto = producto;
    }

    public ProductoFactura(Integer llave, Double subtotal, Integer cantidad, Factura factura, Producto producto) {
        super(llave);
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
        this.producto = producto;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
