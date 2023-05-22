package application.models.finanzas;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import org.jetbrains.annotations.Nullable;

@SqlEntity(type = SqlEntity.ATTRIBUTES_CLASS)
public enum Puestos implements Entity {
    MOSTRADOR("mostrador", 2800d),
    VETERINARIO("veterinario", 6500d),
    LIMPIEZA("limpieza", 3200d),
    GERENTE("gerente", 7500d);

    @SqlAttribute
    private final String puesto;
    @SqlAttribute("salario_bruto")
    private final Double salario;

    Puestos(String puesto, Double salario) {
        this.puesto = puesto; this.salario = salario;
    }

    public static @Nullable Puestos from(String nombre_puesto) {
        for(Puestos puestos : values()) {
            if (puestos.puesto.equals(nombre_puesto)) return puestos;
        }
        return null;
    }
}
