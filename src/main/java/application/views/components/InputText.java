package application.views.components;

import application.views.components.abstracts.CustomJTextField;
import application.views.utils.Fonts;
import org.jdesktop.swingx.prompt.PromptSupport;

import java.awt.*;

public class InputText extends CustomJTextField {
    public InputText(String placeholder) {
        super();
        setSize(344, 58);
        setFont(Fonts.load(Fonts.MONSERRAT).deriveFont(Font.BOLD, 20f));
        PromptSupport.setPrompt(placeholder, this);
        PromptSupport.setForeground(Color.black, this);
        PromptSupport.setFontStyle(Font.ITALIC, this);
    }
}
