package application.modelos.finanzas;

import application.modelos.Tabla;
import application.modelos.entidades.Empleados;

import java.time.LocalDate;

public class Nominas extends Tabla <Integer> {
    private LocalDate fecha;
    private Integer total_horas;
    private Double salario;
    private Double bono = 0d;
    private Empleados empleado;

    public Nominas(LocalDate fecha, Integer total_horas, Double salario, Double bono, Empleados empleado) {
        this.fecha = fecha;
        this.total_horas = total_horas;
        this.salario = salario;
        this.bono = bono;
        this.empleado = empleado;
    }

    public Nominas(Integer llave, LocalDate fecha, Integer total_horas, Double salario, Double bono, Empleados empleado) {
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

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }
}
