package application.models.finanzas;

import application.models.entidades.ConCantidad;
import application.models.entidades.ConMonto;

import java.util.Collection;

public interface IGestorArticulos {
    boolean agregarArticulo(ConMonto articulo, Integer cantidad);
    boolean agregarArticulos(Collection<ConCantidad> articulos);
    boolean eliminarArticulo(ConMonto articulo);
    boolean eliminarArticulos();
    boolean modificarCantidad(ConMonto articulo, Integer cantidad);
    Integer consultarArticulo(ConMonto articulo);
    void consultarArticulos();

}
