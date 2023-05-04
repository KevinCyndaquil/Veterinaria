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

@Getter @Setter @SqlEntity(value = "alimentos", type = SqlEntity.SPECIALIZED_CLASS)
public class Alimentos implements Entity {
    @SqlAttribute @SqlKey @SqlKey(SqlKey.FOREIGN_KEY)
    private Articulos articulo;
    @SqlAttribute
    private Float gramaje;

    public Alimentos(Integer id_articulo, Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, Float gramaje) {
        articulo = new Articulos(
                id_articulo,
                proveedor,
                nombre,
                monto_compra,
                descripcion);
        this.gramaje = gramaje;
    }

    public Alimentos(Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, Float gramaje) {
        articulo = new Articulos(
                proveedor,
                nombre,
                monto_compra,
                descripcion);
        this.gramaje = gramaje;
    }

    @SqlInstance
    public Alimentos(Articulos articulo, Float gramaje) {
        this.articulo = articulo;
        this.gramaje = gramaje;
    }
}
/*
public class Alimentos extends ArticulosGramaje {
    @SqlInstance
    public Alimentos(Integer id_articulo, Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje) {
        super(id_articulo, proveedor, nombre, monto_compra, descripcion, gramaje);
    }

    public Alimentos(Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje) {
        super(proveedor, nombre, monto_compra, descripcion, gramaje);
    }
}*/
