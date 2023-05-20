package application.controllers.abstracts;

import javax.swing.*;

public abstract class ViewerController <V extends JFrame> implements EventsController {
    protected V view;
    public ViewerController(V view) {
        this.view = view;

        if (throwView())
            initEvents();
    }

    public boolean throwView() {
        if (view == null)
            return false;

        view.setVisible(!view.isVisible());
        return true;
    }
}
