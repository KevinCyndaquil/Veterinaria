package application.modelos.inventario;

import application.modelos.entidades.Articulo;

import java.time.LocalDate;

public class AlimentoStock extends ArticuloStock{
    public AlimentoStock (LocalDate caducidad, Integer cantidad, Articulo articulo) {
        super(caducidad, cantidad, articulo);
    }

    public AlimentoStock (Integer cns, LocalDate caducidad, Integer cantidad, Articulo articulo) {
        super(cns, caducidad, cantidad, articulo);
    }
}
