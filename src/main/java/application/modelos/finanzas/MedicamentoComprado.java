package application.modelos.finanzas;

import application.modelos.Lista;
import application.modelos.entidades.Medicamento;

public class MedicamentoComprado extends Lista <Integer> {
    private Integer cantidad;
    private Medicamento medicamento;
    private Ticket ticket;

    public MedicamentoComprado(Integer cantidad, Medicamento medicamento, Ticket ticket) {
        this.cantidad = cantidad;
        this.medicamento = medicamento;
        this.ticket = ticket;
    }

    public MedicamentoComprado(Integer llave, Integer cantidad, Medicamento medicamento, Ticket ticket) {
        super(llave);
        this.cantidad = cantidad;
        this.medicamento = medicamento;
        this.ticket = ticket;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
