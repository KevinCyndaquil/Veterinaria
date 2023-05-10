package application.models.entidades;

import java.math.BigDecimal;

public interface ConCantidad {
    Integer cantidad(int cantidad);
    Integer cantidad();
    BigDecimal calcular();
}
