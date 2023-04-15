package application.modelos.finanzas;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FormasPago {
    private Integer id_forma_pago;
    private String nombre;
    private BigDecimal comision;

    public FormasPago(Integer id_forma_pago, String nombre, BigDecimal comision) {
        this.id_forma_pago = id_forma_pago;
        this.nombre = nombre;
        this.comision = comision;
    }

    public FormasPago(String nombre, BigDecimal comision) {
        this.nombre = nombre;
        this.comision = comision;
    }
}
