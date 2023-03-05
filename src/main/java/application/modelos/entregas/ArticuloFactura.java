package application.modelos.entregas;

import application.modelos.Tabla;
import application.modelos.entidades.Articulo;
import lombok.Getter;
import lombok.Setter;

public class ArticuloFactura extends Tabla {
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private Double monto_subtotal;
    @Getter @Setter
    private Articulo articulo;

    public ArticuloFactura(Integer cantidad, Double monto_subtotal, Articulo articulo) {
        this.cantidad = cantidad;
        this.monto_subtotal = monto_subtotal;
        this.articulo = articulo;
    }

    public ArticuloFactura(Integer cns, Integer cantidad, Double monto_subtotal, Articulo articulo) {
        super(cns);
        this.cantidad = cantidad;
        this.monto_subtotal = monto_subtotal;
        this.articulo = articulo;
    }
}
