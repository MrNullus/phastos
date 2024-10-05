import java.awt.*;

public class Function_Colors {
    GUI gui;

    public Function_Colors(GUI gui) {
        this.gui = gui;
    }

    public void changeColor(String color) {
        switch (color) {
            case "Color1":
                gui.window.getContentPane().setBackground(Color.white);
                gui.textArea.setBackground(Color.white);
                gui.textArea.setCaretColor(Color.black);
                gui.textArea.setSelectionColor(Color.gray);
                gui.textArea.setSelectedTextColor(Color.black);
                gui.textArea.setForeground(Color.black);
                break;
            case "Color2":
            default:
                gui.textArea.setBackground(new Color(0xEF000000, true));
                gui.textArea.setCaretColor(new Color(0xE9FD0853, true));
                gui.textArea.setSelectionColor(new Color(0xBAFD0853, true));
                gui.textArea.setSelectedTextColor(new Color(0xFFFFFEFE, true));
                gui.textArea.setForeground(Color.white);
                break;
            case "Color3":
                gui.textArea.setBackground(new Color(0xEF000000, true));
                gui.textArea.setCaretColor(new Color(0x80FD08DC, true));
                gui.textArea.setSelectionColor(new Color(0x80FD08DC, true));
                gui.textArea.setSelectedTextColor(new Color(0xFFFFFEFE, true));
                break;
        }
    }
}
