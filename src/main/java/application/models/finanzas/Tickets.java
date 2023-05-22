package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.detalles.DetalleTicket;
import application.models.detalles.Pagos;
import application.models.entidades.ConMonto;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;



@SqlEntity("tickets")
public class Tickets implements
        IGestorPagos<Pagos>,
        IGestorArticulos<DetalleTicket>,
        ConMonto,
        Entity {

    @Getter
    @SqlAttribute
    @SqlKey
    private Integer id_ticket;
    @SqlAttribute
    private BigDecimal monto_total;
    @SqlAttribute("pago_total")
    private BigDecimal pagoTotal;
    @Getter @Setter
    @SqlAttribute("fecha_cobro")
    private Date fecha;
    @Getter @Setter
    @SqlAttribute("hora_cobro")
    private Time hora;
    @Getter @Setter
    @SqlAttribute("estatus")
    private Estatus estatusTicket;

    public final List<DetalleTicket> detalleArticulos;
    private final List<Pagos> pagos;


    public Tickets(int id_ticket, Date fecha, Time hora, Estatus estatusTicket) {
        this.id_ticket = id_ticket;
        this.fecha = fecha;
        this.hora = hora;
        this.estatusTicket = estatusTicket;

        pagoTotal = BigDecimal.ZERO;
        monto_total = BigDecimal.ZERO;

        detalleArticulos = new ArrayList<>();
        pagos = new ArrayList<>();
    }

    public Tickets(Date fecha, Time hora) {
        this.fecha = fecha;
        this.hora = hora;
        this.estatusTicket = Estatus.PENDIENTE;


        pagoTotal = BigDecimal.ZERO;
        monto_total = BigDecimal.ZERO;

        detalleArticulos = new ArrayList<>();
        pagos = new ArrayList<>();
    }

    private void validarStatus() {
        if (pagos.stream().map(Pagos::monto).reduce(BigDecimal.ZERO, BigDecimal::add).compareTo(monto_total) >= 0) {
            estatusTicket = Estatus.PAGADO;
        } else {
            estatusTicket = Estatus.PENDIENTE;
        }
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
        detalleArticulos.forEach(d -> d.setId_ticket(id_ticket));
    }

    public void calcularMontoTotal() {
        monto(detalleArticulos.stream()
                .map(d -> (d.getArticuloVenta().monto().multiply(BigDecimal.valueOf(d.cantidad()))))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        System.out.println("monto new: " + monto());
    }

    @Override
    public Estatus agregarPago(Pagos pago) {
        if (pago == null) return estatusTicket;

        pagos.add( pago);
        pagoTotal = pagoTotal.add(pago.monto());

        validarStatus();
        return estatusTicket;
    }

    @Override
    public Estatus agregarPagos(@NotNull Collection<Pagos> pagos) {
        pagos.forEach(this::agregarPago);

        return estatusTicket;
    }

    @Override
    public Estatus eliminarPago(Pagos pago) {
        if (pago == null) return null;

        pagos.remove( pago);
        pagoTotal = pagoTotal.subtract(pago.monto());
        validarStatus();
        return estatusTicket;
    }

    @Override
    public Estatus modificarPago(Pagos pago) {
        if (pago == null) return estatusTicket;

        eliminarPago(pago);
        agregarPago(pago);
        validarStatus();
        return estatusTicket;
    }

    @Override
    public BigDecimal monto(BigDecimal monto) {
        return monto_total = monto;
    }

    @Override
    public BigDecimal monto() {
        return monto_total;
    }

    @Override
    public boolean agregarArticulo(DetalleTicket detalleTicket) {
        if (detalleTicket == null) return false;

        if (detalleArticulos.contains(detalleTicket))
            return modificarCantidad(detalleTicket) != null;

        monto(monto().add(detalleTicket.monto()));
        detalleTicket.setId_ticket(id_ticket);
        return detalleArticulos.add(detalleTicket);
    }

    @Override
    public boolean agregarArticulos(Collection<DetalleTicket> collection) {
        if (collection == null) return false;
        if (collection.isEmpty()) return false;

        collection.forEach(this::agregarArticulo);
        return true;
    }

    @Override
    public boolean eliminarArticulo(DetalleTicket detalleTicket) {
        if (detalleTicket == null) return false;

        monto(monto().subtract(detalleTicket.monto()));
        return detalleArticulos.remove(detalleTicket);
    }

    @Override
    public boolean eliminarArticulos() {
        detalleArticulos.forEach(this::eliminarArticulo);
        return true;
    }

    @Override
    public Integer modificarCantidad(DetalleTicket detalleTicket) {
        if (detalleTicket == null) return null;
        if (!detalleArticulos.contains(detalleTicket)) return null;

        DetalleTicket detalleToDelete = detalleArticulos.stream().filter(detalleTicket::equals).reduce(null, (a, d) -> d);

        System.out.println("detalle a borrar: " + detalleToDelete);
        eliminarArticulo(detalleToDelete);
        agregarArticulo(detalleTicket);

        return detalleTicket.cantidad();
    }

    @Override
    public String consultarArticulo(DetalleTicket detalleTicket) {
        if (detalleTicket == null) return null;

        for (DetalleTicket d : detalleArticulos) {
            if (d.equals(detalleTicket)) return d.toString();
        }

        return "0~";
    }

    @Override
    public void consultarArticulos() {
        detalleArticulos.stream()
                .map(this::consultarArticulo).toList().forEach(System.out::println);
    }

    @Override
    public String toString() {
        return String.valueOf(id_ticket);
    }
}
