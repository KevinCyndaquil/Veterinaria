package application.models.entidades;

import application.models.finanzas.Tickets;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public record Citas (
        Integer id_cita,
        LocalDate fecha_cita,
        LocalTime hora,
        String detalle,
        Mascotas mascota,
        Empleados veterinario,
        Tickets ticket,
        EstatusCita estatus) {

    public Citas(Integer id_cita, LocalDate fecha_cita, LocalTime hora, String detalle, Mascotas mascota, Empleados veterinario, Tickets ticket, EstatusCita estatus) {
        this.id_cita = id_cita;
        this.fecha_cita = fecha_cita;
        this.hora = hora;
        this.detalle = detalle;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.ticket = ticket;
        this.estatus = estatus;
    }

    public Citas(LocalDate fecha_cita, LocalTime hora, String detalle, Mascotas mascota, Empleados veterinario, Tickets ticket, EstatusCita estatus) throws IllegalArgumentException{
        this(null, fecha_cita, hora, detalle, mascota, veterinario, ticket, estatus);

        if(!veterinario.getPuesto().getPuesto().equals("veterinario")) {
            throw new IllegalArgumentException("El empleado no es un veterinario");
        }
    }

    public Citas(LocalDate fecha_cita, LocalTime hora, String detalle, Mascotas mascota, Empleados veterinario, EstatusCita estatus) throws IllegalArgumentException{
        this(null, fecha_cita, hora, detalle, mascota, veterinario, null, estatus);

        if(!veterinario.getPuesto().getPuesto().equals("veterinario")) {
            throw new IllegalArgumentException("El empleado no es un veterinario");
        }
    }
}
