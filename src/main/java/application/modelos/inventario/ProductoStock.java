package application.modelos.inventario;

import application.modelos.Lista;
import application.modelos.entidades.Producto;

import java.time.LocalDate;

public class ProductoStock extends Lista <LocalDate> {
    private Integer cantidad;
    private Producto producto;

    public ProductoStock(Integer cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public ProductoStock(LocalDate caducidad, Integer cantidad, Producto producto) {
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
