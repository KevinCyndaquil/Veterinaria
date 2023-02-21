package application.modelos.inventario;

import application.modelos.Lista;
import application.modelos.Tabla;

import java.time.LocalDate;

public class MedicamentoStock extends Lista <LocalDate> {
    private Integer cantidad;
    private Tabla <?> medicamento;

    public MedicamentoStock(Integer cantidad, Tabla <?> medicamento) {
        this.cantidad = cantidad;
        this.medicamento = medicamento;
    }

    public MedicamentoStock(LocalDate caducidad, Integer cantidad, Tabla <?> medicamento) {
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

    public Tabla <?> getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Tabla <?> medicamento) {
        this.medicamento = medicamento;
    }
}
