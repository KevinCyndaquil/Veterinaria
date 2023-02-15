package application.modelos.finanzas;

import application.modelos.Tabla;
import application.modelos.entidades.Medicamentos;

public class MedicamentosComprados extends Tabla <Integer> {
    private Integer cantidad;
    private Medicamentos medicamento;
    private Tickets ticket;

    public MedicamentosComprados(Integer cantidad, Medicamentos medicamento, Tickets ticket) {
        this.cantidad = cantidad;
        this.medicamento = medicamento;
        this.ticket = ticket;
    }

    public MedicamentosComprados(Integer integer, Integer cantidad, Medicamentos medicamento, Tickets ticket) {
        super(integer);
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

    public Medicamentos getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamentos medicamento) {
        this.medicamento = medicamento;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }
}
