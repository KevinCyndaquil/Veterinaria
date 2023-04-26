package application.models.entidades;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Mascotas extends Animales {
    private Integer id_mascota;
    private String nombre;
    private LocalDate fecha_nacimiento;
    private String sexo;
    private Personas propietario;

    public Mascotas(Integer id_raza, String nombre, Personas propietario) {
        super(id_raza, nombre);
        this.propietario = propietario;
    }

    public Mascotas(String nombre, Personas propietario) {
        super(nombre);
        this.propietario = propietario;
    }
}



