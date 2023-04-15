package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.*;
import application.modelos.entidades.Alimento;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentoRep implements
        Create<Alimento>,
        ReadFull<Integer, Alimento>,
        Update<Alimento>,
        Hide<Integer> {

    @Override
    public String create(Alimento alimento) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Alimento read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Alimento> readAll() throws SQLException {
        return null;
    }

    @Override
    public Alimento update(Alimento alimento) throws SQLException {
        return null;
    }
}
