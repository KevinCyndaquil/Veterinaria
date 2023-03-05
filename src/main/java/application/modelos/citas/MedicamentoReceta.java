package application.modelos.citas;

import application.modelos.entidades.Articulo;

public class MedicamentoReceta extends ArticuloReceta{
    public MedicamentoReceta (Integer tomar_dias, Integer frecuencia_dias, Double cantidad_frecuencia, Articulo articulo) {
        super(tomar_dias, frecuencia_dias, cantidad_frecuencia, articulo);
    }

    public MedicamentoReceta (Integer cns, Integer tomar_dias, Integer frecuencia_dias, Double cantidad_frecuencia, Articulo articulo) {
        super(cns, tomar_dias, frecuencia_dias, cantidad_frecuencia, articulo);
    }
}
