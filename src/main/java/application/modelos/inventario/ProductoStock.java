package application.modelos.inventario;

import application.modelos.entidades.Articulo;

import java.time.LocalDate;

public class ProductoStock extends ArticuloStock{
    public ProductoStock (LocalDate caducidad, Integer cantidad, Articulo articulo) {
        super(caducidad, cantidad, articulo);
    }

    public ProductoStock (Integer cns, LocalDate caducidad, Integer cantidad, Articulo articulo) {
        super(cns, caducidad, cantidad, articulo);
    }
}
