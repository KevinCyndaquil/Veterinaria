package application;

import application.database.repositorios.FacturaRep;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        FacturaRep read = new FacturaRep();
        try {
            read.read(new Integer[]{1}).
                    getArticuloFacturas().forEach(articuloFactura -> System.out.println(articuloFactura.getNombre()));


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("No existe esa factura");
        }
    }
}