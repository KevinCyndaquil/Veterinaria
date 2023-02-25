package application.modelos.entregas;

import application.modelos.Tabla;
import application.modelos.entidades.Articulo;

public class ArticuloFactura extends Tabla<Integer> {
    private Integer cantidad;
    private Double monto_subtotal;
    private Articulo articulo;

    public ArticuloFactura(Integer cantidad, Double monto_subtotal, Articulo articulo) {
        this.cantidad = cantidad;
        this.monto_subtotal = monto_subtotal;
        this.articulo = articulo;
    }

    public ArticuloFactura(Integer id, Integer cantidad, Double monto_subtotal, Articulo articulo) {
        super(id);
        this.cantidad = cantidad;
        this.monto_subtotal = monto_subtotal;
        this.articulo = articulo;
    }
}
