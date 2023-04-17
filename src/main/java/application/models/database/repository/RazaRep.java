/*package application.models.database.repository;

import application.models.database.Postgres;
import application.models.database.interfaces.Save;
import application.models.database.interfaces.Find;
import application.models.database.interfaces.Hide;
import application.models.database.interfaces.Update;
import application.models.entidades.Razas;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RazaRep implements Find<Razas>, Hide<Razas>, Save<Razas>, Update<Razas> {

    @Override
    public Optional<Razas> findOne(Razas raza) {
        if (raza == null) return Optional.empty();

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT * FROM razas WHERE nombre = ?")
        ) {
            statement.setString(1, raza.getNombre());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new Razas(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre")
                ));
            }
            return Optional.empty();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }


    @Override
    public Razas findByID(Integer id) {
        if(id == null || id < 1) return null;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT * FROM razas WHERE id_raza= ?")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Razas(
                        resultSet.getInt("id_raza"),
                        resultSet.getString("nombre")
                );
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Razas findByID(Integer id, boolean isHidden) {
        if (isHidden) return findByID(id);

        if (id == null || id < 1) return null;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT * FROM razas WHERE id_raza = ? AND activo = false")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Razas(
                        resultSet.getInt("id_raza"),
                        resultSet.getString("nombre")
                );
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Iterable<Razas> findAllById(Iterable<Integer> ids) {
        List<Razas> razas = new ArrayList<>();

        ids.forEach(integer -> {
            try {
                razas.add(findByID(integer));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        return razas;
    }

    @Override
    public Iterable<Razas> findAll() {
        List<Razas> razas = new ArrayList<>();

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT * FROM razas ORDER BY id_raza")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                razas.add(new Razas(
                        resultSet.getInt("id_raza"),
                        resultSet.getString("nombre")
                ));
            }
            return razas;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return razas;
        }
    }

    @Override
    public String hide(Razas raza) {
        if (raza == null || raza.getId_raza() == null) return "No se ha especificado una raza";

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("UPDATE razas SET activo = false WHERE id_raza = ?")
        ) {
            statement.setInt(1, raza.getId_raza());
            statement.executeUpdate();

            if (statement.getUpdateCount() > 0) return "Raza ocultado";
            return "No se ha podido ocultar la raza";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "No se ha podido ocultar la raza";
        }
    }

    @Override
    public boolean isHidden(Razas raza) {
        if (raza == null || raza.getId_raza() == null) return false;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT activo FROM razas WHERE id_raza = ?")
        ) {
            statement.setInt(1, raza.getId_raza());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return !resultSet.getBoolean("activo");
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    @Override
    public Map<Razas, Boolean> areHidden(Iterable<Razas> razas) {
        if (razas == null) return null;

        Map<Razas, Boolean> map = new HashMap<>();

        razas.forEach(raza -> {
            try {
                map.put(raza, isHidden(raza));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        return map;
    }

    @Override
    public Integer save(Razas raza) {
        if (raza == null) return null;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT agrraza(?)")
        ) {
            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    raza.getNombre()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return -1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    @Override
    public Iterable<Integer> saveAll(Iterable<Razas> razas) {
        List<Integer> ids = new ArrayList<>();

        razas.forEach(raza-> {
            try {
                ids.add(save(raza));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        return ids;
    }

    @Override
    public Integer update(Razas raza) {
        if (raza == null) return null;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT actraza(?)")
        ) {
            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    raza.getId_raza(),
                    raza.getNombre()
            });

            statement.setArray(1, array);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}*/


