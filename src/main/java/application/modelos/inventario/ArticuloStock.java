package application.modelos.inventario;

import application.modelos.entidades.ArticulosVenta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class ArticuloStock  {
    @Getter @Setter
    private LocalDate caducidad;
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private ArticulosVenta articuloVenta;

    public ArticuloStock(LocalDate caducidad, Integer cantidad, ArticulosVenta articuloVenta) {
        this.caducidad = caducidad;
        this.cantidad = cantidad;
        this.articuloVenta = articuloVenta;
    }

    public ArticuloStock(Integer cns, LocalDate caducidad, Integer cantidad, ArticulosVenta articuloVenta) {
        this.caducidad = caducidad;
        this.cantidad = cantidad;
        this.articuloVenta = articuloVenta;
    }
}
