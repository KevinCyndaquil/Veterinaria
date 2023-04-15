package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

public class Alimento extends ArticulosProveedor {
    @Getter @Setter
    private Double gramaje;

    public Alimento(String nombre, Double monto, Proveedores proveedor, Double gramaje) {
        super(nombre, monto, proveedor);
        this.gramaje = gramaje;
    }

    public Alimento(Integer id, String nombre, Double monto, Proveedores proveedor, Double gramaje) {
        super(id, nombre, monto, proveedor);
        this.gramaje = gramaje;
    }
}
