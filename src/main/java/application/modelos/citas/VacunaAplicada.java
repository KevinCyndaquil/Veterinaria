package application.modelos.citas;

import application.modelos.Lista;
import application.modelos.entidades.Mascota;
import application.modelos.entidades.Medicamento;

import java.time.LocalDate;

public class VacunaAplicada extends Lista<Integer> {
    private LocalDate fecha;
    private Mascota mascota;
    private Medicamento medicamento;

    public VacunaAplicada(LocalDate fecha, Mascota mascota, Medicamento medicamento) {
        this.fecha = fecha;
        this.mascota = mascota;
        this.medicamento = medicamento;
    }

    public VacunaAplicada(Integer llave, LocalDate fecha, Mascota mascota, Medicamento medicamento) {
        super(llave);
        this.fecha = fecha;
        this.mascota = mascota;
        this.medicamento = medicamento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}
