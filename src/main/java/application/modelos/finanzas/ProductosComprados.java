package application.modelos.finanzas;

import application.modelos.Tabla;
import application.modelos.entidades.Productos;

public class ProductosComprados extends Tabla <Integer> {
    private Integer cantidad;
    private Productos productos;
    private Tickets ticket;

    public ProductosComprados(Integer cantidad, Productos productos, Tickets ticket) {
        this.cantidad = cantidad;
        this.productos = productos;
        this.ticket = ticket;
    }

    public ProductosComprados(Integer integer, Integer cantidad, Productos productos, Tickets ticket) {
        super(integer);
        this.cantidad = cantidad;
        this.productos = productos;
        this.ticket = ticket;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getProducto() {
        return productos;
    }

    public void setProducto(Productos productos) {
        this.productos = productos;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }
}
