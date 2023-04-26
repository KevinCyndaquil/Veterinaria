package application.models.finanzas;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record Pagos (
        Integer cns_pago,
        BigDecimal subtotal,
        FormasPago forma_pago,
        LocalDate fecha_cobro,
        LocalTime hora_cobro) {

    public Pagos(Integer cns_pago, @NotNull BigDecimal subtotal, @NotNull FormasPago forma_pago, LocalDate fecha_cobro, LocalTime hora_cobro) {
        this.cns_pago = cns_pago;
        this.subtotal = subtotal.subtract(forma_pago.comision());
        this.forma_pago = forma_pago;
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
    }

    public Pagos(BigDecimal subtotal, FormasPago forma_pago, LocalDate fecha_cobro, LocalTime hora_cobro) {
        this(null, subtotal, forma_pago, fecha_cobro, hora_cobro);
    }
}
