package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter @Setter

@SqlEntity("mascotas")
public class Mascotas implements Entity {
    @SqlAttribute
    @SqlKey
    Integer id_mascota;
    @SqlAttribute
    String nombre;
    @SqlAttribute
    Date fecha_nacimiento;
    @SqlAttribute
    String sexo;
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    Personas propietario;
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    Razas raza;

    @SqlInstance
    public Mascotas(Integer id_mascota, String nombre, Date fecha_nacimiento, String sexo, Personas propietario, Razas raza) {
        this.id_mascota = id_mascota;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.propietario = propietario;
        this.raza = raza;
    }


    public Mascotas(String nombre, Date fecha_nacimiento, String sexo, Personas propietario, Razas raza) {
        this.id_mascota = null;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.propietario = propietario;
        this.raza = raza;
    }

    @Override
    public String toString() {
        return id_mascota.toString();
    }
}



