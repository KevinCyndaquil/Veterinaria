package application.modelos.finanzas;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

public class Pago extends Tabla {
    @Getter @Setter
    private Double monto_subtotal;
    @Getter @Setter
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
