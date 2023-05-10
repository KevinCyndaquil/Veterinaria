package application.models.detalles;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.finanzas.Articulos;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@SqlEntity("detalle_factura")
public class DetalleFactura extends DetalleArticulo<Articulos> implements Entity {
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    private Articulos articulo;
    @SqlAttribute @SqlKey
    private Integer id_factura;
    @SqlAttribute("cns_detalle_factura") @SqlKey
    private Integer cns;

    @SqlInstance
    public DetalleFactura(Articulos articulo,
                          Integer id_factura,
                          Integer cns,
                          Integer cantidad,
                          BigDecimal subtotal) {
        super(cantidad, subtotal, articulo);
        this.articulo = articulo;
        this.id_factura = id_factura;
        this.cns = cns;
    }

    public DetalleFactura(Articulos articulo, Integer id_factura, Integer cns, Integer cantidad) {
        super(cantidad, articulo);
        this.articulo = articulo;
        this.id_factura = id_factura;
        this.cns = cns;
    }

    public DetalleFactura(Articulos articulo, Integer cantidad) {
        super(cantidad, articulo);
        this.articulo = articulo;
    }

    public DetalleFactura(int id_factura) {
        super(null, null);
        this.id_factura = id_factura;
    }

    public DetalleFactura() {
        super(null, null);
    }

    @Contract("_, _ -> new")
    public static @NotNull DetalleFactura of(@NotNull Articulos articulo, Integer cantidad) {
        return new DetalleFactura(
                articulo,
                cantidad);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Articulos a) return this.articulo.equals(a);
        if (obj instanceof DetalleFactura d) return this.articulo.equals(d.articulo);
        return false;
    }

    @Override
    public int hashCode() {
        return articulo.getId_articulo();
    }

    @Override
    public String toString() {
        return "factura: " + id_factura +"\narticulo: " + articulo + "\ncantidad: " + cantidad() + "\nmonto: $" + monto();
    }
}
