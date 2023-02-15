package application.modelos.entregas;

import application.modelos.Tabla;
import application.modelos.entidades.Medicamentos;

public class MedicamentosFactura extends Tabla <Integer> {
    private Double subtotal;
    private Integer cantidad;
    private Facturas factura;
    private Medicamentos medicamento;

    public MedicamentosFactura(Double subtotal, Integer cantidad, Facturas factura, Medicamentos medicamento) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
        this.medicamento = medicamento;
    }

    public MedicamentosFactura(Integer integer, Double subtotal, Integer cantidad, Facturas factura, Medicamentos medicamento) {
        super(integer);
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
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

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public Medicamentos getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamentos medicamento) {
        this.medicamento = medicamento;
    }
}
