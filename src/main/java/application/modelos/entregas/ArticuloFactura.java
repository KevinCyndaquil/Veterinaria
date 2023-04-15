package application.modelos.entregas;

import application.modelos.entidades.ArticulosProveedor;
import application.modelos.entidades.Proveedores;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
@Getter
@Setter
public class ArticuloFactura extends ArticulosProveedor {
    private Integer cantidad;
    private Double monto_subtotal;

    public ArticuloFactura(@NotNull ArticulosProveedor articuloProveedor, Integer cantidad) {
        super(

                articuloProveedor.getNombre(),
                articuloProveedor.getMontoCompra(),
                articuloProveedor.getProveedor());
        this.cantidad = cantidad;
        this.monto_subtotal = getMontoCompra() * cantidad;
    }

    public ArticuloFactura(String nombre, Double monto, Proveedores proveedor, Integer cantidad) {
        super(nombre, monto, proveedor);
        this.cantidad = cantidad;
        this.monto_subtotal = getMontoCompra() * cantidad;
    }

    public ArticuloFactura(Integer id, String nombre, Double monto, Proveedores proveedor, Integer cantidad) {
        super(id, nombre, monto, proveedor);
        this.cantidad = cantidad;
        this.monto_subtotal = getMontoCompra() * cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        this.monto_subtotal = getMontoCompra() * cantidad;
    }

    @Override
    public void setMontoCompra(Double montoCompra) {
        super.setMontoCompra(montoCompra);
        this.monto_subtotal = getMontoCompra() * cantidad;
    }
}
