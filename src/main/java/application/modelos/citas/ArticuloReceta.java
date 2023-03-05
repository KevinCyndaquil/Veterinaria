package application.modelos.citas;

import application.modelos.Tabla;
import application.modelos.entidades.Articulo;
import lombok.Getter;
import lombok.Setter;

public class ArticuloReceta extends Tabla {
    @Getter @Setter
    private Integer tomar_dias;
    @Getter @Setter
    private Integer frecuencia_dias;
    @Getter @Setter
    private Double cantidad_frecuencia;
    @Getter @Setter
    private Articulo articulo;

    public ArticuloReceta (Integer tomar_dias, Integer frecuencia_dias, Double cantidad_frecuencia, Articulo articulo) {
        this.tomar_dias = tomar_dias;
        this.frecuencia_dias = frecuencia_dias;
        this.cantidad_frecuencia = cantidad_frecuencia;
        this.articulo = articulo;
    }

    public ArticuloReceta (Integer cns, Integer tomar_dias, Integer frecuencia_dias, Double cantidad_frecuencia, Articulo articulo) {
        super(cns);
        this.tomar_dias = tomar_dias;
        this.frecuencia_dias = frecuencia_dias;
        this.cantidad_frecuencia = cantidad_frecuencia;
        this.articulo = articulo;
    }
}
