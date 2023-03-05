package application.modelos.entregas;

import application.modelos.entidades.Medicamento;

public class MedicamentoFactura extends ArticuloFactura{
    public MedicamentoFactura (Integer cantidad, Double monto_subtotal, Medicamento articulo) {
        super(cantidad, monto_subtotal, articulo);
    }

    public MedicamentoFactura (Integer id, Integer cantidad, Double monto_subtotal, Medicamento articulo) {
        super(id, cantidad, monto_subtotal, articulo);
    }
}
