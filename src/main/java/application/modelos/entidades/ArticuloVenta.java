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
    @Getter @Setter
    private String tipo;

    public ArticuloVenta(ArticuloProveedor articulo, Double montoVenta, Integer stock, String descripcion, String tipo) {
        this.articulo = articulo;
        this.montoVenta = montoVenta;
        this.stock = stock;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public ArticuloVenta(Integer id, ArticuloProveedor articulo, Double montoVenta, Integer stock, String descripcion, String tipo) {
        super(id);
        this.articulo = articulo;
        this.montoVenta = montoVenta;
        this.stock = stock;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return getId() + ":" + articulo.getNombre() + ", " + descripcion;
    }
}
