package application.modelos.inventario;

import application.modelos.Tabla;
import application.modelos.entidades.Articulo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class ArticuloStock extends Tabla {
    @Getter @Setter
    private LocalDate caducidad;
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private Articulo articulo;

    public ArticuloStock(LocalDate caducidad, Integer cantidad, Articulo articulo) {
        this.caducidad = caducidad;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public ArticuloStock(Integer cns, LocalDate caducidad, Integer cantidad, Articulo articulo) {
        super(cns);
        this.caducidad = caducidad;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }
}
