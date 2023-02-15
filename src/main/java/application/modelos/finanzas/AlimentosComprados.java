package application.modelos.finanzas;

import application.modelos.Tabla;
import application.modelos.entidades.Alimentos;

public class AlimentosComprados extends Tabla <Integer> {
    private Integer cantidad;
    private Alimentos alimento;
    private Tickets ticket;

    public AlimentosComprados(Integer cantidad, Alimentos alimento, Tickets ticket) {
        this.cantidad = cantidad;
        this.alimento = alimento;
        this.ticket = ticket;
    }

    public AlimentosComprados(Integer integer, Integer cantidad, Alimentos alimento, Tickets ticket) {
        super(integer);
        this.cantidad = cantidad;
        this.alimento = alimento;
        this.ticket = ticket;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Alimentos getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimentos alimento) {
        this.alimento = alimento;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }
}
