package application.modelos.citas;

import lombok.Getter;

/**
 * @author KevinCyndaquil
 * Es una enumeración que contiene todos los posibles estados en los que puede estar una cita para una
 * mascota.
 */

public enum EstatusCita {
    PENDIENTE("pendiente"),
    REALIZADA("realizada"),
    CANCELADA("cancelada"),
    NO_REALIZADA("no realizada"),
    POSPUESTA("pospuesta");

    @Getter
    private final String estatus;

    EstatusCita(String estatus) {
        this.estatus = estatus;
    }
}
