package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Mascotas;

import java.sql.*;
import java.util.ArrayList;
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