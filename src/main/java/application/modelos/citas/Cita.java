package application.modelos.citas;

import application.modelos.Tabla;
import application.modelos.entidades.Empleado;
import application.modelos.entidades.Mascota;
import application.modelos.finanzas.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Cita extends Tabla {
    @Getter @Setter
    private LocalDate fecha;
    @Getter @Setter
    private LocalTime hora;
    @Getter @Setter
    private String detalle;
    @Getter @Setter
    private Mascota mascota;
    @Getter @Setter
    private Empleado veterinario;
    @Getter @Setter
    private Ticket ticket;
    @Getter @Setter
    private EstatusCita estatus;

    public Cita(LocalDate fecha, LocalTime hora, String detalle, Mascota mascota, Empleado veterinario, Ticket ticket, EstatusCita estatus) {
        this.fecha = fecha;
        this.hora = hora;
        this.detalle = detalle;
        this.mascota = mascota;
        this.veterinario = /* validar aquí que sea un veterinario */ veterinario;
        this.ticket = ticket;
        this.estatus = estatus;
    }

    public Cita(Integer id, LocalDate fecha, LocalTime hora, String detalle, Mascota mascota, Empleado veterinario, Ticket ticket, EstatusCita estatus) {
        super(id);
        this.fecha = fecha;
        this.hora = hora;
        this.detalle = detalle;
        this.mascota = mascota;
        this.veterinario = /* validar aquí que sea un veterinario */ veterinario;
        this.ticket = ticket;
        this.estatus = estatus;
    }
}
