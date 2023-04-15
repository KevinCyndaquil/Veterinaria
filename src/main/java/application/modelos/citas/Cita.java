package application.modelos.citas;

import application.modelos.entidades.Empleados;
import application.modelos.entidades.Mascotas;
import application.modelos.finanzas.Tickets;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita{
    @Getter @Setter
    private LocalDate fecha;
    @Getter @Setter
    private LocalTime hora;
    @Getter @Setter
    private String detalle;
    @Getter @Setter
    private Mascotas mascota;
    @Getter @Setter
    private Empleados veterinario;
    @Getter @Setter
    private Tickets ticket;
    @Getter @Setter
    private EstatusCita estatus;

    public Cita(LocalDate fecha, LocalTime hora, String detalle, Mascotas mascota, Empleados veterinario, Tickets ticket, EstatusCita estatus) {
        this.fecha = fecha;
        this.hora = hora;
        this.detalle = detalle;
        this.mascota = mascota;
        this.veterinario = /* validar aquí que sea un veterinario */ veterinario;
        this.ticket = ticket;
        this.estatus = estatus;
    }

    public Cita(Integer id, LocalDate fecha, LocalTime hora, String detalle, Mascotas mascota, Empleados veterinario, Tickets ticket, EstatusCita estatus) {
        this.fecha = fecha;
        this.hora = hora;
        this.detalle = detalle;
        this.mascota = mascota;
        this.veterinario = /* validar aquí que sea un veterinario */ veterinario;
        this.ticket = ticket;
        this.estatus = estatus;
    }
}
