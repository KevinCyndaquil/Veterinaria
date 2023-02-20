package application.modelos.citas;

import application.modelos.Lista;
import application.modelos.entidades.Mascota;
import application.modelos.entidades.Veterinario;
import application.modelos.finanzas.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita extends Lista <Integer> {
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Estatus estatus;
    private Veterinario veterinario;
    private Ticket ticket;
    private String detalle;
    private Mascota mascota;

    public Cita(LocalDate fecha, LocalTime hora, Double costo, Estatus estatus, Veterinario veterinario, Ticket ticket, String detalle, Mascota mascota) {
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.estatus = estatus;
        this.veterinario = veterinario;
        this.ticket = ticket;
        this.detalle = detalle;
        this.mascota = mascota;
    }

    public Cita(Integer llave, LocalDate fecha, LocalTime hora, Double costo, Estatus estatus, Veterinario veterinario, Ticket ticket, String detalle, Mascota mascota) {
        super(llave);
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.estatus = estatus;
        this.veterinario = veterinario;
        this.ticket = ticket;
        this.detalle = detalle;
        this.mascota = mascota;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}
