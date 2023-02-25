package application.modelos.inventario;

import application.modelos.Tabla;
import application.modelos.entidades.Articulo;

import java.time.LocalDate;

public class ArticuloStock extends Tabla<LocalDate> {
    private Integer cantidad;
    private Articulo articulo;

    public ArticuloStock(Integer cantidad, Articulo articulo) {
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public ArticuloStock(LocalDate id, Integer cantidad, Articulo articulo) {
        super(id);
        this.cantidad = cantidad;
        this.articulo = articulo;
    }
}
