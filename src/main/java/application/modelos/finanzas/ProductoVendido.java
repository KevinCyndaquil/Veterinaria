package application.modelos.finanzas;

import application.modelos.Lista;
import application.modelos.Tabla;

public class ProductoVendido extends Lista <Integer> {
    private Integer cantidad;
    private Double subtotal;
    private Tabla <?> producto;
    private Tabla <?> ticket;

    public ProductoVendido(Integer cantidad, Double subtotal, Tabla <?> producto, Tabla <?> ticket) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
        this.ticket = ticket;
    }

    public ProductoVendido(Integer llave, Integer cantidad, Double subtotal, Tabla <?> producto, Tabla <?> ticket) {
        super(llave);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
        this.ticket = ticket;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Tabla <?> getProducto() {
        return producto;
    }

    public void setProducto(Tabla <?> producto) {
        this.producto = producto;
    }

    public Tabla <?> getTicket() {
        return ticket;
    }

    public void setTicket(Tabla <?> ticket) {
        this.ticket = ticket;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
