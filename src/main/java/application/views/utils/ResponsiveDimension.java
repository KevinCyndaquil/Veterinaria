package application.views.utils;
import java.awt.*;

public class ResponsiveDimension {
    //this method return the screen size of the computer where the app is running
    private static Dimension getScreenSize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return toolkit.getScreenSize();
    }
    //return the resolution preferred for the app depending on the screen size
    public static Resolutions getNextResolution() {
        Dimension screenSize = getScreenSize();

        if (screenSize.equals(Resolutions.FULL_HD.getSize())) {
            return Resolutions.WXGA;
        } else if (screenSize.equals(Resolutions.WXGA.getSize())) {
            return Resolutions.HD;
        } else if (screenSize.equals(Resolutions.HD.getSize())) {
            return Resolutions.QHD;
        } else if (screenSize.equals(Resolutions.QHD.getSize())) {
            return Resolutions.FWVGA;
        } else {
            return Resolutions.FWVGA;
        }
    }

}
