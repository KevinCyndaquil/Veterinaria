package application.views;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import application.views.components.Button;
import application.views.components.ShowerTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author jacki
 */

public class MostrarPropietario extends ShowerTable {
    public MostrarPropietario(Object[] columns) {
        super(columns);
    }
    /*
    public MostrarPropietario() {
        super("mostrar propietario");
    }

    @Override
    public void initComponents() {

        setSize(600, 500);

        table = new JTable();
        table.setBounds(20, 20, getWidth() - 60, getHeight() - 200);

        DefaultTableModel model = new DefaultTableModel(new Object[]{
                "ID",
                "RFC",
                "Nombre",
                "Apellido paterno",
                "Apellido materno",
                "No Cuenta"
        }, 0);

        table.setModel(model);

        table.setBackground(Color.white);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(table.getBounds());
        sp.setViewportView(table);
        add(sp);

        btnSeleccionar = new Button("Seleccionar");
        btnSeleccionar.setBounds(20, getHeight() - 100, 200, 50);
        btnSeleccionar.setBackground(Color.decode("#F8F2E7"));
        add(btnSeleccionar);

        btnRegistrar = new application.views.components.Button("Registrar");
        btnRegistrar.setBounds(getWidth() - 240, getHeight() - 100, 200, 50);
        btnRegistrar.setBackground(Color.decode("#F8F2E7"));
        add(btnRegistrar);
    }*/
}
