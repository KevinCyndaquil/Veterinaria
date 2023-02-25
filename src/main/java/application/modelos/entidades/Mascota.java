package application.modelos.entidades;

import application.modelos.Tabla;
import application.modelos.citas.VacunaExpediente;

import java.time.LocalDate;
import java.util.List;

public class Mascota extends Tabla<Integer> {
    private String nombre;
    private LocalDate fecha_nacimiento;
    private String sexo;
    private Propietario propietario;
    private Raza raza;
    private List<VacunaExpediente> vacunas;

    public Mascota(String nombre, LocalDate fecha_nacimiento, String sexo, Propietario propietario, Raza raza, List<VacunaExpediente> vacunas) {
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.propietario = propietario;
        this.raza = raza;
        this.vacunas = vacunas;
    }

    public Mascota(Integer id, String nombre, LocalDate fecha_nacimiento, String sexo, Propietario propietario, Raza raza) {
        super(id);
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.propietario = propietario;
        this.raza = raza;
    }
}
