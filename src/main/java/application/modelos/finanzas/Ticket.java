package application.modelos.finanzas;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket extends Tabla {
    @Getter @Setter
    private Double monto_total;
    @Getter @Setter
    private Double pago_total;
    @Getter @Setter
    private LocalDate fecha_cobro;
    @Getter @Setter
    private LocalTime hora_cobro;
    @Getter @Setter
    private List<ArticuloVendido> articuloVendidos;
    @Getter @Setter
    private List<Pago> pagos;

    public Ticket(LocalDate fecha_cobro, LocalTime hora_cobro) {
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
        this.articuloVendidos = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    public Ticket(Integer id, LocalDate fecha_cobro, LocalTime hora_cobro) {
        super(id);
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
        this.articuloVendidos = new ArrayList<>();
        this.pagos = new ArrayList<>();
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

    public void agregarPago(Pago pago) {
        this.pagos.add(pago);
        this.pago_total += pago.getMonto_subtotal();
    }

    public void agregarPagos(List<Pago> pagos) {
        this.pagos.addAll(pagos);
        calcularPago();
    }

    public void calcularPago() {
        pago_total += 0d;
        this.pagos.forEach(p -> pago_total += p.getMonto_subtotal());
    }
}
