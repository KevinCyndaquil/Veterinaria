package application.database.repositorios;

import application.database.interfaces.Create;
import application.database.interfaces.Read;
import application.database.interfaces.Update;
import application.models.finanzas.Nominas;

import java.sql.SQLException;
import java.util.List;

public class NominaRep implements
        Create<Nominas>,
        Read<Integer, List<Nominas>>,
        Update<Nominas> {

    @Override
    public String create(Nominas nomina) {
        return null;
    }

    @Override
    public List<Nominas> read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public Nominas update(Nominas nomina) throws SQLException {
        return null;
    }
}
