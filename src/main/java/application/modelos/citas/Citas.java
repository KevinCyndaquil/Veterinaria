package application.modelos.citas;

import application.modelos.Tabla;
import application.modelos.entidades.Mascotas;
import application.modelos.entidades.Veterinarios;
import application.modelos.finanzas.Tickets;

import java.time.LocalDate;
import java.time.LocalTime;

public class Citas extends Tabla<Integer> {
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Estatus estatus;
    private Veterinarios veterinario;
    private Tickets ticket;
    private String detalle;
    private Mascotas mascota;

    public Citas(LocalDate fecha, LocalTime hora, Double costo, Estatus estatus, Veterinarios veterinario, Tickets ticket, String detalle, Mascotas mascota) {
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.estatus = estatus;
        this.veterinario = veterinario;
        this.ticket = ticket;
        this.detalle = detalle;
        this.mascota = mascota;
    }

    public Citas(Integer integer, LocalDate fecha, LocalTime hora, Double costo, Estatus estatus, Veterinarios veterinario, Tickets ticket, String detalle, Mascotas mascota) {
        super(integer);
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

    public Veterinarios getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinarios veterinario) {
        this.veterinario = veterinario;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Mascotas getMascota() {
        return mascota;
    }

    public void setMascota(Mascotas mascota) {
        this.mascota = mascota;
    }
}
