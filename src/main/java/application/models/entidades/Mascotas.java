package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;

import java.sql.Date;

@SqlEntity("mascotas")
public record Mascotas (
        @SqlAttribute
        @SqlKey
        Integer id_mascota,
        @SqlAttribute
        String nombre,
        @SqlAttribute
        Date fecha_nacimiento,
        @SqlAttribute
        String sexo,
        @SqlAttribute
        @SqlKey(SqlKey.FOREIGN_KEY)
        Personas propietario,
        @SqlAttribute
        @SqlKey(SqlKey.FOREIGN_KEY)
        Razas raza
        ) implements Entity {

    @SqlInstance
    public Mascotas {
    }

    public Mascotas(String nombre, Date fecha_nacimiento, String sexo, Personas propietario, Razas raza) {
        this(null, nombre, fecha_nacimiento, sexo, propietario, raza);
    }
}



