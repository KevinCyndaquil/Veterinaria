package application.modelos.finanzas;

import application.modelos.Lista;
import application.modelos.entidades.Producto;

public class ProductoComprado extends Lista <Integer> {
    private Integer cantidad;
    private Producto producto;
    private Ticket ticket;

    public ProductoComprado(Integer cantidad, Producto producto, Ticket ticket) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.ticket = ticket;
    }

    public ProductoComprado(Integer llave, Integer cantidad, Producto producto, Ticket ticket) {
        super(llave);
        this.cantidad = cantidad;
        this.producto = producto;
        this.ticket = ticket;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
