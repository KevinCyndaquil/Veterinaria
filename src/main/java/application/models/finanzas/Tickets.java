package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.detalles.DetalleTicket;
import application.models.detalles.Pagos;
import application.models.entidades.ConCantidad;
import application.models.entidades.ConMonto;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;



@SqlEntity("tickets")
public class Tickets implements
        IGestorPagos,
        IGestorArticulos,
        ConMonto,
        Entity {

    @Getter @Setter
    @SqlAttribute
    @SqlKey
    private int id_ticket;
    @SqlAttribute
    private BigDecimal monto_total;
    @Getter @Setter
    @SqlAttribute("fecha_cobro")
    private LocalDate fecha;
    @Getter @Setter
    @SqlAttribute("hora_cobro")
    private LocalTime hora;
    @Getter @Setter
    @SqlAttribute("estatus")
    private Estatus estatusTicket;

    private final List<DetalleTicket> detalle;
    private final List<Pagos> pagos;


    public Tickets(int id_ticket, LocalDate fecha, LocalTime hora, Estatus estatusTicket) {
        this.id_ticket = id_ticket;
        this.fecha = fecha;
        this.hora = hora;
        this.estatusTicket = estatusTicket;

        detalle = new ArrayList<>();
        pagos = new ArrayList<>();
    }

    public Tickets(LocalDate fecha, LocalTime hora) {
        this.fecha = fecha;
        this.hora = hora;
        this.estatusTicket = Estatus.PENDIENTE;

        detalle = new ArrayList<>();
        pagos = new ArrayList<>();
    }

    private void validarStatus() {
        if (pagos.stream().map(Pagos::monto).reduce(BigDecimal.ZERO, BigDecimal::add).compareTo(monto_total) >= 0) {
            estatusTicket = Estatus.PAGADO;
        } else {
            estatusTicket = Estatus.PENDIENTE;
        }
    }

    @Override
    public boolean agregarArticulo(ConMonto articulo, Integer cantidad) {
        if (articulo == null || cantidad <= 0) return false;

        detalle.add(DetalleTicket.valueOf(articulo, cantidad));
        return true;
    }

    @Override
    public boolean agregarArticulos(Collection<ConCantidad> articulos) {
        if (articulos == null)
            return false;

        articulos.forEach(a -> detalle.add((DetalleTicket) a));
        return true;
    }

    @Override
    public boolean eliminarArticulo(ConMonto articulo) {
        if (articulo == null) return false;

        if (detalle.contains(DetalleTicket.valueOf(articulo, 0))) {
            detalle.forEach(d -> {
                if (d.getArticuloVenta().equals(articulo))
                    detalle.remove(d);
            });
        }

        return true;
    }

    @Override
    public boolean eliminarArticulos() {
        detalle.clear();

        return true;
    }

    @Override
    public boolean modificarCantidad(ConMonto articulo, Integer cantidad) {
        if (articulo == null || cantidad == null || cantidad <= 0) return false;

        //detalle.stream().map(DetalleTicket::getArticuloVenta).(BigDecimal.ZERO, Integer)

        detalle.forEach(d -> {
            if (d.getArticuloVenta().equals(articulo))
                d.cantidad(cantidad);
        });
        monto(monto().add(
                articulo.monto().multiply(
                        BigDecimal.valueOf(cantidad))).floatValue());
        return true;
    }

    @Override
    public Integer consultarArticulo(ConMonto articulo) {
        if (articulo == null)
            return null;

        for (DetalleTicket d : detalle) {
            if (d.getArticuloVenta().equals(articulo))
                return detalle.indexOf(d);
        }

        return 0;
    }

    @Override
    public void consultarArticulos() {
        detalle.forEach((d) -> System.out.println("Articulo de venta: " + d.getArticuloVenta() +
                "\nCantidad: " + d.cantidad() +
                "\nMonto: $" + d.monto()));
    }

    @Override
    public Estatus agregarPago(ConCantidad pago) {
        if (pago == null) return estatusTicket;

        pagos.add((Pagos) pago);
        validarStatus();
        return estatusTicket;
    }

    @Override
    public Estatus agregarPagos(@NotNull List<ConCantidad> pagos) {
        pagos.forEach(this::agregarPago);

        return estatusTicket;
    }

    @Override
    public Estatus eliminarPago(ConCantidad pago) {
        if (pago == null) return null;

        pagos.remove((Pagos) pago);
        validarStatus();
        return estatusTicket;
    }

    @Override
    public Estatus modificarPago(ConCantidad pago) {
        if (pago == null) return estatusTicket;

        pagos.remove((Pagos) pago);
        pagos.add((Pagos) pago);
        validarStatus();
        return estatusTicket;
    }

    @Override
    public void monto(Float monto) {
        this.monto_total = new BigDecimal(monto);
    }

    @Override
    public BigDecimal monto() {
        return monto_total;
    }
}
