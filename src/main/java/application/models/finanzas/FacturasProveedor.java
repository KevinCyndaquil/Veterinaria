package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.*;
import application.models.entidades.Proveedores;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SqlEntity("facturas_proveedor")
public class FacturasProveedor extends Facturas implements Entity {

    @Getter
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    private Proveedores proveedor;

    //first: Articulo, second: cantidad
    @Getter
    private final Map<Articulos, Integer> articulos;

    @SqlInstance
    public FacturasProveedor(Proveedores proveedor, Integer id_factura, BigDecimal monto_total, Date fecha_generacion) {
        super(id_factura, monto_total, fecha_generacion);
        this.proveedor = proveedor;
        this.articulos = new HashMap<>();
    }

    public FacturasProveedor(Integer id_factura, Date fecha_generacion, Proveedores proveedor) {
        super(id_factura, fecha_generacion);
        this.proveedor = proveedor;
        this.articulos = new HashMap<>();
    }

    public FacturasProveedor(Date fecha_generacion, Proveedores proveedor) {
        super(fecha_generacion);
        this.proveedor = proveedor;
        this.articulos = new HashMap<>();
    }

    public FacturasProveedor(Integer id_factura) {
        super(id_factura);
        this.articulos = new HashMap<>();
    }

    @Override
    public boolean agregarArticulo(Articulos articulo, Integer cantidad) {

        if (articulo == null || cantidad == null || cantidad < 0) return false;

        if (articulos.containsKey(articulo)) {
            articulos.put(articulo, articulos.get(articulo) + cantidad);
        } else {
            articulos.put(articulo, cantidad);
        }
        setMonto_total(getMonto_total().add(articulo.getMonto_compra().multiply(BigDecimal.valueOf(cantidad))));
        return true;
    }

    @Override
    public boolean agregarArticulos(Map<Articulos, Integer> articulos) {
        if (articulos == null || articulos.isEmpty()) return false;
        articulos.forEach(this::agregarArticulo);
        return true;
    }

    @Override
    public boolean eliminarArticulo(Articulos articulo) {
        if (articulo == null) return false;
        if (articulos.containsKey(articulo)) {
            articulos.remove(articulo);
            return true;
        }
        return false;
    }

    @Override
    public boolean modificarCantidad(Articulos articulo, Integer cantidad) {

        if (articulo == null || cantidad == null || cantidad <= 0) return false;
        if (articulos.containsKey(articulo)) {
            articulos.replace(articulo, cantidad);
            return true;
        }else return false;
    }

    @Override
    public Integer consultarArticulo(Articulos articulo) {
        if (articulo == null) return null;
        return articulos.get(articulo);
    }

    @Override
    public void consultarArticulos() {
        articulos.forEach((articulo, cantidad) -> System.out.println(articulo.getNombre() + " - " + cantidad));
    }
}
