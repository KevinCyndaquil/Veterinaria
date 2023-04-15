package application.modelos.inventario;

import application.modelos.entidades.ArticulosVenta;

import java.time.LocalDate;

public class MedicamentoStock extends ArticuloStock{
    public MedicamentoStock (LocalDate caducidad, Integer cantidad, ArticulosVenta articuloVenta) {
        super(caducidad, cantidad, articuloVenta);
    }

    public MedicamentoStock (Integer cns, LocalDate caducidad, Integer cantidad, ArticulosVenta articuloVenta) {
        super(cns, caducidad, cantidad, articuloVenta);
    }
}
