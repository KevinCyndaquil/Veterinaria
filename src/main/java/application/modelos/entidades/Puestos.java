package application.modelos.entidades;

import lombok.Getter;

public enum Puestos {
    MOSTRADOR("mostrador", 2800d),
    VETERINARIO("veterinario", 6500d),
    LIMPIEZA("limpieza", 3200d),
    GERENTE("gerente", 7500d);

    @Getter
    private final String puesto;
    @Getter
    private final Double salario;

    Puestos(String puesto, Double salario) {
        this.puesto = puesto; this.salario = salario;
    }
}
