package application.modelos.inventario;

import application.modelos.entidades.ArticuloVenta;

import java.time.LocalDate;

public class MedicamentoStock extends ArticuloStock{
    public MedicamentoStock (LocalDate caducidad, Integer cantidad, ArticuloVenta articuloVenta) {
        super(caducidad, cantidad, articuloVenta);
    }

    public MedicamentoStock (Integer cns, LocalDate caducidad, Integer cantidad, ArticuloVenta articuloVenta) {
        super(cns, caducidad, cantidad, articuloVenta);
    }
}
