package application.database.repositorios;

import application.database.interfaces.Create;
import application.database.interfaces.Hide;
import application.database.interfaces.ReadFull;
import application.database.interfaces.Update;
import application.models.entidades.Proveedores;

import java.sql.*;
import java.util.List;

public class ProveedorRep implements
        Create<Proveedores>,
        ReadFull<Integer, Proveedores>,
        Hide<Integer>,
        Update<Proveedores> {

    @Override
    public String create(Proveedores proveedor) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Proveedores read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Proveedores> readAll() throws SQLException {
        return null;
    }

    @Override
    public Proveedores update(Proveedores proveedor) throws SQLException {
        return null;
    }
}
