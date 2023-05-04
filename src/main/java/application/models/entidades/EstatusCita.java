package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import lombok.Getter;

/**
 * @author KevinCyndaquil
 * Es una enumeración que contiene todos los posibles estados en los que puede estar una cita para una
 * mascota.
 */

@SqlEntity(type = SqlEntity.ATTRIBUTES_CLASS)
public enum EstatusCita implements Entity {
    PENDIENTE("pendiente"),
    REALIZADA("realizada"),
    CANCELADA("cancelada"),
    NO_REALIZADA("no realizada"),
    POSPUESTA("pospuesta");

    @SqlAttribute
    private final String estatus;

    EstatusCita(String estatus) {
        this.estatus = estatus;
    }
}
