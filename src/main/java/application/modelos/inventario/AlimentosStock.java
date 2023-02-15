package application.modelos.inventario;

import application.modelos.Tabla;
import application.modelos.entidades.Alimentos;

import java.time.LocalDate;

public class AlimentosStock extends Tabla <LocalDate> {
    private Integer cantidad;
    private Alimentos alimentos;

    public AlimentosStock(Integer cantidad, Alimentos alimentos) {
        this.cantidad = cantidad;
        this.alimentos = alimentos;
    }

    public AlimentosStock(LocalDate localDate, Integer cantidad, Alimentos alimentos) {
        super(localDate);
        this.cantidad = cantidad;
        this.alimentos = alimentos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Alimentos getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Alimentos alimentos) {
        this.alimentos = alimentos;
    }
}
