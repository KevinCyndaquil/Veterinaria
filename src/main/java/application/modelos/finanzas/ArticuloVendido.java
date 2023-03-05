package application.modelos.finanzas;

import application.modelos.Tabla;
import application.modelos.entidades.Articulo;
import lombok.Getter;
import lombok.Setter;

public class ArticuloVendido extends Tabla {
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private Double monto_subtotal;
    @Getter @Setter
    private Articulo articulo;

    public ArticuloVendido(Integer cantidad, Double monto_subtotal, Articulo articulo) {
        this.cantidad = cantidad;
        this.monto_subtotal = monto_subtotal;
        this.articulo = articulo;
    }

    public ArticuloVendido(Integer cns, Integer cantidad, Double monto_subtotal, Articulo articulo) {
        super(cns);
        this.cantidad = cantidad;
        this.monto_subtotal = monto_subtotal;
        this.articulo = articulo;
    }
}
