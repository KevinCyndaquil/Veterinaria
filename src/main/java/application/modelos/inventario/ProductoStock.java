package application.modelos.inventario;

import application.modelos.entidades.ArticulosVenta;

import java.time.LocalDate;

public class ProductoStock extends ArticuloStock{
    public ProductoStock (LocalDate caducidad, Integer cantidad, ArticulosVenta articuloVenta) {
        super(caducidad, cantidad, articuloVenta);
    }

    public ProductoStock (Integer cns, LocalDate caducidad, Integer cantidad, ArticulosVenta articuloVenta) {
        super(cns, caducidad, cantidad, articuloVenta);
    }
}
