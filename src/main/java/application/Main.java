package application;

import application.models.database.interfaces.Find;
import application.models.database.interfaces.Hide;
import application.models.database.interfaces.Save;
import application.models.database.interfaces.Update;
import application.models.database.repository.ProveedorRep;
import application.models.entidades.Proveedores;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        Proveedores proveedor = new Proveedores("DECHRA", "962 101 5739", "Venta de vacunas rabia");
        proveedor.setDireccion("Calle 2");

        Proveedores proveedor2 = new Proveedores("DEA", "962 102 5739", "Venta de vacunas asma");
        proveedor2.setDireccion("Calle 3");

        Proveedores proveedor3 = new Proveedores("SAN FR", "962 102 5239", "Venta de vacunas y medicamentos");

        List<Proveedores> proveedores = List.of(proveedor, proveedor2, proveedor3);

        Save<Proveedores, Integer> proveedorRep = new ProveedorRep();

        proveedorRep.
                saveAll(proveedores).forEach(System.out::println);

         */

        Hide<Proveedores> proveedorRep = new ProveedorRep();

        proveedorRep.areHidden(List.of(new Proveedores(1), new Proveedores(2), new Proveedores(3))).
                forEach((k, v) -> System.out.println(k.getId_proveedor() + " " + v));

    }
}