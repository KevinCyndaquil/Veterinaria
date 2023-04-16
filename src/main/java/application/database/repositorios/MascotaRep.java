package application.database.repositorios;

import application.database.interfaces.Create;
import application.database.interfaces.Hide;
import application.database.interfaces.ReadFull;
import application.database.interfaces.Update;
import application.models.entidades.Mascotas;

import java.sql.*;
import java.util.List;

public class MascotaRep implements
        Create<Mascotas>,
        ReadFull<Integer, Mascotas>,
        Hide<Integer>,
        Update<Mascotas> {
    @Override
    public String create(Mascotas mascotas) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Mascotas read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Mascotas> readAll() throws SQLException {
        return null;
    }

    @Override
    public Mascotas update(Mascotas mascotas) throws SQLException {
        return null;
    }
}