package application.models.detalles;

import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.entidades.ConMonto;
import application.models.finanzas.ArticulosVenta;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@SqlEntity("detalle_ticket")
public class DetalleTicket extends DetalleSubtotal{
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    private ArticulosVenta articuloVenta;
    @SqlAttribute @SqlKey
    private Integer id_ticket;
    @SqlAttribute("cns_detalle_ticket") @SqlKey
    private Integer cns;

    @SqlInstance
    public DetalleTicket(ArticulosVenta articuloVenta, Integer cns, Integer cantidad, BigDecimal subtotal) {
        super(cantidad, subtotal, articuloVenta);
        this.articuloVenta = articuloVenta;
        this.cns = cns;
    }

    public DetalleTicket(ArticulosVenta articulosVenta, Integer cantidad) {
        super(cantidad, null, articulosVenta);
        this.articuloVenta = articulosVenta;
    }

    @Contract("_, _ -> new")
    public static @NotNull DetalleTicket valueOf(@NotNull ConMonto conMonto, Integer cantidad) {
        return new DetalleTicket(
                (ArticulosVenta) conMonto,
                cantidad);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof ArticulosVenta a)
            return this.articuloVenta.equals(a);
        return false;
    }
}
