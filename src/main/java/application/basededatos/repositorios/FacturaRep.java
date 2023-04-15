package application.basededatos.repositorios;

import application.basededatos.interfaces.*;
import application.modelos.entregas.Facturas_Proveedor;

import java.sql.*;
import java.util.List;

public class FacturaRep implements
        Create<Facturas_Proveedor>,
        ReadFull<Integer, Facturas_Proveedor>,
        Hide<Integer>,
        Update<Facturas_Proveedor> {

    @Override
    public String create(Facturas_Proveedor factura) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Facturas_Proveedor read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Facturas_Proveedor> readAll() throws SQLException {
        return null;
    }

    @Override
    public Facturas_Proveedor update(Facturas_Proveedor factura) throws SQLException {
        return null;
    }
}
