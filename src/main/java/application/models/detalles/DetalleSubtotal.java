package application.models.detalles;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.entidades.ConCantidad;
import application.models.entidades.ConMonto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public abstract class DetalleSubtotal implements Entity, ConCantidad, ConMonto {
    @SqlAttribute
    private Integer cantidad;
    @SqlAttribute
    private BigDecimal subtotal;
    @Getter
    @Setter
    private ConMonto conMonto;

    public DetalleSubtotal(Integer cantidad, BigDecimal subtotal, ConMonto conMonto) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.conMonto = conMonto;
    }
    public DetalleSubtotal(Integer cantidad, ConMonto conMonto) {
        this.cantidad = cantidad;
        this.subtotal = BigDecimal.ZERO;
        this.conMonto = conMonto;
    }

    @Override
    public void monto(Float monto) {
        subtotal = new BigDecimal(monto);
    }

    @Override
    public BigDecimal monto() {
        return subtotal;
    }

    @Override
    public void cantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public Integer cantidad() {
        return cantidad;
    }

    @Override
    public void calcular(Integer cantidad) {
        subtotal = conMonto.monto().multiply(BigDecimal.valueOf(cantidad));
    }

    @Override
    public void descontar(Integer cantidad) {
        subtotal = (conMonto.monto()
                .subtract(conMonto.monto().multiply(BigDecimal.valueOf(cantidad))));
    }
}
