package application.modelos.finanzas;

import application.modelos.entidades.Medicamento;

public class MedicamentoVendido extends ArticuloVendido{
    public MedicamentoVendido (Integer cantidad, Double monto_subtotal, Medicamento articulo) {
        super(cantidad, monto_subtotal, articulo);
    }

    public MedicamentoVendido (Integer id, Integer cantidad, Double monto_subtotal, Medicamento articulo) {
        super(id, cantidad, monto_subtotal, articulo);
    }
}
