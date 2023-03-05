package application.modelos.entregas;

import application.modelos.Tabla;
import application.modelos.entidades.Proveedor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class Factura extends Tabla {
    @Getter @Setter
    private LocalDate fecha_factura;
    @Getter @Setter
    private Double monto_total;
    @Getter @Setter
    private Proveedor proveedor;
    @Getter @Setter
    private List<ArticuloFactura> articulos;

    public Factura(LocalDate fecha_factura, Double monto_total, Proveedor proveedor, List<ArticuloFactura> articulos) {
        this.fecha_factura = fecha_factura;
        this.monto_total = monto_total;
        this.proveedor = proveedor;
        this.articulos = articulos;
    }

    public Factura(Integer id, LocalDate fecha_factura, Double monto_total, Proveedor proveedor, List<ArticuloFactura> articulos) {
        super(id);
        this.fecha_factura = fecha_factura;
        this.monto_total = monto_total;
        this.proveedor = proveedor;
        this.articulos = articulos;
    }
}
