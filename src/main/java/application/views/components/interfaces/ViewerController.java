package application.views.components.interfaces;

import javax.swing.*;

public abstract class ViewerController <V extends JFrame> {
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
    public abstract void initEvents();
}
