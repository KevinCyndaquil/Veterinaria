package application.modelos.entregas;

import application.modelos.Tabla;

import java.time.LocalDate;

public class ProductoFactura extends Factura {
    private Integer cantidad;
    private Double subtotal;
    private Tabla <?> producto;

    public ProductoFactura(LocalDate fecha_factura, Double monto_total, Tabla<?> proveedor, Integer cantidad, Double subtotal, Tabla<?> producto) {
        super(fecha_factura, monto_total, proveedor);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    public ProductoFactura(Integer llave, LocalDate fecha_factura, Double monto_total, Tabla<?> proveedor, Integer cantidad, Double subtotal, Tabla<?> producto) {
        super(llave, fecha_factura, monto_total, proveedor);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Tabla<?> getProducto() {
        return producto;
    }

    public void setProducto(Tabla<?> producto) {
        this.producto = producto;
    }
}
