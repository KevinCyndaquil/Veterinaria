package application.modelos.entregas;

import application.modelos.Tabla;

import java.time.LocalDate;

public class AlimentoFactura extends Factura {
    private Integer cantidad;
    private Double subtotal;
    private Tabla <?> alimento;

    public AlimentoFactura(LocalDate fecha_factura, Double monto_total, Tabla<?> proveedor, Integer cantidad, Double subtotal, Tabla<?> alimento) {
        super(fecha_factura, monto_total, proveedor);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.alimento = alimento;
    }

    public AlimentoFactura(Integer llave, LocalDate fecha_factura, Double monto_total, Tabla<?> proveedor, Integer cantidad, Double subtotal, Tabla<?> alimento) {
        super(llave, fecha_factura, monto_total, proveedor);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.alimento = alimento;
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

    public Tabla<?> getAlimento() {
        return alimento;
    }

    public void setAlimento(Tabla<?> alimento) {
        this.alimento = alimento;
    }
}
