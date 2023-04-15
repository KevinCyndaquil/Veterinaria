package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.ReadFull;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Medicamento;
import application.modelos.entidades.ViasMedicamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoRep implements
        Create<Medicamento>,
        ReadFull<Integer, Medicamento>,
        Hide<Integer>,
        Update<Medicamento> {

    @Override
    public String create(Medicamento medicamento) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Medicamento read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Medicamento> readAll() throws SQLException {
        return null;
    }

    @Override
    public Medicamento update(Medicamento medicamento) throws SQLException {
        return null;
    }
}
