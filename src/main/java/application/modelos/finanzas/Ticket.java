package application.modelos.finanzas;

import application.modelos.Tabla;
import application.modelos.entidades.Articulo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Ticket extends Tabla<Integer> {
    private Double monto_total;
    private LocalDate fecha_cobro;
    private LocalTime hora_cobro;
    private List<Articulo> articulos;
    private List<Pago> pagos;

    public Ticket(Double monto_total, LocalDate fecha_cobro, LocalTime hora_cobro, List<Articulo> articulos, List<Pago> pagos) {
        this.monto_total = monto_total;
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
        this.articulos = articulos;
        this.pagos = pagos;
    }

    public Ticket(Integer id, Double monto_total, LocalDate fecha_cobro, LocalTime hora_cobro) {
        super(id);
        this.monto_total = monto_total;
        this.fecha_cobro = fecha_cobro;
        this.hora_cobro = hora_cobro;
    }
}
