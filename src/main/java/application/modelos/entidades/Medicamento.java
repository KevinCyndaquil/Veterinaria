package application.modelos.entidades;

public class Medicamento extends Articulo{
    private Double gramaje;
    private String laboratorio;
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
