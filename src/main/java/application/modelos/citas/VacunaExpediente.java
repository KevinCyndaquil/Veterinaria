package application.modelos.citas;

import application.modelos.Tabla;
import application.modelos.entidades.Medicamento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class VacunaExpediente extends Tabla {
    @Getter @Setter
    private LocalDate fecha;
    @Getter @Setter
    private Medicamento medicamento;

    public VacunaExpediente(LocalDate fecha, Medicamento medicamento) {
        this.fecha = fecha;
        this.medicamento = medicamento;
    }

    public VacunaExpediente(Integer cns, LocalDate fecha, Medicamento medicamento) {
        super(cns);
        this.fecha = fecha;
        this.medicamento = medicamento;
    }
}
