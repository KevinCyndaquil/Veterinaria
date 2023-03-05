package application.modelos.inventario;

import application.modelos.entidades.Articulo;

import java.time.LocalDate;

public class MedicamentoStock extends ArticuloStock{
    public MedicamentoStock (LocalDate caducidad, Integer cantidad, Articulo articulo) {
        super(caducidad, cantidad, articulo);
    }

    public MedicamentoStock (Integer cns, LocalDate caducidad, Integer cantidad, Articulo articulo) {
        super(cns, caducidad, cantidad, articulo);
    }
}
