package application.views.components.abstracts;

import application.views.components.interfaces.IPositionableComponent;
import application.views.utils.Positions;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class CustomJLabel extends JLabel implements IPositionableComponent {
    public CustomJLabel() {
        super();
    }
    public CustomJLabel(String text) {
        super(text);
    }

    @Override
    public void setLocation(@NotNull JPanel fatherJPanel, Positions position) {

        switch (position) {
            case CENTER ->
                    super.setLocation(fatherJPanel.getWidth() / 2 - getWidth() / 2, fatherJPanel.getHeight() / 2 - getHeight() / 2);
            case LEFT -> super.setLocation(0, fatherJPanel.getHeight() / 2 - getHeight() / 2);
            case RIGHT ->
                    super.setLocation(fatherJPanel.getWidth() - getWidth(), fatherJPanel.getHeight() / 2 - getHeight() / 2);
            case TOP -> super.setLocation(fatherJPanel.getWidth() / 2 - getWidth() / 2, 0);
            case BOTTOM ->
                    super.setLocation(fatherJPanel.getWidth() / 2 - getWidth() / 2, fatherJPanel.getHeight() - getHeight());
        }
    }

    @Override
    public void setLocation(@NotNull JPanel fatherJPanel, Positions position, int x, int y) {

        switch (position) {
            case CENTER ->
                    super.setLocation(fatherJPanel.getWidth() / 2 - getWidth() / 2 + x, fatherJPanel.getHeight() / 2 - getHeight() / 2 + y);
            case LEFT -> super.setLocation(0, fatherJPanel.getHeight() / 2 - getHeight() / 2);
            case RIGHT ->
                    super.setLocation(fatherJPanel.getWidth() - getWidth() , fatherJPanel.getHeight() / 2 - getHeight() / 2);
            case TOP -> super.setLocation(fatherJPanel.getWidth() / 2 - getWidth() / 2, 0);
            case BOTTOM ->
                    super.setLocation(fatherJPanel.getWidth() / 2 - getWidth() / 2, fatherJPanel.getHeight() - getHeight());
        }
    }
}
