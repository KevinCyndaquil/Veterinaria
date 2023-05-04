package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
@SqlEntity("animales")
public record Animales (
        @SqlAttribute
        @SqlKey
        Integer id_animal,
        @SqlAttribute("nombre")
        String nombre_animal) implements Entity {

    @SqlInstance
    public Animales {
    }

    public Animales(String nombre_animal) {
        this(null, nombre_animal);
    }
}
