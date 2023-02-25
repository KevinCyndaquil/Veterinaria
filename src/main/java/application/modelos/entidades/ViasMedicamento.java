package application.modelos.entidades;

/**
 * @author KevinCyndaquil
 * Enumeración que contiene los posibles medios de administración de un medicamento en la veterinaria.
 */

public enum ViasMedicamento {
    ORAL("oral"), //boca
    INTRAVENOSA("intravenosa"), //en las venas
    INTRAMUSCULAR("intramuscular"), //en el músculo
    RECTAL("rectal"), //por el recto
    OCULAR("ocular"), //por los ojos
    NASAL("nasal"), //por la nariz
    CUTANEO("cutaneo"); //por la piel

    private final String via;

    ViasMedicamento(String via) {
        this.via = via;
    }
}
