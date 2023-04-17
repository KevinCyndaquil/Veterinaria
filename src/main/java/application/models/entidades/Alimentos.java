package application.models.entidades;

import application.models.finanzas.Articulos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class Alimentos extends Articulos {

    private BigDecimal gramaje;


    public Alimentos(Integer id_articulo, String nombre, BigDecimal monto_compra, String descripcion) {
        super(id_articulo, nombre, monto_compra, descripcion);
    }

    public Alimentos(String nombre, BigDecimal monto_compra, String descripcion) {
        super(nombre, monto_compra, descripcion);
    }
}
