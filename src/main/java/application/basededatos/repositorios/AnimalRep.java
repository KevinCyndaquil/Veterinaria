package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Animales;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalRep implements
        Create<Animales>,
        ReadFull<Integer, Animales>,
        Hide<Integer>,
        Update<Animales> {

    @Override
    public String create(Animales animales) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Animales read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Animales> readAll() throws SQLException {
        return null;
    }

    @Override
    public Animales update(Animales animales) throws SQLException {
        return null;
    }
}
