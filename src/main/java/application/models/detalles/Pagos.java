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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@SqlEntity("pagos")
public class Pagos implements Entity, ConMonto {
    @Getter @Setter
    @SqlAttribute
    private Integer cns_pago;
    @SqlAttribute
    private BigDecimal subtotal;
    @Getter @Setter
    @SqlAttribute @SqlKey(SqlKey.FOREIGN_KEY)
    private FormasPago forma_pago;
    @Getter @Setter
    @SqlAttribute
    private LocalDate fecha_cobro;
    @Getter @Setter
    @SqlAttribute
    private LocalTime hora_cobro;
    @Getter @Setter
    @SqlAttribute @SqlKey
    private Integer id_ticket;
    @Getter @Setter
    @SqlAttribute("cns_pago") @SqlKey
    private Integer cns;

    @SqlInstance
    public Pagos(BigDecimal subtotal,
                 FormasPago forma_pago,
                 LocalDate fecha_cobro,
                 LocalTime hora_cobro,
                 Integer id_ticket,
                 Integer cns) {
        //this.cns_pago = cns_pago;
        this.subtotal = subtotal;
        this.forma_pago = forma_pago;
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
        this.id_ticket = id_ticket;
        this.cns = cns;
    }

    public Pagos(BigDecimal subtotal,
                 FormasPago forma_pago,
                 LocalDate fecha_cobro,
                 LocalTime hora_cobro) {
        //this.cns_pago = null;
        this.subtotal = subtotal;
        this.forma_pago = forma_pago;
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
    }

    @Override
    public void monto(Float monto) {
        subtotal = new BigDecimal(monto);
    }

    @Override
    public BigDecimal monto() {
        return subtotal;
    }
}
