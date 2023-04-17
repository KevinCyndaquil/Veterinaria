package application.models.entidades;

import application.models.finanzas.ArticulosGramaje;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Medicamentos extends ArticulosGramaje {
    private String laboratorio;
    private ViasMedicamento via;

    public Medicamentos(Integer id_articulo, String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje, String laboratorio, ViasMedicamento via) {
        super(id_articulo, nombre, monto_compra, descripcion, gramaje);
        this.laboratorio = laboratorio;
        this.via = via;
    }

    public Medicamentos(String nombre, BigDecimal monto_compra, String descripcion, BigDecimal gramaje, String laboratorio, ViasMedicamento via) {
        super(nombre, monto_compra, descripcion, gramaje);
        this.laboratorio = laboratorio;
        this.via = via;
    }
}
