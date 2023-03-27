package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

public class Medicamento extends ArticuloProveedor {
    @Getter
    @Setter
    private Double gramaje;
    @Getter
    @Setter
    private String laboratorio;
    @Getter
    @Setter
    private ViasMedicamento via;

    public Medicamento(String nombre, Double monto, Proveedor proveedor, Double gramaje, String laboratorio, ViasMedicamento via) {
        super(nombre, monto, proveedor);
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.via = via;
    }

    public Medicamento(Integer id, String nombre, Double monto, Proveedor proveedor, Double gramaje, String laboratorio, ViasMedicamento via) {
        super(id, nombre, monto, proveedor);
        this.gramaje = gramaje;
        this.laboratorio = laboratorio;
        this.via = via;
    }
}
