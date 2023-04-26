package application.models.finanzas;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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
    private String tipo;

    public ArticulosVenta(Integer id_articulo, String nombre, BigDecimal monto_compra, String descripcion, BigDecimal monto_venta, Integer stock, @NotNull String tipo) {
        super(id_articulo, nombre, monto_compra, descripcion);
        this.monto_venta = monto_venta;
        this.stock = stock;
        if (tipo.equals(ALIMENTO) || tipo.equals(PRODUCTO) || tipo.equals(MEDICAMENTO))
            this.tipo = tipo;
    }

    public ArticulosVenta(String nombre, BigDecimal monto_compra, String descripcion, BigDecimal monto_venta, Integer stock, @NotNull String tipo) {
        super(nombre, monto_compra, descripcion);
        this.monto_venta = monto_venta;
        this.stock = stock;
        if (tipo.equals(ALIMENTO) || tipo.equals(PRODUCTO) || tipo.equals(MEDICAMENTO))
            this.tipo = tipo;
    }

    public void setGanancia(BigDecimal ganancia) {
        this.ganancia = ganancia;
        this.monto_venta = this.getMonto_compra().add(ganancia);
    }

}
