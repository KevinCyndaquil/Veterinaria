package application.database.repositorios;

import application.database.interfaces.Create;
import application.database.interfaces.Hide;
import application.database.interfaces.ReadFull;
import application.database.interfaces.Update;
import application.models.entidades.Empleados;

import java.sql.*;
import java.util.List;

public class EmpleadosRep implements
        Create<Empleados>,
        ReadFull<Integer, Empleados>,
        Hide<Integer>,
        Update<Empleados> {


    @Override
    public String create(Empleados empleado) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Empleados read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<Empleados> readAll() throws SQLException {
        return null;
    }

    @Override
    public Empleados update(Empleados empleado) throws SQLException {
        return null;
    }
}
