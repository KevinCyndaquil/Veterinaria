package application.modelos.citas;

import application.modelos.Tabla;
import application.modelos.entidades.ArticuloVenta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class DetalleMascota extends Tabla {
    @Getter @Setter
    private LocalDate fecha;
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private ArticuloVenta articulo;

    public DetalleMascota(LocalDate fecha, Integer cantidad, ArticuloVenta articulo) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public DetalleMascota(Integer id, LocalDate fecha, Integer cantidad, ArticuloVenta articulo) {
        super(id);
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }
}
