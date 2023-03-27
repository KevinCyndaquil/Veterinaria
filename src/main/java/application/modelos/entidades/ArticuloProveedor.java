package application.modelos.entidades;

import application.modelos.Tabla;
import lombok.Getter;
import lombok.Setter;

public class ArticuloProveedor extends Tabla {
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private Double montoCompra;
    @Getter @Setter
    private Proveedor proveedor;

    public ArticuloProveedor(String nombre, Double montoCompra, Proveedor proveedor) {
        this.nombre = nombre;
        this.montoCompra = montoCompra;
        this.proveedor = proveedor;
    }

    public ArticuloProveedor(Integer id, String nombre, Double montoCompra, Proveedor proveedor) {
        super(id);
        this.nombre = nombre;
        this.montoCompra = montoCompra;
        this.proveedor = proveedor;
    }
}
