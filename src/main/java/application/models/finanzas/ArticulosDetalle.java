package application.models.finanzas;

import java.math.BigDecimal;

public class ArticulosDetalle <A extends Articulos> extends Detalle<A>{
    public ArticulosDetalle(A a, Integer cantidad) {
        super(a, cantidad, (Integer i) -> BigDecimal.valueOf(i * a.getMonto_compra().doubleValue()));
    }
}
