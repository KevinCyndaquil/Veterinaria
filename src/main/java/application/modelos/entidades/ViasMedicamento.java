package application.modelos.entidades;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

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

    @Getter
    private final String via;

    ViasMedicamento(String via) {
        this.via = via;
    }

    public static @Nullable ViasMedicamento getValueFrom(String via) {
        for (ViasMedicamento v : ViasMedicamento.values()) {
            if (v.getVia().equalsIgnoreCase(via))
                return v;
        }
        return null;
    }
}
