package application.modelos.entidades;

public class Propietario extends Persona {
    public Propietario(String rfc, String nombre, String apellido_paterno, String apellido_materno, String noCuenta) {
        super(rfc, nombre, apellido_paterno, apellido_materno, noCuenta);
    }

    public Propietario(Integer id, String rfc, String nombre, String apellido_paterno, String apellido_materno, String noCuenta) {
        super(id, rfc, nombre, apellido_paterno, apellido_materno, noCuenta);
    }
}
