package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.finanzas.Articulos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@SqlEntity("productos")
public class Productos implements Entity {
    @SqlAttribute @SqlKey(SqlKey.FOREIGN_KEY)
    private Articulos articulo;
    @SqlAttribute
    private TiposProducto tipo;

    public Productos(Integer id_articulo, Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, TiposProducto tipo) {
        articulo = new Articulos(
                id_articulo,
                proveedor,
                nombre,
                monto_compra,
                descripcion);
        this.tipo = tipo;
    }

    public Productos(Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, TiposProducto tipo) {
        articulo = new Articulos(
                proveedor,
                nombre,
                monto_compra,
                descripcion);
        this.tipo = tipo;
    }

    @SqlInstance
    public Productos(Articulos articulo, TiposProducto tipo) {
        this.articulo = articulo;
        this.tipo = tipo;
    }
}
