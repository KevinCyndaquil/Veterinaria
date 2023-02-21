package application.modelos.citas;

import application.modelos.Lista;
import application.modelos.Tabla;

import java.time.LocalDate;

public class VacunaExpediente extends Lista<Integer> {
    private LocalDate fecha_vacuna;
    private Tabla <?> mascota;
    private Tabla <?> medicamento;

    public VacunaExpediente(LocalDate fecha_vacuna, Tabla <?> mascota, Tabla <?> medicamento) {
        this.fecha_vacuna = fecha_vacuna;
        this.mascota = mascota;
        this.medicamento = medicamento;
    }

    public VacunaExpediente(Integer llave, LocalDate fecha_vacuna, Tabla <?> mascota, Tabla <?> medicamento) {
        super(llave);
        this.fecha_vacuna = fecha_vacuna;
        this.mascota = mascota;
        this.medicamento = medicamento;
    }

    public LocalDate getFecha_vacuna() {
        return fecha_vacuna;
    }

    public void setFecha_vacuna(LocalDate fecha_vacuna) {
        this.fecha_vacuna = fecha_vacuna;
    }

    public Tabla <?> getMascota() {
        return mascota;
    }

    public void setMascota(Tabla <?> mascota) {
        this.mascota = mascota;
    }

    public Tabla <?> getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Tabla <?> medicamento) {
        this.medicamento = medicamento;
    }
}
