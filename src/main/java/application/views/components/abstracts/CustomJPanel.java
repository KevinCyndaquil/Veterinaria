package application.views.components.abstracts;

import application.views.components.interfaces.IPositionableComponent;
import application.views.utils.Positions;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class CustomJPanel extends JPanel implements IPositionableComponent {
    @Override
    public void setLocation(@NotNull JComponent fatherJComponent, Positions position){

        switch (position) {
            case CENTER ->
                    super.setLocation(fatherJComponent.getWidth() / 2 - getWidth() / 2, fatherJComponent.getHeight() / 2 - getHeight() / 2);
            case LEFT -> super.setLocation(0, fatherJComponent.getHeight() / 2 - getHeight() / 2);
            case RIGHT ->
                    super.setLocation(fatherJComponent.getWidth() - getWidth(), fatherJComponent.getHeight() / 2 - getHeight() / 2);
            case TOP -> super.setLocation(fatherJComponent.getWidth() / 2 - getWidth() / 2, 0);
            case BOTTOM ->
                    super.setLocation(fatherJComponent.getWidth() / 2 - getWidth() / 2, fatherJComponent.getHeight() - getHeight());
        }
    }

    @Override
    public void setLocation(@NotNull JComponent fatherJComponent, Positions position, int x, int y) {

        switch (position) {
            case CENTER ->
                    super.setLocation(fatherJComponent.getWidth() / 2 - getWidth() / 2 + x, fatherJComponent.getHeight() / 2 - getHeight() / 2 + y);
            case LEFT -> super.setLocation(x, fatherJComponent.getHeight() / 2 - getHeight() / 2 + y);
            case RIGHT ->
                    super.setLocation(fatherJComponent.getWidth() - getWidth() + x, fatherJComponent.getHeight() / 2 - getHeight() / 2 + y);
            case TOP -> super.setLocation(fatherJComponent.getWidth() / 2 - getWidth() / 2 + x, y);
            case BOTTOM ->
                    super.setLocation(fatherJComponent.getWidth() / 2 - getWidth() / 2 +x, fatherJComponent.getHeight() - getHeight() + y);
        }
    }
}
