package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

public class Producto extends ArticulosProveedor {
   @Getter @Setter
   TiposProducto tipo;

    public Producto(String nombre, Double monto, Proveedores proveedor, TiposProducto tipo) {
        super(nombre, monto, proveedor);
        this.tipo = tipo;
    }

    public Producto(Integer id, String nombre, Double monto, Proveedores proveedor, TiposProducto tipo) {
        super(id, nombre, monto, proveedor);
        this.tipo = tipo;
    }
}
