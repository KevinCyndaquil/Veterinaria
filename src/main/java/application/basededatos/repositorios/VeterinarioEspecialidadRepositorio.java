package application.basededatos.repositorios;

import application.basededatos.Postgres;
import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Read;
import application.modelos.entidades.Especialidad;
import application.modelos.entidades.Veterinario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VeterinarioEspecialidadRepositorio implements Create<Veterinario>, Read<String, List<Especialidad>> {
    private static VeterinarioEspecialidadRepositorio instance;

    public static VeterinarioEspecialidadRepositorio getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, VeterinarioEspecialidadRepositorio::new);
    }

    @Override
    public String create(Veterinario veterinario) {
        if (veterinario == null)
            return null;

        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "INSERT INTO veterinarios_especialidades VALUES(?, ?)")) {

            for (Especialidad e : veterinario.getEspecialidades()) {
                statement.setString(1, veterinario.getLlave());
                statement.setInt(2, e.getLlave());

                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next())
                    return "SQL MISSING " + veterinario.getEspecialidades();
            }

            return "SQL SUCCESSFULLY " + veterinario.getEspecialidades();
        } catch (SQLException ex) {
            return "SQL ERROR: " + ex.getMessage();
        }
    }

    /**
     * Busca los elementos que compartan la llave primaria de veterinaria dentro de la tabla ´veterinarios_especialidades´
     * @param llave la key primaria del elemento a buscar.
     * @return una de los elementos encontrados.
     * @throws SQLException una excepción en caso de ocurrir un error con SQL.
     */

    @Override
    public List<Especialidad> read(String llave) throws SQLException {
        try (PreparedStatement statement = Postgres.getConnection().prepareStatement(
                "SELECT * FROM veterinarios_especialidades WHERE rfc_veterinario = ?")) {

            List<Especialidad> list = new ArrayList<>();

            statement.setString(1, llave);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(new Especialidad(
                        resultSet.getInt(1),
                        resultSet.getString(2)));
            }
            return list;
        }
    }
}
