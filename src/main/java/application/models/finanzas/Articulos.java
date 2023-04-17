package application.models.finanzas;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;

@Getter
@Setter
public abstract class Articulos {
    private Integer id_articulo;
    private String nombre;
    private BigDecimal monto_compra;
    private String descripcion;
    private Boolean activo = true;

    public Articulos(Integer id_articulo, String nombre, BigDecimal monto_compra, String descripcion) {
        this.id_articulo = id_articulo;
        this.nombre = nombre;
        this.monto_compra = monto_compra;
        this.descripcion = descripcion;
    }
    public Articulos(String nombre, BigDecimal monto_compra, String descripcion) {
        this.nombre = nombre;
        this.monto_compra = monto_compra;
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Articulos articulos = (Articulos) obj;
        return Objects.equals(id_articulo, articulos.id_articulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_articulo);
    }
}
