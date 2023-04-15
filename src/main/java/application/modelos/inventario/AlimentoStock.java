package application.modelos.inventario;

import application.modelos.entidades.ArticulosVenta;

import java.time.LocalDate;

public class AlimentoStock extends ArticuloStock{
    public AlimentoStock (LocalDate caducidad, Integer cantidad, ArticulosVenta articuloVenta) {
        super(caducidad, cantidad, articuloVenta);
    }

    public AlimentoStock (Integer cns, LocalDate caducidad, Integer cantidad, ArticulosVenta articuloVenta) {
        super(cns, caducidad, cantidad, articuloVenta);
    }
}
