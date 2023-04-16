package application.database.repositorios;

import application.database.interfaces.Create;
import application.database.interfaces.Hide;
import application.database.interfaces.ReadFull;
import application.database.interfaces.Update;
import application.models.entidades.Raza;

import java.sql.*;
import java.util.List;

public class RazaRep implements
        Create<Raza>,
        ReadFull<Integer, Raza>,
        Hide<Integer>,
        Update<Raza> {


    @Override
    public String create(Raza raza) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Raza read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Raza> readAll() throws SQLException {
        return null;
    }

    @Override
    public Raza update(Raza raza) throws SQLException {
        return null;
    }
}
