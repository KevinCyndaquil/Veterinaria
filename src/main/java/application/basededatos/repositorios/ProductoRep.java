package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Producto;
import application.modelos.entidades.TiposProducto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRep implements
        Create<Producto>,
        ReadFull<Integer, Producto>,
        Hide<Integer>,
        Update<Producto> {

    @Override
    public String create(Producto producto) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Producto read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Producto> readAll() throws SQLException {
        return null;
    }

    @Override
    public Producto update(Producto producto) throws SQLException {
        return null;
    }
}
