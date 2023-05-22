package application.views.components;

import application.views.components.abstracts.CustomJTextField;
import application.views.utils.Fonts;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InputText extends CustomJTextField {
    String placeholder;

    public InputText(String placeholder) {
        super();

        this.placeholder = placeholder;

        setSize(344, 58);
        setFont(Fonts.load(Fonts.MONSERRAT).deriveFont(Font.BOLD, 20f));
        PromptSupport.setBackground(Color.white, this);
        setText(placeholder);
        setForeground(Color.LIGHT_GRAY);
        PromptSupport.setForeground(new Color(0,0,0,95), this);
        PromptSupport.setFontStyle(Font.ITALIC, this);
        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0,0,0,25)));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.DARK_GRAY);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);

                if (getText().equals("")) {
                    setText(placeholder);
                    setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    public void vaciar() {
        this.setText(placeholder);
    }
}
