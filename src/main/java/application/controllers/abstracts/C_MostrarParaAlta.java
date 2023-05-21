package application.controllers.abstracts;

import application.models.Entity_Manager.abstract_manager.Entity;

public abstract class C_MostrarParaAlta<M extends Entity> extends C_Mostrar<M> {
    protected C_AltaPasar<?, ?> alta;

    public C_MostrarParaAlta(Object[] columns, M m, C_AltaPasar<?, ?> alta) {
        super(columns, m);
        this.alta = alta;
    }
}
