package application;

import application.models.entidades.Alimentos;
import application.models.finanzas.Articulos;
import application.models.finanzas.ArticulosDetalle;
import application.models.finanzas.Detalle;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Alimentos pedigree = new Alimentos("pedigree", BigDecimal.valueOf(120),"para perros", BigDecimal.valueOf(100));
        Detalle<Alimentos> detalle = new ArticulosDetalle<>(pedigree, 11);

        System.out.println(detalle.getMonto());
        detalle.setCantidad(1);
        System.out.println(detalle.getMonto());

    }
}