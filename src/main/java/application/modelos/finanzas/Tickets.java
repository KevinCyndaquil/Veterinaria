package application.modelos.finanzas;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Tickets {
    private Integer id_ticket;
    private BigDecimal monto_total;
    private LocalDate fecha_cobro;
    private LocalTime hora_cobro;
    private List<ArticuloVendido> articuloVendidos;
    private List<Pagos> pagos;

    public Tickets(Integer id_ticket, BigDecimal monto_total, LocalDate fecha_cobro, LocalTime hora_cobro, List<ArticuloVendido> articulosVendidos,List<Pagos> pagos) {
        this.id_ticket = id_ticket;
        this.monto_total = monto_total;
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
        this.articuloVendidos = articulosVendidos;
        this.pagos = pagos;
    }

    public Tickets(BigDecimal monto_total, LocalDate fecha_cobro, LocalTime hora_cobro, List<ArticuloVendido> articulosVendidos,List<Pagos> pagos) {
        this.monto_total = monto_total;
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
        this.articuloVendidos = articulosVendidos;
        this.pagos = pagos;
    }
    public void agregarArticulo(ArticuloVendido articuloVendido) {
        this.articuloVendidos.add(articuloVendido);
        this.monto_total += articuloVendido.getMonto_subtotal();
    }

    public void agregarArticulos(List<ArticuloVendido> articuloVendidos) {
        this.articuloVendidos.addAll(articuloVendidos);
        calcularMonto_total();
    }

    public void calcularMonto_total() {
        this.monto_total = 0d;
        this.articuloVendidos.forEach(a -> monto_total += a.getMonto_subtotal());
    }

    public void agregarPago(Pagos pago) {
        this.pagos.add(pago);
        this.pago_total += pago.getMonto_subtotal();
    }

    public void agregarPagos(List<Pagos> pagos) {
        this.pagos.addAll(pagos);
        calcularPago();
    }

    public void calcularPago() {
        pago_total += 0d;
        this.pagos.forEach(p -> pago_total += p.getMonto_subtotal());
    }
}
