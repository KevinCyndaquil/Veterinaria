package application.models.finanzas;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.function.Function;


public abstract class Detalle <T> {
    @Getter
    @Setter
    private T t;
    @Getter
    private Integer cantidad;
    @Getter
    private BigDecimal monto;
    private final Function<Integer, BigDecimal> function;

    public Detalle(T t, Integer cantidad, @NotNull Function<Integer, BigDecimal> function) {
        this.t = t;
        this.cantidad = cantidad;
        this.function = function;
        this.monto = function.apply(cantidad);
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        this.monto = function.apply(cantidad);
    }
}
