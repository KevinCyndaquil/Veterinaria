package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.entidades.ConMonto;
import application.models.entidades.Proveedores;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;


@SqlEntity(value = "articulos", type = SqlEntity.GENERALIZED_CLASS)
public class Articulos implements
        Entity,
        ConMonto {
    public static final String ALIMENTO = "alimento";
    public static final String MEDICAMENTO = "medicamento";
    public static final String PRODUCTO = "producto";

    @Getter @Setter
    @SqlAttribute @SqlKey
    private Integer id_articulo;
    @Getter @Setter
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    private Proveedores proveedor;
    @Getter @Setter
    @SqlAttribute
    private String nombre;
    @SqlAttribute
    private BigDecimal monto_compra;
    @Getter @Setter
    @SqlAttribute
    private String descripcion;
    @Getter @Setter
    private String tipo;

    @SqlInstance
    public Articulos(Integer id_articulo, Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion) {
        this.id_articulo = id_articulo;
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.monto_compra = monto_compra;
        this.descripcion = descripcion;
    }

    public Articulos(Proveedores proveedor, String nombre, BigDecimal monto_compra, String descripcion) {
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.monto_compra = monto_compra;
        this.descripcion = descripcion;
    }

    public Articulos(Integer id_articulo) {
        this.id_articulo = id_articulo;
        this.monto_compra = BigDecimal.ZERO;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Articulos articulos = (Articulos) obj;
        return Objects.equals(id_articulo, articulos.id_articulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_articulo);
    }

    @Override
    public String toString() {
        return id_articulo + ", " + nombre + ", " + monto_compra + ", " + descripcion + ": " + proveedor;
    }

    @Override
    public void monto(Float monto) {
        monto_compra = new BigDecimal(monto);
    }

    @Override
    public BigDecimal monto() {
        return monto_compra;
    }
}
