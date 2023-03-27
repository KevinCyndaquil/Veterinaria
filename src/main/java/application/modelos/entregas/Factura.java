package application.modelos.entregas;

import application.modelos.Tabla;
import application.modelos.entidades.Proveedor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura extends Tabla {
    @Getter @Setter
    private LocalDate fecha_factura;
    @Getter
    private Double monto_total;
    @Getter @Setter
    private Proveedor proveedor;
    @Getter @Setter
    private List<ArticuloFactura> articuloFacturas;

    public Factura(LocalDate fecha_factura, Proveedor proveedor) {
        this.fecha_factura = fecha_factura;
        this.proveedor = proveedor;
        this.articuloFacturas = new ArrayList<>();
    }

    public Factura(Integer id, LocalDate fecha_factura, Proveedor proveedor) {
        super(id);
        this.fecha_factura = fecha_factura;
        this.proveedor = proveedor;
        this.articuloFacturas = new ArrayList<>();
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
