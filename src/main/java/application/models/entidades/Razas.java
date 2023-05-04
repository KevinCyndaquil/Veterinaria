package application.models.entidades;

import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import lombok.Getter;
import lombok.Setter;

public record Razas (
        @SqlAttribute
        @SqlKey
        Integer id_raza,
        @SqlAttribute
        String nombre_raza,
        @SqlAttribute
        Integer total_adopcion,
        @SqlAttribute
        @SqlKey(SqlKey.FOREIGN_KEY)
        Animales animal) {

    @SqlInstance
    public Razas {
    }

    public Razas(String nombre_raza, Integer total_adopcion, Animales animal) {
        this(null, nombre_raza, total_adopcion, animal);
    }
}
