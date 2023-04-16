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
    private Propietarios propietario;

    public Mascotas(Integer id_raza, String nombre, Propietarios propietario) {
        super(id_raza, nombre);
        this.propietario = propietario;
    }

    public Mascotas(String nombre, Propietarios propietario) {
        super(nombre);
        this.propietario = propietario;
    }
}



