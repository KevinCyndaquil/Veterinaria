package application.views.components;

import application.views.components.abstracts.CustomJTextField;
import application.views.utils.Fonts;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;

public class InputText extends CustomJTextField {
    public InputText(String placeholder) {
        super();
        setSize(344, 58);
        setFont(Fonts.load(Fonts.MONSERRAT).deriveFont(Font.BOLD, 20f));
        PromptSupport.setBackground(Color.white, this);
        PromptSupport.setPrompt(placeholder, this);
        PromptSupport.setForeground(new Color(0,0,0,95), this);
        PromptSupport.setFontStyle(Font.ITALIC, this);
        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0,0,0,25)));
    }
}
