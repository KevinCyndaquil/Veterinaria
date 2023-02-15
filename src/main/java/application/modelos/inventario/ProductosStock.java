package application.modelos.inventario;

import application.modelos.Tabla;
import application.modelos.entidades.Productos;

import java.time.LocalDate;

public class ProductosStock extends Tabla <LocalDate> {
    private Integer cantidad;
    private Productos productos;

    public ProductosStock(Integer cantidad, Productos productos) {
        this.cantidad = cantidad;
        this.productos = productos;
    }

    public ProductosStock(LocalDate localDate, Integer cantidad, Productos productos) {
        super(localDate);
        this.cantidad = cantidad;
        this.productos = productos;
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
}
