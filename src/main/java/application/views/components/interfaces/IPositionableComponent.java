package application.views.components.interfaces;

import application.views.utils.Positions;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;

public interface IPositionableComponent {
    /**
     * This method set the location of the logo in the center of the fatherJPanel
     *
     * @param fatherJComponent the fatherJPanel where the logo is going to be placed 120
     */
    void setLocation(@NotNull JComponent fatherJComponent, Positions position);
    /**
     * This method set the location of the logo in the center of the fatherJPanel
     *
     * @param fatherJComponent the fatherJPanel where the logo is going to be placed
     * @param position    the position of the logo CENTER, LEFT, RIGHT, TOP, BOTTOM
     * @param x            the more or less x position of the logo
     * @param y            the more or less y position of the logo
     */
    void setLocation(@NotNull JComponent fatherJComponent, Positions position, int x, int y);
}
