package application.models.entidades;

import application.models.finanzas.Tickets;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class Citas {
    private Integer id_cita;
    private LocalDate fecha_cita;
    private LocalTime hora;
    private String detalle;

    private Mascotas mascota;
    private Empleados veterinario;
    private Tickets ticket;
    private EstatusCita estatus;

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

    public Citas(LocalDate fecha_cita, LocalTime hora, String detalle, Mascotas mascota, Empleados veterinario, Tickets ticket, EstatusCita estatus) throws Exception {
        this.fecha_cita = fecha_cita;
        this.hora = hora;
        this.detalle = detalle;
        this.mascota = mascota;
        if(veterinario.getPuesto().getPuesto().equals("veterinario")) {
            this.veterinario = veterinario;
        } else {
            throw new Exception("El empleado no es un veterinario");
        }
        this.ticket = ticket;
        this.estatus = estatus;
    }

    public Citas(LocalDate fecha_cita, LocalTime hora, String detalle, Mascotas mascota, Empleados veterinario, EstatusCita estatus) throws Exception {
        this.fecha_cita = fecha_cita;
        this.hora = hora;
        this.detalle = detalle;
        this.mascota = mascota;
        if(veterinario.getPuesto().getPuesto().equals("veterinario")) {
            this.veterinario = veterinario;
        } else {
            throw new Exception("El empleado no es un veterinario");
        }
        this.estatus = estatus;
    }
}
