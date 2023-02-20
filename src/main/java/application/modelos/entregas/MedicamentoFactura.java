package application.modelos.entregas;

import application.modelos.Lista;
import application.modelos.entidades.Medicamento;

public class MedicamentoFactura extends Lista <Integer> {
    private Double subtotal;
    private Integer cantidad;
    private Factura factura;
    private Medicamento medicamento;

    public MedicamentoFactura(Double subtotal, Integer cantidad, Factura factura, Medicamento medicamento) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.factura = factura;
        this.medicamento = medicamento;
    }

    public MedicamentoFactura(Integer llave, Double subtotal, Integer cantidad, Factura factura, Medicamento medicamento) {
        super(llave);
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}
