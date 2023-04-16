package application.database.repositorios;

import application.database.interfaces.Create;
import application.database.interfaces.Hide;
import application.database.interfaces.ReadFull;
import application.database.interfaces.Update;
import application.models.entidades.Medicamentos;

import java.sql.*;
import java.util.List;

public class MedicamentoRep implements
        Create<Medicamentos>,
        ReadFull<Integer, Medicamentos>,
        Hide<Integer>,
        Update<Medicamentos> {

    @Override
    public String create(Medicamentos medicamento) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Medicamentos read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Medicamentos> readAll() throws SQLException {
        return null;
    }

    @Override
    public Medicamentos update(Medicamentos medicamento) throws SQLException {
        return null;
    }
}
