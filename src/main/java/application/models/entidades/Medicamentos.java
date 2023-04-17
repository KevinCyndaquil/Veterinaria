package application.models.entidades;

import application.models.finanzas.Articulos;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Medicamentos extends Articulos {

    private Double gramaje;
    private String laboratorio;
    private ViasMedicamento via;

    public Medicamentos(Integer id_articulo, String nombre, BigDecimal monto_compra, String descripcion) {
        super(id_articulo, nombre, monto_compra, descripcion);
    }

    public Medicamentos(String nombre, BigDecimal monto_compra, String descripcion) {
        super(nombre, monto_compra, descripcion);
    }
}
