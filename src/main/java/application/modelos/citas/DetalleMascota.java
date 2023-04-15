package application.modelos.citas;

import application.modelos.entidades.ArticulosVenta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class DetalleMascota  {
    @Getter @Setter
    private LocalDate fecha;
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private ArticulosVenta articulo;

    public DetalleMascota(LocalDate fecha, Integer cantidad, ArticulosVenta articulo) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public DetalleMascota(Integer id, LocalDate fecha, Integer cantidad, ArticulosVenta articulo) {

        this.fecha = fecha;
        this.cantidad = cantidad;
        this.articulo = articulo;
    }
}
