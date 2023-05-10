package application;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.detalles.DetalleFactura;
import application.models.entidades.Proveedores;
import application.models.finanzas.Articulos;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //hola wuichito
        Proveedores proveedor = new Proveedores(
                7,
                "TECATE",
                "Al lado de un arbol de mango",
                "962-891-1001",
                "PARA JOTOS");

        List<Articulos> articulos = List.of(
                new Articulos(
                        8, proveedor, "TECATE LIGHT", new BigDecimal(100), "PARA JOTOS"),
                new Articulos(
                        9, proveedor, "TECATE ROJA", new BigDecimal(120), "PARA HOMBRES"),
                new Articulos(
                        10, proveedor, "TECATE AZUL", new BigDecimal(115), "NO SE")
        );

        /*List<Alimentos> alimentos = List.of(
                new Alimentos(
                        articulos.get(0),
                        600f),
                new Alimentos(
                        articulos.get(1),
                        600f),
                new Alimentos(
                        articulos.get(2),
                        600f));

        FacturasProveedor factura = new FacturasProveedor(
                2,
                Date.valueOf("2023-05-09"),
                proveedor);

        Save<DetalleFactura> save = new Repository<>(new Postgres());

        factura.agregarArticulo(alimentos.get(0).getArticulo(), 2);
        factura.agregarArticulo(alimentos.get(1).getArticulo(), 10);
        factura.agregarArticulo(alimentos.get(2).getArticulo(), 8);

        factura.getDetalleArticulos().forEach(System.out::println);

        try {
            for (DetalleFactura d : factura.getDetalleArticulos()) {
                System.out.println(save.save(d));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        /*
        DetalleFactura detalle1 = new DetalleFactura(articulos.get(0), 1, 1, 10);
        DetalleFactura detalle2 = new DetalleFactura(articulos.get(1), 1, 1, 10);

        System.out.println(detalle1.equals(articulos.get(1)));*/

        System.out.println(Entity.columns(DetalleFactura.class));
    }
}