package application.modelos.entidades;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ArticulosProveedor extends Proveedores{
    private Integer id_articulo;
    private String nombre;
    private String descripcion;
    private BigDecimal monto_compra;
    private Boolean activo;

public ArticulosProveedor(Integer id_articulo, String nombre, String descripcion, BigDecimal monto_compra, Boolean activo) {
        this.id_articulo = id_articulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.monto_compra = monto_compra;
        this.activo = activo;
    }

    public ArticulosProveedor(Integer id_proveedor, String nombre, String telefono, String descripcion) {
        super(id_proveedor, nombre, telefono, descripcion);
    }

    public ArticulosProveedor(String nombre, String telefono, String descripcion) {
        super(nombre, telefono, descripcion);
    }
}
