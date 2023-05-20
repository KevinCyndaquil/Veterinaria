package application.views.panels;

import application.views.components.Button;
import application.views.components.abstracts.CustomJPanel;

import java.awt.*;

public class MenuCitaPanel extends CustomJPanel {
    public Button btnRegresar;
    public Button btnaltacitas;
    public Button btnReportes;
    public Button btnModificaciones;

    @Override
    public void initComponents() {
        btnaltacitas = new Button("Alta Citas");
        btnaltacitas.setBackground(Color.decode("#F8F2E7"));
        btnaltacitas.setSize(350, 110);
        add(btnaltacitas);

        btnReportes = new Button("Reportes");
        btnReportes.setBackground(Color.decode("#F8F2E7"));
        btnReportes.setSize(350, 110);
        add(btnReportes);

        btnModificaciones = new Button("Modificaciones");
        btnModificaciones.setBackground(Color.decode("#F8F2E7"));
        btnModificaciones.setSize(350, 110);
        add(btnModificaciones);

        btnRegresar = new Button("Regresar");
        btnRegresar.setBackground(Color.decode("#F8F2E7"));
        btnRegresar.setForeground(Color.red);
        btnRegresar.setSize(250, 50);
        add(btnRegresar);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                System.out.println(getSize());

                btnaltacitas.setLocation(
                        50,
                        50);
                btnReportes.setLocation(
                        getWidth() - btnReportes.getWidth() - 50,
                        50);
                btnModificaciones.setLocation(
                        (getWidth() / 2) - (btnModificaciones.getWidth() / 2),
                        (getHeight() / 2) - (btnModificaciones.getHeight() / 2));

                btnRegresar.setLocation(
                        (getWidth() / 2) - (btnRegresar.getWidth() / 2),
                        getHeight() - btnRegresar.getHeight() - 60);
            }
        });
    }
}
