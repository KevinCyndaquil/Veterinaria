package application.models.finanzas;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticuloInventario {
    private ArticulosVenta articulosVenta;
    private int cantidadReal;
    private int variacion;

    public ArticuloInventario(ArticulosVenta articulosVenta, int cantidadReal, int variacion) {
        this.articulosVenta = articulosVenta;
        this.cantidadReal = cantidadReal;
        this.variacion = variacion;
    }

    public void agregarCantidad(int cantidadReal) {
        this.cantidadReal = cantidadReal;

        this.variacion = articulosVenta.getStock() - cantidadReal;
    }
}
