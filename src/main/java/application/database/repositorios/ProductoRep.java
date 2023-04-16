package application.database.repositorios;

import application.database.interfaces.Create;
import application.database.interfaces.Hide;
import application.database.interfaces.ReadFull;
import application.database.interfaces.Update;
import application.models.entidades.Productos;

import java.sql.*;
import java.util.List;

public class ProductoRep implements
        Create<Productos>,
        ReadFull<Integer, Productos>,
        Hide<Integer>,
        Update<Productos> {

    @Override
    public String create(Productos producto) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Productos read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Productos> readAll() throws SQLException {
        return null;
    }

    @Override
    public Productos update(Productos producto) throws SQLException {
        return null;
    }
}
