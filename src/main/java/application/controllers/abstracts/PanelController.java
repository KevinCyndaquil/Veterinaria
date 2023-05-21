package application.controllers.abstracts;


import javax.swing.*;

public abstract class PanelController <P extends JPanel> implements EventsController{
    protected P panel;

    public PanelController(P panel) {
        this.panel = panel;
    }

    public P initPanel() {
        if (panel != null)
            initEvents();

        return panel;
    }
}
