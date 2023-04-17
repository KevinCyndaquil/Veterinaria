package application.models.database.repository;

import application.models.database.Postgres;
import application.models.database.interfaces.Save;
import application.models.database.interfaces.Find;
import application.models.database.interfaces.Hide;
import application.models.database.interfaces.Update;
import application.models.entidades.Proveedores;

import java.sql.*;
import java.util.*;

public class ProveedorRep implements Save<Proveedores>, Hide<Proveedores>, Update<Proveedores>, Find<Proveedores> {

    @Override
    public Integer update(Proveedores proveedores) {
        if (proveedores == null) return null;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT actProveedor(?)")
        ) {
            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    proveedores.getNombre(),
                    proveedores.getDireccion(),
                    proveedores.getTelefono(),
                    proveedores.getDescripcion(),
                    proveedores.getId_proveedor()
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

    @Override
    public Integer save(Proveedores proveedor) {
        if (proveedor == null) return null;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT agrproveedor(?)")
        ) {
            Array array = postgres.getConnection().createArrayOf("VARCHAR", new Object[]{
                    proveedor.getNombre(),
                    proveedor.getDireccion(),
                    proveedor.getTelefono(),
                    proveedor.getDescripcion()
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
    public Iterable<Integer> saveAll(Iterable<Proveedores> proveedores) {
        List<Integer> ids = new ArrayList<>();

        proveedores.forEach(proveedor -> {
            try {
                ids.add(save(proveedor));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        return ids;
    }

    @Override
    public Optional<Proveedores> findOne(Proveedores proveedores) {
        if (proveedores == null) return Optional.empty();

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT * FROM proveedores WHERE nombre = ? ")
        ) {
            statement.setString(1, proveedores.getNombre());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Proveedores proveedor = new Proveedores(
                        resultSet.getInt("id_proveedor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("descripcion")
                );
                proveedor.setDireccion(resultSet.getString("direccion"));
                return Optional.of(proveedor);
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Proveedores findByID(Integer id) {
        if (id == null || id < 1) return null;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT * FROM proveedores WHERE id_proveedor = ?")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Proveedores proveedor = new Proveedores(
                        resultSet.getInt("id_proveedor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("descripcion")
                );
                proveedor.setDireccion(resultSet.getString("direccion"));
                return proveedor;
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Proveedores findByID(Integer id, boolean isHidden) {
        if (isHidden) return findByID(id);

        if (id == null || id < 1) return null;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT * FROM proveedores WHERE id_proveedor = ? AND activo = false")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Proveedores proveedor = new Proveedores(
                        resultSet.getInt("id_proveedor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("descripcion")
                );
                proveedor.setDireccion(resultSet.getString("direccion"));
                return proveedor;
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Iterable<Proveedores> findAllById(Iterable<Integer> ids) {
        List<Proveedores> proveedores = new ArrayList<>();

        ids.forEach(integer -> {
            try {
                proveedores.add(findByID(integer));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        return proveedores;
    }

    @Override
    public Iterable<Proveedores> findAll() {
        List<Proveedores> proveedores = new ArrayList<>();

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT * FROM proveedores ORDER BY id_proveedor")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Proveedores proveedor = new Proveedores(
                        resultSet.getInt("id_proveedor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("descripcion")
                );
                proveedor.setDireccion(resultSet.getString("direccion"));
                proveedores.add(proveedor);
            }
            return proveedores;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return proveedores;
        }
    }

    @Override
    public String hide(Proveedores proveedores) {
        if (proveedores == null || proveedores.getId_proveedor() == null) return "No se ha especificado un proveedor";

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("UPDATE proveedores SET activo = false WHERE id_proveedor = ?")
        ) {
            statement.setInt(1, proveedores.getId_proveedor());
            statement.executeUpdate();

            if (statement.getUpdateCount() > 0) return "Proveedor ocultado";
            return "No se ha podido ocultar el proveedor";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "No se ha podido ocultar el proveedor";
        }
    }

    @Override
    public boolean isHidden(Proveedores proveedores) {
        if (proveedores == null || proveedores.getId_proveedor() == null) return false;

        try (
                Postgres postgres = new Postgres();
                PreparedStatement statement = postgres.
                        getConnection().
                        prepareStatement("SELECT activo FROM proveedores WHERE id_proveedor = ?")
        ) {
            statement.setInt(1, proveedores.getId_proveedor());
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
    public Map<Proveedores, Boolean> areHidden(Iterable<Proveedores> proveedores) {
        if (proveedores == null) return null;

        Map<Proveedores, Boolean> map = new HashMap<>();

        proveedores.forEach(proveedor -> {
            try {
                map.put(proveedor, isHidden(proveedor));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        return map;
    }
}
