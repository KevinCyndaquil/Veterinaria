package application.models.entidades;

import application.models.Entity_Manager.abstract_manager.Entity;
import application.models.Entity_Manager.annotations.SqlAttribute;
import application.models.Entity_Manager.annotations.SqlEntity;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * @author KevinCyndaquil
 * Enumeración que contiene los posibles medios de administración de un medicamento en la veterinaria.
 */

@SqlEntity(type = SqlEntity.ATTRIBUTES_CLASS)
public enum ViasMedicamento implements Entity {
    ORAL("oral"), //boca
    INTRAVENOSA("intravenosa"), //en las venas
    INTRAMUSCULAR("intramuscular"), //en el músculo
    RECTAL("rectal"), //por el recto
    OCULAR("ocular"), //por los ojos
    NASAL("nasal"), //por la nariz
    CUTANEO("cutaneo"); //por la piel

    @Getter
    @SqlAttribute
    private final String via;

    ViasMedicamento(String via) {
        this.via = via;
    }
}
