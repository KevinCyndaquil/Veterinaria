package application.modelos.entidades;

public class Producto extends Articulo{
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
