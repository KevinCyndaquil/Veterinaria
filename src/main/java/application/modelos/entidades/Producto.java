package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

public class Producto extends Articulo{
   @Getter @Setter
   TiposProducto tipo;

    public Producto(String nombre, Double monto, String descripcion, TiposProducto tipo) {
        super(nombre, monto, descripcion);
        this.tipo = tipo;
    }

    public Producto(Integer id, String nombre, Double monto, String descripcion, TiposProducto tipo) {
        super(id, nombre, monto, descripcion);
        this.tipo = tipo;
    }
}
