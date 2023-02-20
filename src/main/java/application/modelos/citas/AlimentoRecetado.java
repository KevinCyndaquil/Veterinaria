package application.modelos.citas;

import application.modelos.Lista;
import application.modelos.entidades.Alimento;

public class AlimentoRecetado extends Lista<Integer> {
    private Integer total;
    private Integer dias;
    private Double frecuencia;
    private Integer cantidad_por_frecuencia;
    private Alimento alimento;
    private Cita cita;

    public AlimentoRecetado(Integer total, Integer dias, Double frecuencia, Integer cantidad_por_frecuencia, Alimento alimento, Cita cita) {
        this.total = total;
        this.dias = dias;
        this.frecuencia = frecuencia;
        this.cantidad_por_frecuencia = cantidad_por_frecuencia;
        this.alimento = alimento;
        this.cita = cita;
    }

    public AlimentoRecetado(Integer llave, Integer total, Integer dias, Double frecuencia, Integer cantidad_por_frecuencia, Alimento alimento, Cita cita) {
        super(llave);
        this.total = total;
        this.dias = dias;
        this.frecuencia = frecuencia;
        this.cantidad_por_frecuencia = cantidad_por_frecuencia;
        this.alimento = alimento;
        this.cita = cita;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getCantidad_por_frecuencia() {
        return cantidad_por_frecuencia;
    }

    public void setCantidad_por_frecuencia(Integer cantidad_por_frecuencia) {
        this.cantidad_por_frecuencia = cantidad_por_frecuencia;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
}
