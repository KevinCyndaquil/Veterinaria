package application.modelos.finanzas;

import application.modelos.Lista;
import application.modelos.entidades.Empleado;

import java.time.LocalDate;

public class Nomina extends Lista <Integer> {
    private LocalDate fecha;
    private Integer total_horas;
    private Double salario;
    private Double bono = 0d;
    private Empleado empleado;

    public Nomina(LocalDate fecha, Integer total_horas, Double salario, Double bono, Empleado empleado) {
        this.fecha = fecha;
        this.total_horas = total_horas;
        this.salario = salario;
        this.bono = bono;
        this.empleado = empleado;
    }

    public Nomina(Integer llave, LocalDate fecha, Integer total_horas, Double salario, Double bono, Empleado empleado) {
        super(llave);
        this.fecha = fecha;
        this.total_horas = total_horas;
        this.salario = salario;
        this.bono = bono;
        this.empleado = empleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal_horas() {
        return total_horas;
    }

    public void setTotal_horas(Integer total_horas) {
        this.total_horas = total_horas;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Double getBono() {
        return bono;
    }

    public void setBono(Double bono) {
        this.bono = bono;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
