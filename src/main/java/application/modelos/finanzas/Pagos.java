package application.modelos.finanzas;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Setter
@Getter
public class Pagos {
    private Integer cns_pago;
    private BigDecimal subtotal;
    private FormasPago forma_pago;

    public Pagos(Integer cns_pago, BigDecimal subtotal, FormasPago forma_pago) {
        this.cns_pago = cns_pago;
        this.forma_pago = forma_pago;
        this.subtotal = subtotal.subtract(forma_pago.getComision());
    }

    public Pagos(BigDecimal subtotal, FormasPago forma_pago) {
        this.forma_pago = forma_pago;
        this.subtotal = subtotal.subtract(forma_pago.getComision());
    }
}
