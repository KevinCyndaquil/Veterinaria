package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Hide;
import application.basededatos.interfaces.FullRead;
import application.basededatos.interfaces.Update;
import application.modelos.entidades.Empleado;
import application.modelos.entidades.Veterinario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioRepositorio implements Create<Veterinario>, FullRead<String, Veterinario>, Hide<String, Veterinario> {
    private static VeterinarioRepositorio instance;

    public static VeterinarioRepositorio getInstance() {
        return instance = (instance == null) ? new VeterinarioRepositorio() : instance;
    }

    @Override
    public String create(Veterinario veterinario) {
        if (veterinario == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO veterinarios VALUES (?, default)")) {

            statement.setString(1, veterinario.getLlave());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return "SQL SUCCESSFULLY " + veterinario + " : " + VeterinarioEspecialidadRepositorio.getInstance().create(veterinario);
            return "SQL MISSING: " + veterinario;
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        } catch (RuntimeException ex) {
            return "SUB SQL ERROR: " + ex.getMessage();
        }
    }

    @Override
    public Veterinario hide(String llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "UPDATE veterinarios SET activo = NOT activo WHERE rfc_veterinario = ?")) {

            statement.setString(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Empleado empleado = EmpleadoRepositorio.getInstance().read(llave);

                return new Veterinario(
                        empleado.getLlave(),
                        empleado.getNombre(),
                        empleado.getFecha_inicial(),
                        empleado.getJornada_inicio(),
                        empleado.getJornada_fin(),
                        VeterinarioEspecialidadRepositorio.getInstance().read(llave)
                );
            }
            return null;
        }
    }

    @Override
    public Veterinario read(String llave) throws SQLException {
        if (llave == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM veterinarios WHERE rfc_veterinario = ? AND activo = true")) {

            statement.setString(1, llave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Empleado empleado = EmpleadoRepositorio.getInstance().read(llave);

                return new Veterinario(
                        empleado.getLlave(),
                        empleado.getNombre(),
                        empleado.getFecha_inicial(),
                        empleado.getJornada_inicio(),
                        empleado.getJornada_fin(),
                        VeterinarioEspecialidadRepositorio.getInstance().read(llave)
                );
            }
            return null;
        }
    }

    @Override
    public List<Veterinario> readAll() throws SQLException {
        try (Statement statement = Postgres.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM veterinarios WHERE activo = true ORDER BY rfc_veterinario")) {

            List <Veterinario> list = new ArrayList<>();

            while (resultSet.next()) {
                Empleado empleado = EmpleadoRepositorio.getInstance().read(resultSet.getString(1));

                list.add(new Veterinario(
                        empleado.getLlave(),
                        empleado.getNombre(),
                        empleado.getFecha_inicial(),
                        empleado.getJornada_inicio(),
                        empleado.getJornada_fin(),
                        VeterinarioEspecialidadRepositorio.getInstance().read(resultSet.getString(1))
                ));
            }

            return list;
        }
    }
}

