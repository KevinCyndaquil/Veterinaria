package application.modelos.finanzas;

import application.modelos.entidades.Producto;

public class ProductoVendido extends ArticuloVendido{
    public ProductoVendido (Integer cantidad, Double monto_subtotal, Producto articulo) {
        super(cantidad, monto_subtotal, articulo);
    }

    public ProductoVendido (Integer id, Integer cantidad, Double monto_subtotal, Producto articulo) {
        super(id, cantidad, monto_subtotal, articulo);
    }
}
