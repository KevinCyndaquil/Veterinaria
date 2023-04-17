/*package application.views.FacturaDisplay;

import application.basededatos.interfaces.Read;
import application.basededatos.repositorios.AlimentoFacturaRep;
import application.modelos.entregas.ArticuloFactura;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FacturaController {
    protected FacturaView view;

    public FacturaController() {

    }

    public void throwView(LocalDate date, Integer id_proveedor) {
        view = new FacturaView(date, id_proveedor);
        view.setVisible(true);
        //initEvents();
        rellenarTabla();
    }

    public void rellenarTabla() {
        try {
            AlimentoFacturaRep read = new AlimentoFacturaRep();
            view.jTableModel.addItems(read.readAll(LocalDate.of(2023, 3, 15), 1));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        view.jTable.repaint();
    }

    private void initEvents() {
        view.jTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                JTable jTable = (JTable) e.getSource();
                ArticulosJTableModel jTableModel = (ArticulosJTableModel) jTable.getModel();
                int rowSelected = jTable.getSelectedRow();
                int columnSelected = jTable.getSelectedColumn();

                ArticuloFactura articuloProveedor = jTableModel.getItem(rowSelected);
                System.out.println("articuloProveedor.getNombre()");
                articuloProveedor.getArticuloProveedor().setNombre("");

                if (!e.isActionKey())
                    jTable.getModel().setValueAt(
                            "",
                            rowSelected,
                            columnSelected);
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            System.setProperty("awt.useSystemAAFontSettings", "lcd"); // use font antialiasing
            System.setProperty("swing.aatext", "true");
            // resto del código que utiliza AWT o Swing
            (new FacturaController()).throwView(LocalDate.now(), 1);
        });
    }
}*/
