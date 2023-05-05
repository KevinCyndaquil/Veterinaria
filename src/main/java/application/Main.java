package application;

import application.database.repository.FacturaProveedorRep;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Save;
import application.database.Postgres;
import application.models.entidades.Alimentos;
import application.models.entidades.Proveedores;
import application.models.finanzas.Articulos;
import application.models.finanzas.FacturasProveedor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //hola wuichito
        Proveedores proveedor = new Proveedores(
                null,
                "TECATE",
                "lomas sajeo",
                "962-891-1001",
                "para mampos");

        List<Articulos> articulos = List.of(
                new Articulos(
                        1,
                    proveedor,
                    "WHISKAS",
                    BigDecimal.valueOf(15),
                    "para gatos"),
                new Articulos(
                        2,
                        proveedor,
                        "PEDIGREE",
                        BigDecimal.valueOf(100.79),
                        "para perros"),
                new Articulos(
                        3,
                        proveedor,
                        "GANADOR",
                        BigDecimal.valueOf(150.25),
                        "para perros ganadores")
        );

        List<Alimentos> alimentos = List.of(
                new Alimentos(
                        articulos.get(0),
                        100f),
                new Alimentos(
                        articulos.get(1),
                        1000f),
                new Alimentos(
                        articulos.get(2),
                        1000f));

        FacturasProveedor factura = new FacturasProveedor(
                1,
                Date.valueOf("2020-12-12"),
                proveedor);

        System.out.println(alimentos.get(0).key(SqlKey.PRIMARY_KEY));

        Save<Articulos> save = new Repository<>(new Postgres());
        Save<Proveedores> psave = new Repository<>(new Postgres());
        Save<Alimentos> asave = new Repository<>(new Postgres());

        factura.agregarArticulo(alimentos.get(0).getArticulo(), 2);
        factura.agregarArticulo(alimentos.get(1).getArticulo(), 10);
        factura.agregarArticulo(alimentos.get(2).getArticulo(), 8);

        Save<FacturasProveedor> fsave = new Repository<>(new Postgres());
        Save<FacturasProveedor> afsave = new FacturaProveedorRep(new Postgres());
        FacturaProveedorRep fp = new FacturaProveedorRep(new Postgres());
        try {
            //System.out.println(factura.getId_factura());
            //System.out.println(afsave.save(factura));
            //var fac = (FacturasProveedor) fp.findByDateAndProvider(Date.valueOf("2020-12-12"), new Proveedores(3));
            System.out.println(psave.save(proveedor));
            //System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}