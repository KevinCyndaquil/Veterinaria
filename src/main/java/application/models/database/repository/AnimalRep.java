package application.models.database.repository;

import application.models.database.Postgres;
import application.models.database.interfaces.Save;
import application.models.database.interfaces.Find;
import application.models.database.interfaces.Hide;
import application.models.database.interfaces.Update;
import application.models.entidades.Animales;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AnimalRep implements Find<Animales>, Hide<Animales>, Save<Animales>, Update<Animales>{
    @Override
    public Optional<Animales> findOne(Animales animales) {
        return Optional.empty();
    }

    @Override
    public Animales findByID(Integer id) {
        return null;
    }

    @Override
    public Animales findByID(Integer id, boolean isHidden) {
        return null;
    }

    @Override
    public Iterable<Animales> findAllById(Iterable<Integer> ids) {
        return null;
    }

    @Override
    public Iterable<Animales> findAll() {
        return null;
    }

    @Override
    public String hide(Animales animales) {
        return null;
    }

    @Override
    public boolean isHidden(Animales animales) {
        return false;
    }

    @Override
    public Map<Animales, Boolean> areHidden(Iterable<Animales> animales) {
        return null;
    }

    @Override
    public Integer save(Animales animal) {
        if (animal == null) return null;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT agrraza(?)")
        ) {
            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    animal.getNombre_animal(),
                    //animal.getTotal_adopcion(),
                    //animal.getId_raza()
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
    public Iterable<Integer> saveAll(Iterable<Animales> animales) {
        return null;
    }

    @Override
    public Integer update(Animales table) {
        return null;
    }
}


