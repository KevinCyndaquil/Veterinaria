package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import lombok.Getter;
import lombok.Setter;

@SqlEntity("proveedores")
public record Proveedores (
        @SqlAttribute
        @SqlKey
        Integer id_proveedor,
        @SqlAttribute
        String nombre,
        @SqlAttribute
        String direccion,
        @SqlAttribute
        String telefono,
        @SqlAttribute
        String descripcion) implements Entity{

    @SqlInstance
    public Proveedores {
    }

    public Proveedores(int id_proveedor) {
        this(id_proveedor, null, null, null, null);
    }
}
