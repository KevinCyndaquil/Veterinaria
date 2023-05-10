package application.models.finanzas;

import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.detalles.DetalleFactura;
import application.models.entidades.ConMonto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

public abstract class Facturas implements
        ConMonto {
    @Setter @Getter
    @SqlAttribute
    @SqlKey
    private Integer id_factura;
    @SqlAttribute
    private BigDecimal monto_total;
    @Setter @Getter
    @SqlAttribute("fecha_factura")
    private Date fecha_generacion;

    @SqlInstance
    public Facturas(Integer id_factura, BigDecimal monto_total, Date fecha_generacion) {
        this.id_factura = id_factura;
        this.monto_total = monto_total;
        this.fecha_generacion = fecha_generacion;
    }

    public Facturas(Integer id_factura, Date fecha_generacion) {
        this.id_factura = id_factura;
        this.monto_total = null;
        this.fecha_generacion = fecha_generacion;
    }

    public Facturas(Date fecha_generacion) {
        this.monto_total = null;
        this.fecha_generacion = fecha_generacion;
    }

    public Facturas(Integer id_factura) {
        this.id_factura = id_factura;
        this.monto_total = null;
    }

    @Override
    public BigDecimal monto(BigDecimal monto) {
        return monto_total = monto;
    }

    @Override
    public BigDecimal monto() {
        return monto_total;
    }
}
