package application.models.finanzas;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class Pagos {
    private Integer cns_pago;
    private BigDecimal subtotal;
    private FormasPago forma_pago;
    private LocalDate fecha_cobro;
    private LocalTime hora_cobro;

    public Pagos(Integer cns_pago, @NotNull BigDecimal subtotal, @NotNull FormasPago forma_pago, LocalDate fecha_cobro, LocalTime hora_cobro) {
        this.cns_pago = cns_pago;
        this.forma_pago = forma_pago;
        this.subtotal = subtotal.subtract(forma_pago.getComision());
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
    }

    public Pagos(@NotNull BigDecimal subtotal, @NotNull FormasPago forma_pago, LocalDate fecha_cobro, LocalTime hora_cobro) {
        this.forma_pago = forma_pago;
        this.subtotal = subtotal.subtract(forma_pago.getComision());
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
    }
}
