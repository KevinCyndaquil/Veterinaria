package application.models.finanzas;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public abstract class ArticulosGramaje extends Articulos{
    private BigDecimal gramaje;

    public ArticulosGramaje(Integer id_articulo, String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje) {
        super(id_articulo, nombre, monto_compra, descripcion);
        this.gramaje = gramaje;
    }

    public ArticulosGramaje(String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje) {
        super(nombre, monto_compra, descripcion);
        this.gramaje = gramaje;
    }
}
