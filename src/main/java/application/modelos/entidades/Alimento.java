package application.modelos.entidades;

public class Alimento extends Articulo{
    private Double gramaje;

    public Alimento(String nombre, Double monto, String descripcion, Double gramaje) {
        super(nombre, monto, descripcion);
        this.gramaje = gramaje;
    }

    public Alimento(Integer id, String nombre, Double monto, String descripcion, Double gramaje) {
        super(id, nombre, monto, descripcion);
        this.gramaje = gramaje;
    }
}
