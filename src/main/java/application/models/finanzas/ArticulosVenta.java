package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.entidades.ConCantidad;
import application.models.entidades.ConMonto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author KevinCyndaquil
 * Es un artículo dentro de la veterinaria, un objeto que contiene un nombre, un precio y una descripción
 * al usuario, que puede ser vendido, administrado o contado.
 */

@SqlEntity("articulos_venta")
public class ArticulosVenta implements
        Entity,
        ConMonto {
    @Setter @Getter
    @SqlAttribute
    @SqlKey(SqlKey.FOREIGN_KEY)
    private Articulos articulo;
    @Getter
    private BigDecimal ganancia;
    @SqlAttribute("monto")
    private BigDecimal monto_venta;
    @Setter @Getter
    @SqlAttribute
    private Integer stock;
    @Setter @Getter
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
        this.monto_venta = this.articulo.monto().add(ganancia);
    }

    @Override
    public void monto(Float monto) {
        this.monto_venta = new BigDecimal(monto);
    }

    @Override
    public BigDecimal monto() {
        return monto_venta;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)) return true;

        if(obj == null) return false;

        if(obj.getClass().equals(ArticulosVenta.class)) {
            ArticulosVenta articulo = (ArticulosVenta) obj;
            return this.articulo.equals(articulo.articulo);
        }

        if (obj.getClass().equals(Articulos.class)) {
            Articulos articulo = (Articulos) obj;
            return this.articulo.equals(articulo);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articulo);
    }
}
