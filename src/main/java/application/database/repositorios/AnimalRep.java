package application.database.repositorios;

import application.database.interfaces.Create;
import application.database.interfaces.Hide;
import application.database.interfaces.ReadFull;
import application.database.interfaces.Update;
import application.models.entidades.Animales;

import java.sql.*;
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
