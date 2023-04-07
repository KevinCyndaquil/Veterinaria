package application.modelos.finanzas;

import application.modelos.entidades.ArticuloProveedor;
import application.modelos.entidades.ArticuloVenta;
import lombok.Getter;
import lombok.Setter;

public class ArticuloVendido extends ArticuloVenta {
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private Double monto_subtotal;

    public ArticuloVendido(ArticuloProveedor articulo, Double montoVenta, Integer stock, String descripcion, Integer cantidad, String tipo) {
        super(articulo, montoVenta, stock, descripcion, tipo);
        this.cantidad = cantidad;
        this.monto_subtotal = cantidad * getMontoVenta();
    }

    public ArticuloVendido(Integer id, ArticuloProveedor articulo, Double montoVenta, Integer stock, String descripcion, Integer cantidad, String tipo) {
        super(id, articulo, montoVenta, stock, descripcion, tipo);
        this.cantidad = cantidad;
        this.monto_subtotal = cantidad * getMontoVenta();
    }
}
