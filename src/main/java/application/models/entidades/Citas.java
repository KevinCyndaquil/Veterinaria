package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import application.models.Entity_Manager.annotations.SqlInstance;
import application.models.Entity_Manager.annotations.SqlKey;
import application.models.finanzas.Tickets;

import java.time.LocalDate;
import java.time.LocalTime;

@SqlEntity("citas")
public record Citas (
        @SqlAttribute
        @SqlKey(type = SqlKey.SERIAL)
        Integer id_cita,
        @SqlAttribute
        LocalDate fecha_cita,
        @SqlAttribute
        LocalTime hora,
        @SqlAttribute
        String detalle,
        @SqlAttribute
        @SqlKey(SqlKey.FOREIGN_KEY)
        Mascotas mascota,
        @SqlAttribute
        @SqlKey(SqlKey.FOREIGN_KEY)
        Empleados veterinario,
        @SqlAttribute
        @SqlKey(SqlKey.FOREIGN_KEY)
        Tickets ticket,
        @SqlAttribute
        EstatusCita estatus) implements Entity {

    @SqlInstance
    public Citas {
        if(!veterinario.getPuesto().name().equals("veterinario")) {
            throw new IllegalArgumentException("El empleado no es un veterinario");
        }
    }

    public Citas(LocalDate fecha_cita, LocalTime hora, String detalle, Mascotas mascota, Empleados veterinario, Tickets ticket, EstatusCita estatus) throws IllegalArgumentException{
        this(null, fecha_cita, hora, detalle, mascota, veterinario, ticket, estatus);

        if(!veterinario.getPuesto().name().equals("veterinario")) {
            throw new IllegalArgumentException("El empleado no es un veterinario");
        }
    }

    public Citas(LocalDate fecha_cita, LocalTime hora, String detalle, Mascotas mascota, Empleados veterinario, EstatusCita estatus) throws IllegalArgumentException{
        this(null, fecha_cita, hora, detalle, mascota, veterinario, null, estatus);

        if(!veterinario.getPuesto().name().equals("veterinario")) {
            throw new IllegalArgumentException("El empleado no es un veterinario");
        }
    }
}
