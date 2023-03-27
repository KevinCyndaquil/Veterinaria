package application;

import application.basededatos.repositorios.FacturaRep;
import application.modelos.entidades.TiposProducto;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        FacturaRep read = new FacturaRep();
        try {
            read.read(new Integer[]{1}).getArticuloFacturas().forEach(articuloFactura -> System.out.println(articuloFactura.getNombre()));


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}