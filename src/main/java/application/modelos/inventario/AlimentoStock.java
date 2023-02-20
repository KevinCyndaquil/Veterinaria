package application.modelos.inventario;

import application.modelos.Lista;
import application.modelos.entidades.Alimento;

import java.time.LocalDate;

public class AlimentoStock extends Lista <LocalDate> {
    private Integer cantidad;
    private Alimento alimento;

    public AlimentoStock(Integer cantidad, Alimento alimento) {
        this.cantidad = cantidad;
        this.alimento = alimento;
    }

    public AlimentoStock(LocalDate caducidad, Integer cantidad, Alimento alimento) {
        super(caducidad);
        this.cantidad = cantidad;
        this.alimento = alimento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Alimento getAlimentos() {
        return alimento;
    }

    public void setAlimentos(Alimento alimento) {
        this.alimento = alimento;
    }
}
