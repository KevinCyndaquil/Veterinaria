package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@SqlEntity("pagos")
public record Pagos (
        @SqlAttribute
        Integer cns_pago,
        @SqlAttribute
        BigDecimal subtotal,
        @SqlAttribute @SqlKey(SqlKey.FOREIGN_KEY)
        FormasPago forma_pago,
        @SqlAttribute
        LocalDate fecha_cobro,
        @SqlAttribute
        LocalTime hora_cobro) implements Entity {

    @SqlInstance
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
