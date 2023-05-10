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
@SqlEntity("medicamentos")
public class Medicamentos implements Entity {
    @SqlAttribute @SqlKey(SqlKey.FOREIGN_KEY)
    private Articulos articulo;
    @SqlAttribute
    private Float gramaje;
    @SqlAttribute
    private String laboratorio;
    @SqlAttribute
    private ViasMedicamento via;

    @SqlInstance
    public Medicamentos(Integer id_articulo, Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, Float gramaje, String laboratorio, ViasMedicamento via) {
        articulo = new Articulos(
                id_articulo,
                proveedor,
                nombre,
                monto_compra,
                descripcion);
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.via = via;
    }

    public Medicamentos(Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, Float gramaje, String laboratorio, ViasMedicamento via) {
        articulo = new Articulos(
                proveedor,
                nombre,
                monto_compra,
                descripcion);
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.via = via;
    }

    @SqlInstance
    public Medicamentos(Articulos articulo, Float gramaje, String laboratorio, ViasMedicamento via) {
        this.articulo = articulo;
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.via = via;
    }
}
