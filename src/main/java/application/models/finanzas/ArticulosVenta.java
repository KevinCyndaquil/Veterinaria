package application.models.finanzas;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author KevinCyndaquil
 * Es un artículo dentro de la veterinaria, un objeto que contiene un nombre, un precio y una descripción
 * al usuario, que puede ser vendido, administrado o contado.
 */
@Setter
@Getter
public class ArticulosVenta extends Articulos {
    private BigDecimal ganancia;
    private BigDecimal monto_venta;
    private Integer stock;

    public ArticulosVenta(Integer id_articulo, String nombre, BigDecimal monto_compra, String descripcion, BigDecimal ganancia, BigDecimal monto_venta, Integer stock) {
        super(id_articulo, nombre, monto_compra, descripcion);
        this.ganancia = ganancia;
        this.monto_venta = monto_venta;
        this.stock = stock;
    }

    public ArticulosVenta(String nombre, BigDecimal monto_compra, String descripcion, BigDecimal ganancia, BigDecimal monto_venta, Integer stock) {
        super(nombre, monto_compra, descripcion);
        this.ganancia = ganancia;
        this.monto_venta = monto_venta;
        this.stock = stock;
    }

    public void setGanancia(BigDecimal ganancia) {
        this.ganancia = ganancia;
        this.monto_venta = this.getMonto_compra().add(ganancia);
    }

}
