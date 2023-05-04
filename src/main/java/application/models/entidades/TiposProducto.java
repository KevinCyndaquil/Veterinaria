package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import lombok.Getter;

/**
 * @author KevinCyndaquil
 * Es una enumeración que contiene todos los posibles tipos de productos que están disponibles a la venta
 * en la veterinaria
 */

@SqlEntity(type = SqlEntity.ATTRIBUTES_CLASS)
public enum TiposProducto implements Entity {
    ACCESORIO("accesorio"),
    ROPA("ropa"),
    JUGUETE("juguete"),
    SEGURIDAD("seguridad"),
    HIGIENE("higiene");

    @Getter
    @SqlAttribute("tipo")
    private final String descripcion;

    TiposProducto(String descripcion) {
        this.descripcion = descripcion;
    }
}
