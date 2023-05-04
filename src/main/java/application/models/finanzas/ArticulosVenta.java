package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
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

@SqlEntity("articulos_venta")
public class ArticulosVenta implements Entity {
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    private Articulos articulo;
    private BigDecimal ganancia;
    @SqlAttribute("monto")
    private BigDecimal monto_venta;
    @SqlAttribute
    private Integer stock;
    @SqlAttribute
    private String tipo;

    @SqlInstance
    public ArticulosVenta(Articulos articulo, BigDecimal ganancia, BigDecimal monto_venta, Integer stock, String tipo) {
        this.articulo = articulo;
        this.ganancia = ganancia;
        this.monto_venta = monto_venta;
        this.stock = stock;
        this.tipo = tipo;
    }

    public ArticulosVenta(BigDecimal ganancia, BigDecimal monto_venta, Integer stock, String tipo) {
        this.ganancia = ganancia;
        this.monto_venta = monto_venta;
        this.stock = stock;
        this.tipo = tipo;
    }

    public void setGanancia(BigDecimal ganancia) {
        this.ganancia = ganancia;
        this.monto_venta = this.articulo.getMonto_compra().add(ganancia);
    }

}
