package application.views.components;

import application.views.components.abstracts.CustomJPassword;
import application.views.utils.Fonts;
import org.jdesktop.swingx.prompt.PromptSupport;

import java.awt.*;

public class InputPassword extends CustomJPassword {
    public InputPassword(String placeholder) {
        super();
        setSize(344, 58);
        setFont(getFontsLoader().load(Fonts.MONSERRAT).deriveFont(Font.BOLD, 20f));
        PromptSupport.setPrompt(placeholder, this);
        PromptSupport.setForeground(Color.black, this);
        PromptSupport.setFontStyle(Font.ITALIC, this);
    }
}
