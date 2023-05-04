package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlKey;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@SqlEntity("tickets")
public class Tickets implements IGestorPagos, IGestorArticulos <ArticulosVenta>, Entity {
    @SqlAttribute
    @SqlKey
    private int id_ticket;
    @SqlAttribute
    private BigDecimal monto_total;
    @SqlAttribute("fecha_cobro")
    private LocalDate fecha;
    @SqlAttribute("hora_cobro")
    private LocalTime hora;
    @SqlAttribute("estatus")
    private Estatus estatusTicket;

    private final Map<ArticulosVenta, Integer> articuloVendidos;
    private final List<Pagos> pagos;


    public Tickets(int id_ticket, LocalDate fecha, LocalTime hora, Estatus estatusTicket) {
        this.id_ticket = id_ticket;
        this.fecha = fecha;
        this.hora = hora;
        this.estatusTicket = estatusTicket;

        articuloVendidos = new HashMap<>();
        pagos = new ArrayList<>();
    }

    public Tickets(LocalDate fecha, LocalTime hora) {
        this.fecha = fecha;
        this.hora = hora;
        this.estatusTicket = Estatus.PENDIENTE;

        articuloVendidos = new HashMap<>();
        pagos = new ArrayList<>();
    }

    private void validarStatus() {
        if (pagos.stream().map(Pagos::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add).compareTo(getMonto_total()) >= 0) {
            estatusTicket = Estatus.PAGADO;
        } else {
            estatusTicket = Estatus.PENDIENTE;
        }
    }

    @Override
    public Estatus agregarPago(Pagos pago) {

        if (pago == null) return estatusTicket;

        pagos.add(pago);
        validarStatus();
        return estatusTicket;
    }

    @Override
    public Estatus agregarPagos(List<Pagos> pagos) {
        if (pagos == null || pagos.isEmpty()) return estatusTicket;
        pagos.forEach(this::agregarPago);
        return estatusTicket;
    }

    @Override
    public Estatus eliminarPago(Pagos pago) {
        if (pago == null) return estatusTicket;

        pagos.remove(pago);
        validarStatus();
        return estatusTicket;
    }


    @Override
    public Estatus modificarPago(Pagos pago) {
        if (pago == null) return estatusTicket;

        pagos.remove(pago);
        pagos.add(pago);
        validarStatus();
        return estatusTicket;
    }

    @Override
    public boolean agregarArticulo(ArticulosVenta articulo, Integer cantidad) {
        if (articulo == null || cantidad == null || cantidad <= 0) return false;

        articuloVendidos.merge(articulo, cantidad, Integer::sum);
        setMonto_total(getMonto_total().add((articulo).getMonto_venta().multiply(BigDecimal.valueOf(cantidad))));
        return true;
    }

    @Override
    public boolean agregarArticulos(Map<ArticulosVenta, Integer> articulos) {
        if (articulos == null || articulos.isEmpty()) return false;

        articulos.forEach(this::agregarArticulo);
        return true;
    }

    @Override
    public boolean eliminarArticulo(ArticulosVenta articulo) {
        if (articulo == null) return false;

        articuloVendidos.remove(articulo);
        return true;
    }

    @Override
    public boolean modificarCantidad(ArticulosVenta articulo, Integer cantidad) {
        if (articulo == null || cantidad == null || cantidad <= 0) return false;

        if (articuloVendidos.containsKey(articulo)) {
            articuloVendidos.replace(articulo, cantidad);
            return true;
        } else return false;
    }

    @Override
    public Integer consultarArticulo(ArticulosVenta articulo) {
        if (articulo == null) return null;
        return articuloVendidos.get(articulo);
    }

    @Override
    public void consultarArticulos() {
        articuloVendidos.forEach((articulo, cantidad) -> System.out.println(articulo.getArticulo().getNombre() + " " + cantidad));
    }
}
