package application.modelos.citas;

import application.modelos.Tabla;
import application.modelos.entidades.Medicamentos;

public class MedicamentosRecetados extends Tabla <Integer> {
    private Integer total;
    private Integer dias;
    private Double frecuencia;
    private Integer cantidad_por_frecuencia;
    private Medicamentos medicamento;
    private Citas cita;

    public MedicamentosRecetados(Integer total, Integer dias, Double frecuencia, Integer cantidad_por_frecuencia, Medicamentos medicamento, Citas cita) {
        this.total = total;
        this.dias = dias;
        this.frecuencia = frecuencia;
        this.cantidad_por_frecuencia = cantidad_por_frecuencia;
        this.medicamento = medicamento;
        this.cita = cita;
    }

    public MedicamentosRecetados(Integer integer, Integer total, Integer dias, Double frecuencia, Integer cantidad_por_frecuencia, Medicamentos medicamento, Citas cita) {
        super(integer);
        this.total = total;
        this.dias = dias;
        this.frecuencia = frecuencia;
        this.cantidad_por_frecuencia = cantidad_por_frecuencia;
        this.medicamento = medicamento;
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

    public Medicamentos getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamentos medicamento) {
        this.medicamento = medicamento;
    }

    public Citas getCita() {
        return cita;
    }

    public void setCita(Citas cita) {
        this.cita = cita;
    }
}
