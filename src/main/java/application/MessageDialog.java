package application;

import application.views.components.Icons;

import javax.swing.*;

public class MessageDialog {
    public static void successMessage(JFrame view, String message) {
        JOptionPane.showMessageDialog(
                view,
                message,
                "success",
                JOptionPane.INFORMATION_MESSAGE,
                Icons.get(Icons.STAR_EYES_VENTI));
    }

    public static void querySuccessMessage(JFrame view, String message) {
        JOptionPane.showMessageDialog(
                view,
                message,
                "query success",
                JOptionPane.INFORMATION_MESSAGE,
                Icons.get(Icons.STAR_EYES_GANYU));
    }

    public static void errorMessage(JFrame view, String message) {
        JOptionPane.showMessageDialog(
                view,
                message,
                "error",
                JOptionPane.ERROR_MESSAGE,
                Icons.get(Icons.SHY_SUCROUSE));
    }

    public static void uncertainMessage(JFrame view, String message) {
        JOptionPane.showMessageDialog(
                view,
                message,
                "uncertain result",
                JOptionPane.INFORMATION_MESSAGE,
                Icons.get(Icons.UNCERTAIN_SUCROUSE));
    }

    public static void outMessage(JFrame view, String message) {
        JOptionPane.showMessageDialog(
                view,
                message,
                "we're gonna get out here",
                JOptionPane.INFORMATION_MESSAGE,
                Icons.get(Icons.HAPPY_SUCROUSE));
    }
}
