package application.models.finanzas;

import lombok.Getter;

import java.math.BigDecimal;


public record FormasPago (
        Integer id_forma_pago,
        String nombre,
        BigDecimal comision) {

    public FormasPago(Integer id_forma_pago, String nombre, BigDecimal comision) {
        this.id_forma_pago = id_forma_pago;
        this.nombre = nombre;
        this.comision = comision;
    }

    public FormasPago(String nombre, BigDecimal comision) {
        this(null, nombre, comision);
    }
}
