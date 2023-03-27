package application.modelos.inventario;

import application.modelos.Tabla;
import application.modelos.entidades.ArticuloVenta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class ArticuloStock extends Tabla {
    @Getter @Setter
    private LocalDate caducidad;
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private ArticuloVenta articuloVenta;

    public ArticuloStock(LocalDate caducidad, Integer cantidad, ArticuloVenta articuloVenta) {
        this.caducidad = caducidad;
        this.cantidad = cantidad;
        this.articuloVenta = articuloVenta;
    }

    public ArticuloStock(Integer cns, LocalDate caducidad, Integer cantidad, ArticuloVenta articuloVenta) {
        super(cns);
        this.caducidad = caducidad;
        this.cantidad = cantidad;
        this.articuloVenta = articuloVenta;
    }
}
