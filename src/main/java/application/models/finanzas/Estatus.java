package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;

@SqlEntity(type = SqlEntity.ATTRIBUTES_CLASS)
public enum Estatus implements Entity {
    PENDIENTE("pendiente"),
    PAGADO("pagado"),
    CANCELADO("cancelado");

    @SqlAttribute
    private final String estatus;

    Estatus(String estatus) {
        this.estatus = estatus;
    }
}