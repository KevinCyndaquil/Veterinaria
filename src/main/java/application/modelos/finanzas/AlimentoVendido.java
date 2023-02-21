package application.modelos.finanzas;

import application.modelos.Lista;
import application.modelos.Tabla;

public class AlimentoVendido extends Lista <Integer> {
    private Integer cantidad;
    private Double subtotal;
    private Tabla <?> alimento;
    private Tabla <?> ticket;

    public AlimentoVendido(Integer cantidad, Double subtotal, Tabla <?> alimento, Tabla <?> ticket) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.alimento = alimento;
        this.ticket = ticket;
    }

    public AlimentoVendido(Integer llave, Integer cantidad, Double subtotal, Tabla <?> alimento, Tabla <?> ticket) {
        super(llave);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.alimento = alimento;
        this.ticket = ticket;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Tabla <?> getAlimento() {
        return alimento;
    }

    public void setAlimento(Tabla <?> alimento) {
        this.alimento = alimento;
    }

    public Tabla <?> getTicket() {
        return ticket;
    }

    public void setTicket(Tabla <?> ticket) {
        this.ticket = ticket;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
