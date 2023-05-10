package application.models.detalles;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.entidades.ConMonto;
import application.models.finanzas.FormasPago;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@SqlEntity("pagos")
public record Pagos (
        @SqlAttribute
        Integer cns_pago,
        @SqlAttribute
        BigDecimal subtotal,
        @SqlAttribute @SqlKey(SqlKey.FOREIGN_KEY)
        FormasPago forma_pago,
        @SqlAttribute
        Date fecha_cobro,
        @SqlAttribute
        Time hora_cobro,
        @SqlAttribute @SqlKey
        Integer id_ticket,
        @SqlAttribute("cns_pago") @SqlKey
        Integer cns) implements Entity, ConMonto {

    @SqlInstance
    public Pagos {
    }

    public Pagos(BigDecimal subtotal,
                 FormasPago forma_pago,
                 Date fecha_cobro,
                 Time hora_cobro) {
        this(null, subtotal, forma_pago, fecha_cobro, hora_cobro, null, null);
    }

    @Override
    public @NotNull BigDecimal monto(BigDecimal monto) {
        return subtotal().subtract(subtotal).add(monto);
    }

    @Override
    public BigDecimal monto() {
        return subtotal;
    }
}
