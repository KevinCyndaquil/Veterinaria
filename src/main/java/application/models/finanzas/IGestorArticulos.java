package application.models.finanzas;

import application.models.entidades.ConMonto;

import java.util.Collection;

public interface IGestorArticulos <A extends ConMonto> {
    boolean agregarArticulo(A a);
    boolean agregarArticulos(Collection<A> collection);
    boolean eliminarArticulo(A a);
    boolean eliminarArticulos();
    Integer modificarCantidad(A a);
    String consultarArticulo(A a);
    void consultarArticulos();

}
