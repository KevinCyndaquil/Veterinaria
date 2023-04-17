package application.models.finanzas;

import application.models.finanzas.Estatus;
import application.models.finanzas.Pagos;

import java.util.List;

public interface IGestorPagos {

    Estatus agregarPago(Pagos pago);
    Estatus agregarPagos(List<Pagos> pagos);
    Estatus eliminarPago(Pagos pago);
    Estatus modificarPago(Pagos pago);

}
