package application.modelos.entidades;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

/**
 * @author KevinCyndaquil
 * Es un artículo dentro de la veterinaria, un objeto que contiene un nombre, un precio y una descripción
 * al usuario, que puede ser vendido, administrado o contado.
 */

public class ArticuloVenta extends Tabla {
    @Getter
    private final ArticuloProveedor articulo;
    @Getter @Setter
    private Double montoVenta;
    @Getter @Setter
    private Integer stock;
    @Getter @Setter
    private String descripcion;

    public ArticuloVenta(ArticuloProveedor articulo, Double montoVenta, Integer stock, String descripcion) {
        this.articulo = articulo;
        this.montoVenta = montoVenta;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    public ArticuloVenta(Integer id, ArticuloProveedor articulo, Double montoVenta, Integer stock, String descripcion) {
        super(id);
        this.articulo = articulo;
        this.montoVenta = montoVenta;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return getId() + ":" + articulo.getNombre() + ", " + descripcion;
    }
}
