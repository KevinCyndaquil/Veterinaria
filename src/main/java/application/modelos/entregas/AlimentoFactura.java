package application.modelos.entregas;

import application.modelos.entidades.Alimento;

public class AlimentoFactura extends ArticuloFactura{
    public AlimentoFactura (Integer cantidad, Double monto_subtotal, Alimento articulo) {
        super(cantidad, monto_subtotal, articulo);
    }

    public AlimentoFactura (Integer id, Integer cantidad, Double monto_subtotal, Alimento articulo) {
        super(id, cantidad, monto_subtotal, articulo);
    }
}
