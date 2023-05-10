package application.models.finanzas;

import application.models.detalles.Pagos;
import application.models.entidades.ConCantidad;
import application.models.entidades.ConMonto;

import java.util.Collection;
import java.util.List;

public interface IGestorPagos <P extends ConMonto> {

    Estatus agregarPago(P p);
    Estatus agregarPagos(Collection<P> collection);
    Estatus eliminarPago(P p);
    Estatus modificarPago(P p);

}
