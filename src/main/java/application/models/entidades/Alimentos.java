package application.models.entidades;

import application.models.finanzas.ArticulosGramaje;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class Alimentos extends ArticulosGramaje {
    public Alimentos(Integer id_articulo, String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje) {
        super(id_articulo, nombre, monto_compra, descripcion, gramaje);
    }

    public Alimentos(String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje) {
        super(nombre, monto_compra, descripcion, gramaje);
    }
}
