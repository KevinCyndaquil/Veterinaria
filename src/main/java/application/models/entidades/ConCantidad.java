package application.models.entidades;

public interface ConCantidad {
    void cantidad(Integer cantidad);
    Integer cantidad();
    void calcular(Integer cantidad);
    void descontar(Integer cantidad);
}
