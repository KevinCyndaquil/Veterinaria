package application.controllers;

import application.MessageDialog;
import application.controllers.abstracts.PanelController;
import application.database.Postgres;
import application.models.Entity_Manager.repositories.Find;
import application.models.Entity_Manager.repositories.Repository;
import application.models.Entity_Manager.repositories.Save;
import application.models.detalles.DetalleTicket;
import application.models.finanzas.ArticulosVenta;
import application.models.finanzas.Tickets;
import application.views.components.TableDetalleTicketModel;
import application.views.panels.AltaCompra;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeoutException;

public class C_AltaCompra extends PanelController<AltaCompra> {
    private Tickets ticket;
    private ArticulosVenta articuloSelected;

    public C_AltaCompra() throws TimeoutException {
        super(new AltaCompra());

        if (LocalTime.now().getHour() >= 21 || LocalTime.now().getHour() < 9) {
            throw new TimeoutException(
                    "No se puede realizar una compra fuera del horario de atención");
        }

        ticket = new Tickets(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
    }

    public void consultarArticulo() {
        int id_articulo;
        Find<ArticulosVenta> find = new Repository<>(new Postgres());

        try {
            id_articulo = Integer.parseInt(panel.iBarCode.getText());
        } catch (NumberFormatException ex) {
            MessageDialog.stupidMessage(
                    null,
                    "El código del articulo solo contiene número");
            return;
        }


        try {
            articuloSelected = (ArticulosVenta) find.findById(new ArticulosVenta(id_articulo));
        } catch (SQLException ex) {
            MessageDialog.queryErrorMessage(
                    null,
                    "Ocurrió un problema al consultar el articulo en venta");
        }

        if (articuloSelected == null) {
            MessageDialog.errorMessage(
                    null,
                    "El articulo de código %s no existe o se encuentra disponible".formatted(id_articulo));
            return;
        }

        if (articuloSelected.getStock() <= 0) {
            MessageDialog.errorMessage(
                    null,
                    "No hay existencia para el articulo %s"
                            .formatted(articuloSelected));
            return;
        }

        if (articuloSelected.getStock() <= 10)
            MessageDialog.uncertainMessage(
                    null,
                    "Ya quedan %s existencia para el articulo %s"
                            .formatted(articuloSelected.getStock(), articuloSelected));

        //ticket.agregarArticulo(DetalleTicket.valueOf(articuloSelected, 0));
        panel.iBarCode.setForeground(Color.GREEN);

        panel.iName.setText(articuloSelected.toString());
        panel.iPresentation.setText(articuloSelected.getTipo());
        panel.iPrecio.setText(articuloSelected.monto().toString());
        panel.iIva.setText(articuloSelected.monto().multiply(BigDecimal.valueOf(0.16)).toString());
    }

    public boolean agregarArticulo() {
        TableDetalleTicketModel model = (TableDetalleTicketModel) panel.table.getModel();
        int cantidad;

        if (articuloSelected == null) {
            MessageDialog.stupidMessage(null, "Escanea o ingresa un articulo por favor");
            return false;
        }

        if (ticket.detalleArticulos.stream()
                .map(DetalleTicket::getArticuloVenta)
                .toList()
                .contains(articuloSelected)) return false;

        try {
            cantidad = Integer.parseInt(panel.iAmount.getText());
        } catch (NumberFormatException e) {
            MessageDialog.stupidMessage(
                    null,
                    "La cantidad debe de ser un número...");
            panel.iAmount.setText("");
            return false;
        }

        if (cantidad > articuloSelected.getStock()) {
            MessageDialog.errorMessage(
                    null,
                    "No hay existencias suficientes para el articulo %s, máximo %s"
                            .formatted(articuloSelected, articuloSelected.getStock()));
            return false;
        }

        DetalleTicket detalleTicket = DetalleTicket.valueOf(articuloSelected, cantidad);
        //System.out.println(cantidad);
        ticket.agregarArticulo(detalleTicket);

        panel.iSubtotal.setText(detalleTicket.monto().toString());
        panel.iTotal.setText(ticket.monto().toString());

        model.addItem(detalleTicket);

        return true;
    }

    public boolean alta() {
        if (ticket.detalleArticulos.isEmpty()) {
            MessageDialog.stupidMessage(
                    null,
                    "Primero añade un articulo a la compra por favor");
            return false;
        }

        Save<Tickets> saveTicket = new Repository<>(new Postgres());
        Save<DetalleTicket> saveDetalle = new Repository<>(new Postgres());
        Tickets ticketToSave;

        try {
            ticketToSave = new Tickets(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
            ticketToSave.setId_ticket((Integer) saveTicket.save(ticketToSave));
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    null,
                    "Ocurrió un error al registrar el ticket");
            return false;
        }

        try {
            ticketToSave.agregarArticulos(ticket.detalleArticulos);
            saveDetalle.saveAll(ticketToSave.detalleArticulos);
        } catch (SQLException e) {
            MessageDialog.queryErrorMessage(
                    null,
                    "Ocurrió un error al registrar los articulos de la compra");
            return false;
        }

        MessageDialog.querySuccessMessage(
                null,
                "Compra realizada con exito");

        limpiar();

        return true;
    }

    public void limpiar() {
        articuloSelected = null;
        ticket = new Tickets(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));

        panel.iSubtotal.vaciar();
        panel.iTotal.vaciar();
        panel.iIva.vaciar();
        panel.iName.vaciar();
        panel.iBarCode.vaciar();
        panel.iBarCode.setForeground(Color.LIGHT_GRAY);
        panel.iPrecio.vaciar();
        panel.iPresentation.vaciar();
        panel.iDescription.vaciar();

        TableDetalleTicketModel model = (TableDetalleTicketModel) panel.table.getModel();
        model.removeAll();
    }

    @Override
    public void initEvents() {
        panel.iBarCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                System.out.println(e.getKeyCode());
                if (e.getKeyCode() == 127)
                    if (articuloSelected != null)
                        if (MessageDialog.yesNoQuestionMessage(
                                null,
                                "¿Quieres cambiar el articulo seleccionado?")) {
                            panel.iBarCode.setForeground(Color.DARK_GRAY);
                            articuloSelected = null;
                            panel.iBarCode.setText("");
                        }
                if (e.getKeyCode() == 10)
                    consultarArticulo();
            }
        });
        panel.table.addPropertyChangeListener(evt -> {
            TableDetalleTicketModel model = (TableDetalleTicketModel) panel.table.getModel();
            int rowSelected = panel.table.getSelectedRow();

            if (rowSelected == -1) return;

            DetalleTicket detalleTicket = model.getItem(rowSelected);

            System.out.println("d -> " + detalleTicket);

            ticket.calcularMontoTotal();
            panel.iSubtotal.setText(detalleTicket.monto().toString());
            panel.iTotal.setText(ticket.monto().toString());
            panel.repaint();
        });

        panel.btnAdd.addActionListener(e -> {
            if (!agregarArticulo()) {
                MessageDialog.errorMessage(
                        null,
                        "No se pudo agregar el articulo a la compra");
            }
        });

        panel.btnNew.addActionListener(e -> {
            if (MessageDialog.yesNoQuestionMessage(
                    null,
                    "¿Seguro que desea cancelar la compra?"))
                limpiar();
        });

        panel.btnPay.addActionListener(e -> alta());
    }
}
