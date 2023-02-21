package application.modelos.finanzas;

import application.modelos.Lista;
import application.modelos.Tabla;

public class Pago extends Lista <Integer> {
    private Double subtotal;
    private FormaPago formaPago;
    private Tabla <?> ticket;

    public Pago(Double subtotal, FormaPago formaPago, Tabla<?> ticket) {
        this.subtotal = subtotal;
        this.formaPago = formaPago;
        this.ticket = ticket;
    }

    public Pago(Integer llave, Double subtotal, FormaPago formaPago, Tabla<?> ticket) {
        super(llave);
        this.subtotal = subtotal;
        this.formaPago = formaPago;
        this.ticket = ticket;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Tabla<?> getTicket() {
        return ticket;
    }

    public void setTicket(Tabla<?> ticket) {
        this.ticket = ticket;
    }
}
