package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Raza;

import java.sql.*;
import java.util.ArrayList;
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
