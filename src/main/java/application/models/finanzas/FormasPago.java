package application.models.finanzas;

import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;

import java.math.BigDecimal;

@SqlEntity("formas_pago")
public record FormasPago (
        @SqlAttribute @SqlKey
        Integer id_forma_pago,
        @SqlAttribute
        String nombre,
        @SqlAttribute
        BigDecimal comision) {

    @SqlInstance
    public FormasPago(Integer id_forma_pago, String nombre, BigDecimal comision) {
        this.id_forma_pago = id_forma_pago;
        this.nombre = nombre;
        this.comision = comision;
    }

    public FormasPago(String nombre, BigDecimal comision) {
        this(null, nombre, comision);
    }
}
