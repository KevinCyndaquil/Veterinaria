package application.modelos.inventario;

import application.modelos.Lista;
import application.modelos.entidades.Medicamento;

import java.time.LocalDate;

public class MedicamentoStock extends Lista <LocalDate> {
    private Integer cantidad;
    private Medicamento medicamento;

    public MedicamentoStock(Integer cantidad, Medicamento medicamento) {
        this.cantidad = cantidad;
        this.medicamento = medicamento;
    }

    public MedicamentoStock(LocalDate caducidad, Integer cantidad, Medicamento medicamento) {
        super(caducidad);
        this.cantidad = cantidad;
        this.medicamento = medicamento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}
