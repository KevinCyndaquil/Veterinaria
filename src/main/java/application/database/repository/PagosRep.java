package application.database.repository;

import application.models.Entity_Manager.repositories.GetConn;
import application.models.Entity_Manager.repositories.Repository;
import application.models.finanzas.Pagos;
import org.jetbrains.annotations.NotNull;

public class PagosRep extends Repository<Pagos> {

    public PagosRep(GetConn conn) {
        super(conn);
    }

    @Override
    public Object save (@NotNull Pagos pago) {
        return null;
    }
}
