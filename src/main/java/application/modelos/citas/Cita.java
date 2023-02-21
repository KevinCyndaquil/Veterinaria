package application.modelos.citas;

import application.modelos.Lista;
import application.modelos.Tabla;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita extends Lista <Integer> {
    private LocalDate fecha_cita;
    private LocalTime hora;
    private Double monto;
    private String detalle;
    private Tabla<?> mascota;
    private Tabla<?> veterinario;
    private Tabla<?> ticket;
    private Estatus estatus;

    public Cita(LocalDate fecha_cita, LocalTime hora, Double monto, String detalle, Tabla<?> mascota, Tabla<?> veterinario, Tabla<?> ticket, Estatus estatus) {
        this.fecha_cita = fecha_cita;
        this.hora = hora;
        this.monto = monto;
        this.detalle = detalle;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.ticket = ticket;
        this.estatus = estatus;
    }

    public Cita(Integer llave, LocalDate fecha_cita, LocalTime hora, Double monto, String detalle, Tabla<?> mascota, Tabla<?> veterinario, Tabla<?> ticket, Estatus estatus) {
        super(llave);
        this.fecha_cita = fecha_cita;
        this.hora = hora;
        this.monto = monto;
        this.detalle = detalle;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.ticket = ticket;
        this.estatus = estatus;
    }

    public LocalDate getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(LocalDate fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Tabla<?> getMascota() {
        return mascota;
    }

    public void setMascota(Tabla<?> mascota) {
        this.mascota = mascota;
    }

    public Tabla<?> getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Tabla<?> veterinario) {
        this.veterinario = veterinario;
    }

    public Tabla<?> getTicket() {
        return ticket;
    }

    public void setTicket(Tabla<?> ticket) {
        this.ticket = ticket;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }
}
