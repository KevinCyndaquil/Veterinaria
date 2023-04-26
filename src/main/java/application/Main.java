package application;

import application.models.entidades.Alimentos;
import application.models.utils.Alumno;
import application.models.utils.Create;
import application.models.utils.EntityManager;
import application.models.utils.Maestro;

import java.math.BigDecimal;

public class Main {
    public static void printNum(int... values) {
        for (int value : values) {
            System.out.println(value);
        }
    }

    public static void main(String[] args) {
        Alimentos pedigree = new Alimentos("pedigree", BigDecimal.valueOf(120),"para perros", BigDecimal.valueOf(100));
        /*Detalle<Alimentos> detalle = new ArticulosDetalle<>(pedigree, 11);

        System.out.println(detalle.getMonto());
        detalle.setCantidad(1);
        System.out.println(detalle.getMonto());
        */

        Maestro maestro = new Maestro("1b11", 1, "Wuichito");
        Alumno alumno = new Alumno(null, "Chavez", 5f, maestro);



        /*EntityManager manager = new EntityManager(alumno);

        System.out.println(manager.getForeignValues(alumno));

        manager.getAttributes().forEach((a) -> System.out.println(
                        a.getAttributeName() +
                        "pk:" +
                        a.isPrimaryKey() +
                        "fk:" +
                        a.isForeignKey() + "," +
                                ((a.getForeignKey() != null) ? a.getForeignKey().getValues() : null)));*/

        Create<Alumno> create = new Create<>(alumno);

        create.create();
    }
}