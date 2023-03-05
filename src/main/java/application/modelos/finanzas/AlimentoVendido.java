package application.modelos.finanzas;

import application.modelos.entidades.Alimento;

public class AlimentoVendido extends ArticuloVendido{
    public AlimentoVendido (Integer cantidad, Double monto_subtotal, Alimento articulo) {
        super(cantidad, monto_subtotal, articulo);
    }

    public AlimentoVendido (Integer id, Integer cantidad, Double monto_subtotal, Alimento articulo) {
        super(id, cantidad, monto_subtotal, articulo);
    }
}
