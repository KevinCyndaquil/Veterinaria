package application.models.detalles;

import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.entidades.ConCantidad;
import application.models.entidades.ConMonto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public abstract class DetalleArticulo<A extends ConMonto> implements
        ConCantidad,
        ConMonto {

    @SqlAttribute
    private Integer cantidad;
    @SqlAttribute
    private BigDecimal subtotal;
    @Getter
    @Setter
    private A a;

    public DetalleArticulo(Integer cantidad, BigDecimal subtotal, A a) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.a = a;
    }
    public DetalleArticulo(Integer cantidad, A a) {
        this.cantidad = cantidad;
        this.subtotal = BigDecimal.ZERO;
        this.a = a;

        calcular();
    }

    @Override
    public BigDecimal monto(BigDecimal monto) {
        return subtotal = monto;
    }

    @Override
    public BigDecimal monto() {
        return subtotal;
    }

    @Override
    public Integer cantidad(int cantidad) {
        this.cantidad = cantidad;
        calcular();

        return this.cantidad;
    }

    @Override
    public Integer cantidad() {
        return cantidad;
    }

    @Override
    public BigDecimal calcular() {
        if (this.monto() == null || a == null) return this.monto(BigDecimal.ZERO);

        return this.monto(a.monto().multiply(BigDecimal.valueOf(cantidad)));
    }
}
