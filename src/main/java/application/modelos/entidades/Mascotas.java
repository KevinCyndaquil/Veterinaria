package application.modelos.entidades;

import application.modelos.Tabla;

import java.time.LocalDate;

public class Mascotas extends Tabla<String> {
    private String nombre;
    private LocalDate fecha_nacimiento;
    private Integer edad;
    private Boolean sexo;
    private Propietarios propietario;
    private Razas raza;

    public Mascotas(String nombre, LocalDate fecha_nacimiento, Integer edad, Boolean sexo, Propietarios propietario, Razas raza) {
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
        this.sexo = sexo;
        this.propietario = propietario;
        this.raza = raza;
    }

    public Mascotas(String s, String nombre, LocalDate fecha_nacimiento, Integer edad, Boolean sexo, Propietarios propietario, Razas raza) {
        super(s);
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
        this.sexo = sexo;
        this.propietario = propietario;
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public Propietarios getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietarios propietario) {
        this.propietario = propietario;
    }

    public Razas getRaza() {
        return raza;
    }

    public void setRaza(Razas raza) {
        this.raza = raza;
    }
}
