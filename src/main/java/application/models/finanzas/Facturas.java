package application.models.finanzas;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public abstract class Facturas implements IGestorArticulos{
    private Integer id_factura;
    private BigDecimal monto_total;
    private LocalDate fecha_generacion;

    public Facturas(Integer id_factura, LocalDate fecha_generacion) {
        this.id_factura = id_factura;
        this.fecha_generacion = fecha_generacion;
    }

    public Facturas(BigDecimal monto_total, LocalDate fecha_generacion) {
        this.monto_total = monto_total;
        this.fecha_generacion = fecha_generacion;
    }
}
