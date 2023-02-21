package application.modelos.inventario;

import application.modelos.Lista;
import application.modelos.Tabla;

import java.time.LocalDate;

public class AlimentoStock extends Lista <LocalDate> {
    private Integer cantidad;
    private Tabla <?> alimento;

    public AlimentoStock(Integer cantidad, Tabla <?> alimento) {
        this.cantidad = cantidad;
        this.alimento = alimento;
    }

    public AlimentoStock(LocalDate caducidad, Integer cantidad, Tabla <?> alimento) {
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

    public Tabla <?> getAlimentos() {
        return alimento;
    }

    public void setAlimentos(Tabla <?> alimento) {
        this.alimento = alimento;
    }
}
