package application.views.components;

import application.views.components.abstracts.CustomJPanel;
import application.views.utils.Positions;


public class Banner extends CustomJPanel {
    private final Logo logo;
    private final NombreEmpresa nombreEmpresa;

    public Banner() {
        setLayout(null);
        logo = new Logo();
        add(logo);

        nombreEmpresa = new NombreEmpresa();
        add(nombreEmpresa);

        setSize(nombreEmpresa.getWidth(), nombreEmpresa.getHeight() + logo.getHeight());
        logo.setLocation(this, Positions.TOP);
        nombreEmpresa.setLocation(this, Positions.BOTTOM);

    }
}
