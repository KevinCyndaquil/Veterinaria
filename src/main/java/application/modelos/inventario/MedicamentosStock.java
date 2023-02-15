package application.modelos.inventario;

import application.modelos.Tabla;
import application.modelos.entidades.Medicamentos;

import java.time.LocalDate;

public class MedicamentosStock extends Tabla <LocalDate> {
    private Integer cantidad;
    private Medicamentos medicamento;

    public MedicamentosStock(Integer cantidad, Medicamentos medicamento) {
        this.cantidad = cantidad;
        this.medicamento = medicamento;
    }

    public MedicamentosStock(LocalDate localDate, Integer cantidad, Medicamentos medicamento) {
        super(localDate);
        this.cantidad = cantidad;
        this.medicamento = medicamento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Medicamentos getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamentos medicamento) {
        this.medicamento = medicamento;
    }
}
