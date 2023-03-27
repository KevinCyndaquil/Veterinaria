package application.modelos.entidades;

import application.modelos.Tabla;
import application.modelos.citas.DetalleMascota;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class Mascota extends Tabla {
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private LocalDate fecha_nacimiento;
    @Getter @Setter
    private String sexo;
    @Getter @Setter
    private Propietario propietario;
    @Getter @Setter
    private Raza raza;
    @Getter @Setter
    private List<DetalleMascota> detalle;

    public Mascota(String nombre, LocalDate fecha_nacimiento, String sexo, Propietario propietario, Raza raza, List<DetalleMascota> detalle) {
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.propietario = propietario;
        this.raza = raza;
        this.detalle = detalle;
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
