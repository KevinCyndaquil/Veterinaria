package application.modelos.entregas;

import application.modelos.Tabla;

import java.time.LocalDate;

public class MedicamentoFactura extends Factura {
    private Double subtotal;
    private Integer cantidad;
    private Tabla <?>  medicamento;

    public MedicamentoFactura(LocalDate fecha_factura, Double monto_total, Tabla<?> proveedor, Double subtotal, Integer cantidad, Tabla<?> medicamento) {
        super(fecha_factura, monto_total, proveedor);
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.medicamento = medicamento;
    }

    public MedicamentoFactura(Integer llave, LocalDate fecha_factura, Double monto_total, Tabla<?> proveedor, Double subtotal, Integer cantidad, Tabla<?> medicamento) {
        super(llave, fecha_factura, monto_total, proveedor);
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.medicamento = medicamento;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Tabla<?> getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Tabla<?> medicamento) {
        this.medicamento = medicamento;
    }
}
