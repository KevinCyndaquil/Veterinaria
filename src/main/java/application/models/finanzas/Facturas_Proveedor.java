package application.models.finanzas;

import application.models.entidades.Proveedores;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Facturas_Proveedor extends Facturas{

    @Getter
    private final Proveedores proveedor;

    //first: Articulo, second: cantidad
    private Map<Articulos, Integer> articulos;

    public Facturas_Proveedor(Integer id_factura, LocalDate fecha_generacion, Proveedores proveedor) {
        super(id_factura, fecha_generacion);
        this.proveedor = proveedor;
        this.articulos = new HashMap<>();
    }

    public Facturas_Proveedor(BigDecimal monto_total, LocalDate fecha_generacion, Proveedores proveedor) {
        super(monto_total, fecha_generacion);
        this.proveedor = proveedor;
        this.articulos = new HashMap<>();
    }


    @Override
    public boolean agregarArticulo(Articulos articulo, Integer cantidad) {

        if (articulo == null || cantidad == null || cantidad <= 0) return false;

        if (articulos.containsKey(articulo)) {
            articulos.put(articulo, articulos.get(articulo) + cantidad);
        } else {
            articulos.put(articulo, cantidad);
        }
        setMonto_total(getMonto_total().add(articulo.getMonto_compra().multiply(BigDecimal.valueOf(cantidad))));
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
        if (articulos.containsKey(articulo)) {
            articulos.remove(articulo);
            return true;
        }
        return false;
    }

    @Override
    public boolean modificarCantidad(Articulos articulo, Integer cantidad) {

        if (articulo == null || cantidad == null || cantidad <= 0) return false;
        if (articulos.containsKey(articulo)) {
            articulos.replace(articulo, cantidad);
            return true;
        }else return false;
    }

    @Override
    public Integer consultarArticulo(Articulos articulo) {
        if (articulo == null) return null;
        return articulos.get(articulo);
    }

    @Override
    public void consultarArticulos() {
        articulos.forEach((articulo, cantidad) -> System.out.println(articulo.getNombre() + " - " + cantidad));
    }
}
