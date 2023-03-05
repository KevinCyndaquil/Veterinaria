package application.modelos.entregas;

import application.modelos.entidades.Producto;

public class ProductoFactura extends ArticuloFactura{
    public ProductoFactura (Integer cantidad, Double monto_subtotal, Producto articulo) {
        super(cantidad, monto_subtotal, articulo);
    }

    public ProductoFactura (Integer id, Integer cantidad, Double monto_subtotal, Producto articulo) {
        super(id, cantidad, monto_subtotal, articulo);
    }
}
