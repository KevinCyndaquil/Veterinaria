package application.models.finanzas;

import application.models.detalles.Pagos;
import application.models.entidades.ConCantidad;

import java.util.List;

public interface IGestorPagos {

    Estatus agregarPago(ConCantidad pago);
    Estatus agregarPagos(List<ConCantidad> pagos);
    Estatus eliminarPago(ConCantidad pago);
    Estatus modificarPago(ConCantidad pago);

}
