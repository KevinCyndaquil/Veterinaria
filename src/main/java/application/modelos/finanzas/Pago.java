package application.modelos.finanzas;

import application.modelos.Tabla;

public class Pago extends Tabla<Integer> {
    private Double monto_subtotal;
    private FormaPago forma_pago;

    public Pago(Double monto_subtotal, FormaPago forma_pago) {
        this.monto_subtotal = monto_subtotal;
        this.forma_pago = forma_pago;
    }

    public Pago(Integer id, Double monto_subtotal, FormaPago forma_pago) {
        super(id);
        this.monto_subtotal = monto_subtotal;
        this.forma_pago = forma_pago;
    }
}
