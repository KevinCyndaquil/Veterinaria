package application.modelos.entregas;

import application.modelos.entidades.Proveedores;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Facturas_Proveedor extends Proveedores {
    private Integer id_factura;
    private LocalDate fecha_factura;
    private BigDecimal monto_total;
    private List<ArticuloFactura> articulos;

    public Facturas_Proveedor(LocalDate fecha_factura,BigDecimal monto_total,Integer id_proveedor, String nombre, String telefono, String descripcion, List<ArticuloFactura> articulos) {
        super( id_proveedor, nombre, telefono, descripcion);
        this.fecha_factura = fecha_factura;
        this.monto_total = monto_total;
        this.articulos = articulos;
    }

    public Facturas_Proveedor(Integer id_factura, LocalDate fecha_factura,BigDecimal monto_total, String nombre, String telefono, String descripcion, List<ArticuloFactura> articulos) {
        super( nombre, telefono, descripcion);
        this.id_factura = id_factura;
        this.fecha_factura = fecha_factura;
        this.monto_total = monto_total;
        this.articulos = articulos;
    }

    public void agregarArticulo(ArticuloFactura articuloFactura) {
        this.articuloFacturas.add(articuloFactura);
        this.monto_total += articuloFactura.getMonto_subtotal();
    }

    public void agregarArticulos(List<ArticuloFactura> articuloFacturas) {
        this.articuloFacturas.addAll(articuloFacturas);
        calcularMonto_total();
    }

    public void calcularMonto_total() {
        this.monto_total = 0d;
        this.articuloFacturas.forEach(a -> monto_total += a.getMonto_subtotal());
    }
}
