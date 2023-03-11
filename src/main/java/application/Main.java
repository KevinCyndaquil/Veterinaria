package application;

import application.basededatos.repositorios.AlimentoRep;
import application.modelos.entidades.Alimento;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        AlimentoRep alimentoRep = new AlimentoRep();

        alimentoRep.create(new Alimento("GANADOiR", 1000d, "para perros ganadores", 0.5d));
    }
}