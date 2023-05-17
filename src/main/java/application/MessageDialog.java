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

    public static void queryErrorMessage(JFrame view, String message) {
        JOptionPane.showMessageDialog(
                view,
                message,
                "query error",
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

    public static void stupidMessage(JFrame view, String message) {
        JOptionPane.showMessageDialog(
                view,
                message,
                "are you kidding me?",
                JOptionPane.INFORMATION_MESSAGE,
                Icons.get(Icons.AMBER_OUTTA_MY_WAY));
    }

    public static boolean yesNoQuestionMessage(JFrame view, String message) {
        return JOptionPane.showOptionDialog(
                view,
                message,
                "choose question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                Icons.get(Icons.HU_TAO_FRIGHTEN),
                new Object[]{"yes", "no"},
                0) == 0;
    }
}
