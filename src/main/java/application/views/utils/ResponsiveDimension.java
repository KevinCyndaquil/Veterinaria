package application.views.utils;


import java.awt.*;

public class ResponsiveDimension {
    //this method return the screen size of the computer where the app is running
    private static Dimension getScreenSize() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return toolkit.getScreenSize();
    }

    //return the resolution preferred for the app depending on the screen size
    public static Dimension getResolution() {
        Dimension screenSize = getScreenSize();

        if(screenSize.equals(Resolutions.FULL_HD.getSize())){
            return Resolutions.WXGA.getSize();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getResolution());
    }
}
