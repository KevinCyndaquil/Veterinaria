package application.modelos.entidades;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

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

    @Getter
    private final String descripcion;

    TiposProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    public static @Nullable TiposProducto getValueFrom(String descripcion) {
        for(TiposProducto tp : TiposProducto.values()) {
            if (tp.getDescripcion().equalsIgnoreCase(descripcion))
                return tp;
        }
        return null;
    }
}
