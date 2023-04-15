package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

/**
 * @author KevinCyndaquil
 * Es un artículo dentro de la veterinaria, un objeto que contiene un nombre, un precio y una descripción
 * al usuario, que puede ser vendido, administrado o contado.
 */
@Setter
@Getter
public class ArticulosVenta extends ArticulosProveedor {
    private Integer id_articulo_venta;
    private Double montoVenta;
    private Integer stock;
    private String descripcion;
    private String tipo;

}
