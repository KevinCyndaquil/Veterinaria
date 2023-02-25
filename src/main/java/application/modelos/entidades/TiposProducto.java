package application.modelos.entidades;

/**
 * @author KevinCyndaquil
 * Es una enumeración que contiene todos los posibles tipos de productos que están disponibles a la venta
 * en la veterinaria
 */

public enum TiposProducto {
    ACCESORIO("accesorio"),
    ROPA("ropa"),
    JUGUETE("juguete"),
    SEGURIDAD("seguridad"),
    HIGIENE("higiene");

    private final String tipo;

    TiposProducto(String tipo) {
        this.tipo = tipo;
    }
}
