package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

public class Medicamento extends Articulo{
    @Getter @Setter
    private Double gramaje;
    @Getter @Setter
    private String laboratorio;
    @Getter @Setter
    private ViasMedicamento via;

    public Medicamento(String nombre, Double monto, String descripcion, Double gramaje, String laboratorio, ViasMedicamento via) {
        super(nombre, monto, descripcion);
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.via = via;
    }

    public Medicamento(Integer id, String nombre, Double monto, String descripcion, Double gramaje, String laboratorio, ViasMedicamento via) {
        super(id, nombre, monto, descripcion);
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.via = via;
    }
}
