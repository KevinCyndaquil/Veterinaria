package application.views.utils;
import java.awt.*;

public class ResponsiveDimension {
    //this method return the screen size of the computer where the app is running
    private static Dimension getScreenSize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return toolkit.getScreenSize();
    }

    //return the resolution preferred for the app depending on the screen size
    public static Dimension getNextResolution() {
        Dimension screenSize = getScreenSize();

        if (screenSize.equals(Resolutions.FULL_HD.getSize())) {
            return Resolutions.WXGA.getSize();
        } else if (screenSize.equals(Resolutions.WXGA.getSize())) {
            return Resolutions.HD.getSize();
        } else if (screenSize.equals(Resolutions.HD.getSize())) {
            return Resolutions.QHD.getSize();
        } else if (screenSize.equals(Resolutions.QHD.getSize())) {
            return Resolutions.FWVGA.getSize();
        } else {
            return Resolutions.FWVGA.getSize();
        }
    }


}
