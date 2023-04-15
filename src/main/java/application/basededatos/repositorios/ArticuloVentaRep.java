package application.basededatos.repositorios;

import application.basededatos.interfaces.*;
import application.modelos.entidades.ArticulosVenta;

import java.sql.*;
import java.util.List;

public class ArticuloVentaRep implements
        Create<ArticulosVenta>,
        ReadFull<Integer, ArticulosVenta>,
        Update<ArticulosVenta>,
        Hide<Integer> {

    @Override
    public String create(ArticulosVenta articuloVenta) {
        return null;
    }

    @Override
    public String hide(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public ArticulosVenta read(Integer[] id) throws SQLException {
        return null;
    }

    @Override
    public List<ArticulosVenta> readAll() throws SQLException {
        return null;
    }

    @Override
    public ArticulosVenta update(ArticulosVenta articuloVenta) throws SQLException {
        return null;
    }
}
