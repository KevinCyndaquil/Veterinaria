package application.database.repositorios;

import application.database.interfaces.*;
import application.models.entidades.Alimentos;

import java.sql.*;
import java.util.List;

public class AlimentoRep implements
        Create<Alimentos>,
        ReadFull<Integer, Alimentos>,
        Update<Alimentos>,
        Hide<Integer> {

    @Override
    public String create(Alimentos alimento) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Alimentos read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Alimentos> readAll() throws SQLException {
        return null;
    }

    @Override
    public Alimentos update(Alimentos alimento) throws SQLException {
        return null;
    }
}
