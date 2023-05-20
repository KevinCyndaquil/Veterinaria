package application.views.components;

import application.views.components.abstracts.CustomJPanel;
import application.views.utils.Positions;


public class Banner extends CustomJPanel {
    private Logo logo;
    private TextDisplay nombreEmpresa;

    @Override
    public void initComponents() {
        setLayout(null);
        logo = new Logo();
        add(logo);

        nombreEmpresa = new TextDisplay("Veterinaria Vida");
        add(nombreEmpresa);

        setSize(nombreEmpresa.getWidth(), nombreEmpresa.getHeight() + logo.getHeight());
        logo.setLocation(this, Positions.TOP);
        nombreEmpresa.setLocation(this, Positions.BOTTOM);
    }
}
