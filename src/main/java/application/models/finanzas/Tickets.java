package application.models.finanzas;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tickets extends Facturas implements IGestorPagos {

    private final Map<ArticulosVenta, Integer> articuloVendidos;
    private final List<Pagos> pagos;

    @Getter
    @Setter
    private Estatus estatusTicket;

    public Tickets(Integer id_factura, LocalDate fecha_generacion) {
        super(id_factura, fecha_generacion);
        articuloVendidos = new HashMap<>();
        pagos = new ArrayList<>();
        estatusTicket = Estatus.PENDIENTE;
    }

    public Tickets(BigDecimal monto_total, LocalDate fecha_generacion) {
        super(monto_total, fecha_generacion);
        articuloVendidos = new HashMap<>();
        pagos = new ArrayList<>();
        estatusTicket = Estatus.PENDIENTE;
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
    public boolean agregarArticulo(Articulos articulo, Integer cantidad) {
        if (articulo == null || cantidad == null || cantidad <= 0) return false;

        articuloVendidos.merge((ArticulosVenta) articulo, cantidad, Integer::sum);
        setMonto_total(getMonto_total().add(((ArticulosVenta) articulo).getMonto_venta().multiply(BigDecimal.valueOf(cantidad))));
        return true;
    }

    @Override
    public boolean agregarArticulos(Map<Articulos, Integer> articulos) {
        if (articulos == null || articulos.isEmpty()) return false;

        articulos.forEach(this::agregarArticulo);
        return true;
    }

    @Override
    public boolean eliminarArticulo(Articulos articulo) {
        if (articulo == null) return false;
        articuloVendidos.remove((ArticulosVenta) articulo);
        return true;
    }

    @Override
    public boolean modificarCantidad(Articulos articulo, Integer cantidad) {
        if (articulo == null || cantidad == null || cantidad <= 0) return false;

        if (articuloVendidos.containsKey((ArticulosVenta) articulo)) {
            articuloVendidos.replace((ArticulosVenta) articulo, cantidad);
            return true;
        } else return false;
    }

    @Override
    public Integer consultarArticulo(Articulos articulo) {
        if (articulo == null) return null;
        return articuloVendidos.get((ArticulosVenta) articulo);
    }

    @Override
    public void consultarArticulos() {
        articuloVendidos.forEach((articulo, cantidad) -> System.out.println(articulo.getNombre() + " " + cantidad));
    }
}
