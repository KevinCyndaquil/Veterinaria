package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;
import application.modelos.entidades.Propietarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropietarioRep implements
        Create<Propietarios>,
        ReadFull<Integer, Propietarios>,
        Hide<Integer>,
        Update<Propietarios> {

    @Override
    public String create(Propietarios propietarios) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Propietarios read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Propietarios> readAll() throws SQLException {
        return null;
    }

    @Override
    public Propietarios update(Propietarios propietarios) throws SQLException {
        return null;
    }
}
