package application;

import application.basededatos.repositorios.FacturaRep;
import application.modelos.entidades.TiposProducto;

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