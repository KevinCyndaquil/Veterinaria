package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.*;
import application.models.detalles.DetalleFactura;
import application.models.entidades.Proveedores;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SqlEntity("facturas_proveedor")
public class FacturasProveedor extends Facturas implements
        Entity,
        IGestorArticulos<DetalleFactura> {

    @Getter
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    private Proveedores proveedor;

    //first: Articulo, second: cantidad
    @Getter
    private final List<DetalleFactura> detalleArticulos;

    @SqlInstance
    public FacturasProveedor(Proveedores proveedor, Integer id_factura, BigDecimal monto_total, Date fecha_generacion) {
        super(id_factura, monto_total, fecha_generacion);
        this.proveedor = proveedor;
        this.detalleArticulos = new ArrayList<>();
    }

    public FacturasProveedor(Integer id_factura, Date fecha_generacion, Proveedores proveedor) {
        super(id_factura, fecha_generacion);
        this.proveedor = proveedor;
        this.detalleArticulos = new ArrayList<>();
    }

    public FacturasProveedor(Date fecha_generacion, Proveedores proveedor) {
        super(fecha_generacion);
        this.proveedor = proveedor;
        this.detalleArticulos = new ArrayList<>();
    }

    public FacturasProveedor(Integer id_factura) {
        super(id_factura);
        this.detalleArticulos = new ArrayList<>();
    }

    @Override
    public boolean agregarArticulo(DetalleFactura detalleFactura) {
        if (detalleFactura == null) return false;

        monto(monto().add(detalleFactura.monto()));
        return detalleArticulos.add(detalleFactura);
    }

    @Override
    public boolean agregarArticulos(Collection<DetalleFactura> collection) {
        if (collection == null) return false;
        if (collection.isEmpty()) return false;

        collection.forEach(this::agregarArticulo);
        return true;
    }

    @Override
    public boolean eliminarArticulo(DetalleFactura detalleFactura) {
        if (detalleFactura == null) return false;

        monto(monto().subtract(detalleFactura.monto()));
        return detalleArticulos.remove(detalleFactura);
    }

    @Override
    public boolean eliminarArticulos() {
        detalleArticulos.clear();
        monto(BigDecimal.ZERO);
        return true;
    }

    @Override
    public Integer modificarCantidad(DetalleFactura detalleFactura) {
        if (detalleFactura == null) return null;

        detalleArticulos.forEach(d -> {
            if (d.equals(detalleFactura))
                d.cantidad(detalleFactura.cantidad());
        });

        return detalleFactura.cantidad();
    }

    @Override
    public String consultarArticulo(DetalleFactura detalleFactura) {
        if (detalleFactura == null) return null;

        for (DetalleFactura d : detalleArticulos) {
            if (d.equals(detalleFactura)) return d.toString();
        }

        return "0~";
    }

    @Override
    public void consultarArticulos() {
        detalleArticulos.stream()
                .map(this::consultarArticulo).toList().forEach(System.out::println);
    }
}
