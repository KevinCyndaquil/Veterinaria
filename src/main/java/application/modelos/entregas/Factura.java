package application.modelos.entregas;

import application.modelos.Tabla;
import application.modelos.entidades.Articulo;
import application.modelos.entidades.Proveedor;

import java.time.LocalDate;
import java.util.List;

public class Factura extends Tabla<Integer> {
    private LocalDate fecha_factura;
    private Double monto_total;
    private Proveedor proveedor;
    private List<Articulo> articulos;

    public Factura(LocalDate fecha_factura, Double monto_total, Proveedor proveedor, List<Articulo> articulos) {
        this.fecha_factura = fecha_factura;
        this.monto_total = monto_total;
        this.proveedor = proveedor;
        this.articulos = articulos;
    }

    public Factura(Integer id, LocalDate fecha_factura, Double monto_total, Proveedor proveedor) {
        super(id);
        this.fecha_factura = fecha_factura;
        this.monto_total = monto_total;
        this.proveedor = proveedor;
    }
}
