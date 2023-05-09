package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.*;
import application.models.detalles.DetalleFacturas;
import application.models.entidades.ConCantidad;
import application.models.entidades.ConMonto;
import application.models.entidades.Proveedores;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SqlEntity("facturas_proveedor")
public class FacturasProveedor extends Facturas implements Entity {

    @Getter
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    private Proveedores proveedor;

    //first: Articulo, second: cantidad
    @Getter
    private final List<DetalleFacturas> detalle;

    @SqlInstance
    public FacturasProveedor(Proveedores proveedor, Integer id_factura, BigDecimal monto_total, Date fecha_generacion) {
        super(id_factura, monto_total, fecha_generacion);
        this.proveedor = proveedor;
        this.detalle = new ArrayList<>();
    }

    public FacturasProveedor(Integer id_factura, Date fecha_generacion, Proveedores proveedor) {
        super(id_factura, fecha_generacion);
        this.proveedor = proveedor;
        this.detalle = new ArrayList<>();
    }

    public FacturasProveedor(Date fecha_generacion, Proveedores proveedor) {
        super(fecha_generacion);
        this.proveedor = proveedor;
        this.detalle = new ArrayList<>();
    }

    public FacturasProveedor(Integer id_factura) {
        super(id_factura);
        this.detalle = new ArrayList<>();
    }

    @Override
    public boolean agregarArticulo(ConMonto articulo, Integer cantidad) {
        if (articulo == null)
            return false;

        DetalleFacturas df = DetalleFacturas.valueOf(articulo, cantidad);
        df.setId_factura(getId_factura());

        detalle.add(df);
        return true;
    }

    @Override
    public boolean agregarArticulos(Collection<ConCantidad> articulos) {
        if (articulos == null)
            return false;

        articulos.forEach(a -> detalle.add((DetalleFacturas) a));
        return true;
    }

    @Override
    public boolean eliminarArticulo(ConMonto articulo) {
        if (articulo == null)
            return false;

        if (detalle.contains(DetalleFacturas.valueOf(articulo, 0))) {
            detalle.forEach(d -> {
                if (d.getArticulo().equals(articulo))
                    detalle.remove(d);
            });
        }

        return true;
    }

    @Override
    public boolean eliminarArticulos() {
        detalle.clear();

        return true;
    }

    @Override
    public boolean modificarCantidad(ConMonto articulo, Integer cantidad) {
        if (articulo == null || cantidad == null || cantidad <= 0) return false;

        detalle.forEach(d -> {
            if (d.getArticulo().equals(articulo))
                d.cantidad(cantidad);
        });
        this.monto(this.monto().add(
                articulo.monto().multiply(
                        BigDecimal.valueOf(cantidad))).floatValue());
        return true;
    }

    @Override
    public Integer consultarArticulo(ConMonto articulo) {
        if (articulo == null)
            return null;

        for (DetalleFacturas d : detalle) {
            if (d.getArticulo().equals(articulo))
                return detalle.indexOf(d);
        }

        return 0;
    }

    @Override
    public void consultarArticulos() {
        detalle.forEach((d) -> System.out.println("Articulo: " + d.getArticulo() +
                "\nCantidad: " + d.cantidad() +
                "\nMonto: $" + d.monto()));
    }

    @Override
    public String toString() {
        return getId_factura().toString();
    }
}
