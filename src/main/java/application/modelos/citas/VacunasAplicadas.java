package application.modelos.citas;

import application.modelos.Tabla;
import application.modelos.entidades.Mascotas;
import application.modelos.entidades.Medicamentos;

import java.time.LocalDate;

public class VacunasAplicadas extends Tabla <Integer> {
    private LocalDate fecha;
    private Mascotas mascota;
    private Medicamentos medicamento;

    public VacunasAplicadas(LocalDate fecha, Mascotas mascota, Medicamentos medicamento) {
        this.fecha = fecha;
        this.mascota = mascota;
        this.medicamento = medicamento;
    }

    public VacunasAplicadas(Integer integer, LocalDate fecha, Mascotas mascota, Medicamentos medicamento) {
        super(integer);
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

    public Mascotas getMascota() {
        return mascota;
    }

    public void setMascota(Mascotas mascota) {
        this.mascota = mascota;
    }

    public Medicamentos getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamentos medicamento) {
        this.medicamento = medicamento;
    }
}
