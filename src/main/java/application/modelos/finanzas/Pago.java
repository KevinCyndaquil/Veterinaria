package application.modelos.finanzas;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class Pago extends Tabla {
    @Getter @Setter
    private FormaPago forma_pago;
    @Getter @Setter
    private Double monto_subtotal;

    public Pago(@NotNull FormaPago forma_pago, Double monto_subtotal) {
        this.forma_pago = forma_pago;
        this.monto_subtotal = monto_subtotal - forma_pago.getComision();
    }

    public Pago(Integer id, @NotNull FormaPago forma_pago, Double monto_subtotal) {
        super(id);
        this.forma_pago = forma_pago;
        this.monto_subtotal = monto_subtotal - forma_pago.getComision();
    }
}
