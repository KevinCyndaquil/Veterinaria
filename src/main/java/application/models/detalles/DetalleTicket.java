package application.models.detalles;

import application.models.Entity_Manager.abstract_manager.Entity;
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
public class DetalleTicket extends DetalleArticulo<ArticulosVenta> implements Entity {
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
        super(cantidad, articulosVenta);
        this.articuloVenta = articulosVenta;
    }

    @Contract("_, _ -> new")
    public static @NotNull DetalleTicket valueOf(@NotNull ConMonto conMonto, Integer cantidad) {
        return new DetalleTicket(
                (ArticulosVenta) conMonto,
                cantidad);
    }

    @Override
    public Integer cantidad(int cantidad) {
        if (cantidad > articuloVenta.getStock()) return -1;
        return super.cantidad(cantidad);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof DetalleTicket d)
            return this.articuloVenta.equals(d.getArticuloVenta());
        return false;
    }

    @Override
    public int hashCode() {
        return articuloVenta.getArticulo().getId_articulo();
    }

    @Override
    public String toString() {
        return "ticket: " + id_ticket +"\narticulo: " + articuloVenta + "\ncantidad: " + cantidad() +
                "\nmonto: $" + monto();
    }
}
