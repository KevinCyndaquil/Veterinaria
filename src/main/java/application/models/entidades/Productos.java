package application.models.entidades;

import application.models.finanzas.Articulos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Productos extends Articulos {

    private TiposProducto tipo;

    public Productos(Integer id_articulo, String nombre, BigDecimal monto_compra, String descripcion) {
        super(id_articulo, nombre, monto_compra, descripcion);
    }

    public Productos(String nombre, BigDecimal monto_compra, String descripcion) {
        super(nombre, monto_compra, descripcion);
    }
}
