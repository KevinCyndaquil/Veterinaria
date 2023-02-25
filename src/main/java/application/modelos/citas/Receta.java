package application.modelos.citas;

import application.modelos.Tabla;
import application.modelos.entidades.Articulo;

public class Receta extends Tabla<Integer> {
    private Integer tomar_dias;
    private Integer frecuencia_dias;
    private Double cantidad_frecuencia;
    private Articulo articulo;

    public Receta(Integer tomar_dias, Integer frecuencia_dias, Double cantidad_frecuencia, Articulo articulo) {
        this.tomar_dias = tomar_dias;
        this.frecuencia_dias = frecuencia_dias;
        this.cantidad_frecuencia = cantidad_frecuencia;
        this.articulo = articulo;
    }

    public Receta(Integer id, Integer tomar_dias, Integer frecuencia_dias, Double cantidad_frecuencia, Articulo articulo) {
        super(id);
        this.tomar_dias = tomar_dias;
        this.frecuencia_dias = frecuencia_dias;
        this.cantidad_frecuencia = cantidad_frecuencia;
        this.articulo = articulo;
    }
}
