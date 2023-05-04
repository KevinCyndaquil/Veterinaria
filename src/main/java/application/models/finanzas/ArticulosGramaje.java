package application.models.finanzas;

import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.entidades.Proveedores;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ArticulosGramaje extends Articulos{
    @SqlAttribute
    private BigDecimal gramaje;

    public ArticulosGramaje(Integer id_articulo, Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje) {
        super(id_articulo, proveedor, nombre, monto_compra, descripcion);
        this.gramaje = gramaje;
    }

    public ArticulosGramaje(Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje) {
        super(proveedor, nombre, monto_compra, descripcion);
        this.gramaje = gramaje;
    }
}
