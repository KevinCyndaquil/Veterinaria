package application.basededatos.repositorios;

import application.basededatos.interfaces.Create;
import application.basededatos.interfaces.Read;
import application.basededatos.interfaces.Update;
import application.modelos.finanzas.Nominas;

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
