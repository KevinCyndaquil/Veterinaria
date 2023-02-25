package application.modelos.citas;

import application.modelos.Tabla;
import application.modelos.entidades.Mascota;
import application.modelos.entidades.Medicamento;

import java.time.LocalDate;

public class VacunaExpediente extends Tabla<Integer> {
    private LocalDate fecha;
    private Medicamento medicamento;

    public VacunaExpediente(LocalDate fecha, Medicamento medicamento) {
        this.fecha = fecha;
        this.medicamento = medicamento;
    }

    public VacunaExpediente(Integer id, LocalDate fecha, Medicamento medicamento) {
        super(id);
        this.fecha = fecha;
        this.medicamento = medicamento;
    }
}
