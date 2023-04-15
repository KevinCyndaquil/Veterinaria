package application.modelos.entidades;

import application.modelos.citas.DetalleMascota;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter


public class Mascotas extends Animales {
    private Integer id_mascota;
    private String nombre;
    private LocalDate fecha_nacimiento;
    private String sexo;
    private Propietarios propietario;
    private List<DetalleMascota> historial_compras;

    public Mascotas(Integer id_mascota, String nombre_mascota, LocalDate fecha_nacimiento, Propietarios propietario, Integer id_animal, String nombre_animal, Integer id_raza, String nombre_raza, Integer total_adopcion) {
        super(id_animal, nombre_animal, id_raza, nombre_raza, total_adopcion);
        this.id_mascota = id_mascota;
        this.nombre = nombre_mascota;
        this.fecha_nacimiento = fecha_nacimiento;
        this.propietario = propietario;
    }

    public Mascotas(String nombre_mascota, LocalDate fecha_nacimiento, Propietarios propietario, String nombre_animal, String nombre_raza, Integer total_adopcion) {
        super(nombre_animal, nombre_raza, total_adopcion);
        this.nombre = nombre_mascota;
        this.fecha_nacimiento = fecha_nacimiento;
        this.propietario = propietario;
    }

}



