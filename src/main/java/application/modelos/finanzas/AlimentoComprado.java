package application.modelos.finanzas;

import application.modelos.Lista;
import application.modelos.entidades.Alimento;

public class AlimentoComprado extends Lista <Integer> {
    private Integer cantidad;
    private Alimento alimento;
    private Ticket ticket;

    public AlimentoComprado(Integer cantidad, Alimento alimento, Ticket ticket) {
        this.cantidad = cantidad;
        this.alimento = alimento;
        this.ticket = ticket;
    }

    public AlimentoComprado(Integer llave, Integer cantidad, Alimento alimento, Ticket ticket) {
        super(llave);
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

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
