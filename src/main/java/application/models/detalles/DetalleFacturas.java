package application.models.detalles;

import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.entidades.ConMonto;
import application.models.finanzas.Articulos;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@SqlEntity("detalle_factura")
public class DetalleFacturas extends DetalleSubtotal {
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    private Articulos articulo;
    @SqlAttribute @SqlKey
    private Integer id_factura;
    @SqlAttribute("cns_detalle_factura") @SqlKey
    private Integer cns;

    @SqlInstance
    public DetalleFacturas(Articulos articulo, Integer id_factura, Integer cns, Integer cantidad, BigDecimal subtotal) {
        super(cantidad, subtotal, articulo);
        this.articulo = articulo;
        this.id_factura = id_factura;
        this.cns = cns;
    }

    public DetalleFacturas(Articulos articulo, Integer cantidad) {
        super(cantidad, articulo);
        this.articulo = articulo;
        calcular(cantidad);
    }

    @Contract("_, _ -> new")
    public static @NotNull DetalleFacturas valueOf(@NotNull ConMonto conMonto, Integer cantidad) {
        return new DetalleFacturas(
                (Articulos) conMonto,
                cantidad);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof Articulos a)
            return this.articulo.equals(a);
        return false;
    }

    @Override
    public String toString() {
        return "factura: " + id_factura +"\narticulo: " + articulo + "\ncantidad: " + cantidad() + "\nmonto: $" + monto();
    }
}
