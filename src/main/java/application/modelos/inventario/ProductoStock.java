package application.modelos.inventario;

import application.modelos.Lista;
import application.modelos.Tabla;

import java.time.LocalDate;

public class ProductoStock extends Lista <LocalDate> {
    private Integer cantidad;
    private Tabla <?> producto;

    public ProductoStock(Integer cantidad, Tabla <?> producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public ProductoStock(LocalDate caducidad, Integer cantidad, Tabla <?> producto) {
        super(caducidad);
        this.cantidad = cantidad;
        this.producto = producto;
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
}
