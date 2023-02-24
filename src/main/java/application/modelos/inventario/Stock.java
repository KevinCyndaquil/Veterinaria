package application.modelos.inventario;

import application.modelos.Lista;
import application.modelos.Tabla;

import java.time.LocalDate;

public class Stock extends Lista <LocalDate> {
    private Integer cantidad;
    private Tabla<?> articulo;

    public Stock(Integer cantidad, Tabla<?> articulo) {
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public Stock(LocalDate llave, Integer cantidad, Tabla<?> articulo) {
        super(llave);
        this.cantidad = cantidad;
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Tabla<?> getArticulo() {
        return articulo;
    }

    public void setArticulo(Tabla<?> articulo) {
        this.articulo = articulo;
    }


}
