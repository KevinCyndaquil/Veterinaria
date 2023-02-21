package application.modelos.entidades;

import application.modelos.Tabla;

import java.time.LocalDate;

public class Mascota extends Tabla<String> {
    private LocalDate fecha_nacimiento;
    private Integer edad;
    private Boolean sexo;
    private Tabla<?> propietario;
    private Tabla<?> raza;

    public Mascota(LocalDate fecha_nacimiento, Integer edad, Boolean sexo, Tabla<?> propietario, Tabla<?> raza) {
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
        this.sexo = sexo;
        this.propietario = propietario;
        this.raza = raza;
    }

    public Mascota(String llave, String nombre, LocalDate fecha_nacimiento, Integer edad, Boolean sexo, Tabla<?> propietario, Tabla<?> raza) {
        super(llave, nombre);
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
        this.sexo = sexo;
        this.propietario = propietario;
        this.raza = raza;
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

    public Tabla<?> getPropietario() {
        return propietario;
    }

    public void setPropietario(Tabla<?> propietario) {
        this.propietario = propietario;
    }

    public Tabla<?> getRaza() {
        return raza;
    }

    public void setRaza(Tabla<?> raza) {
        this.raza = raza;
    }
}
