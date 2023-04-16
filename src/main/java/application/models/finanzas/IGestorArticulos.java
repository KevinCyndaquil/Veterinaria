package application.models.finanzas;

import java.util.Map;

public interface IGestorArticulos {
    boolean agregarArticulo(Articulos articulo, Integer cantidad);
    boolean agregarArticulos(Map<Articulos,Integer> articulos);
    boolean eliminarArticulo(Articulos articulo);
    boolean modificarCantidad(Articulos articulo, Integer cantidad);
    Integer consultarArticulo(Articulos articulo);
    void consultarArticulos();

}
