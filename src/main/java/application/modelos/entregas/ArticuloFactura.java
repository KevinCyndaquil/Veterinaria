package application.modelos.entregas;

import application.modelos.entidades.ArticuloProveedor;
import application.modelos.entidades.Proveedor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public class ArticuloFactura extends ArticuloProveedor {
    @Getter
    private Integer cantidad;
    @Getter
    private Double monto_subtotal;

    public ArticuloFactura(@NotNull ArticuloProveedor articuloProveedor, Integer cantidad) {
        super(
                articuloProveedor.getId(),
                articuloProveedor.getNombre(),
                articuloProveedor.getMontoCompra(),
                articuloProveedor.getProveedor());
        this.cantidad = cantidad;
        this.monto_subtotal = getMontoCompra() * cantidad;
    }

    public ArticuloFactura(String nombre, Double monto, Proveedor proveedor, Integer cantidad) {
        super(nombre, monto, proveedor);
        this.cantidad = cantidad;
        this.monto_subtotal = getMontoCompra() * cantidad;
    }

    public ArticuloFactura(Integer id, String nombre, Double monto, Proveedor proveedor, Integer cantidad) {
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
