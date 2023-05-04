package application.models.finanzas;

import java.util.Map;

public interface IGestorArticulos <A> {
    boolean agregarArticulo(A articulo, Integer cantidad);
    boolean agregarArticulos(Map<A,Integer> articulos);
    boolean eliminarArticulo(A articulo);
    boolean modificarCantidad(A articulo, Integer cantidad);
    Integer consultarArticulo(A articulo);
    void consultarArticulos();

}
