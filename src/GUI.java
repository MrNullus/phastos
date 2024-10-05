import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI implements ActionListener {

    JFrame window;
    private JPanel backgroundPanel;
    boolean wordWrapOn = false;

    //    TEXT AREA
    JTextArea textArea;
    JScrollPane scrollPane;

    //    TOP MENU
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;

    //    FILE MENU
    JMenuItem menuItemNew, menuItemOpen, menuItemSave, menuItemSaveAs, menuItemExit, menuItemUndo, menuItemRedo, menuItemCut, menuItemCopy, menuItemPaste, menuItemSelectAll, menuItemClear, menuItemFont, menuItemBackgroundColor;

    //    FORMAT MENU
    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28;
    JMenu menuFont, menuFontSize;

    // COLORS MENU
    JMenuItem iColor1, iColor2, iColor3;

    //     FUNCTIONS
    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Colors colors = new Function_Colors(this);

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {

        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createFormatMenu();
        createColorMenu();

        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        colors.changeColor("Color2");

        window.setVisible(true);
    }

    public void createBackgroundPanel() {
        backgroundPanel = new JPanel() {
            private BufferedImage backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(getClass().getResource("/resources/zerotwo.jpeg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        window.setContentPane(backgroundPanel);
    }

    public void createWindow() {
        window = new JFrame("Phastos");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createTextArea() {
        textArea = new JTextArea();

        textArea.setBackground(new Color(0xEF000000, true));
        textArea.setCaretColor(new Color(0xE9FD0853, true));
        textArea.setSelectionColor(new Color(0xBAFD0853, true));
        textArea.setSelectedTextColor(new Color(0xFFFFFEFE, true));
        textArea.setFont(new Font("Consolas", Font.BOLD, 14));
        textArea.setForeground(new Color(0xF6E8E8));

        textArea.setMargin(new Insets(20, 40, 20, 40));

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        window.add(scrollPane);
    }

    public void createMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setForeground(new Color(0xF6E8E8));
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);
        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);
        menuColor = new JMenu("Color");
        menuBar.add(menuColor);
    }

    public void createFileMenu() {
        menuItemNew = new JMenuItem("New");
        menuItemNew.addActionListener(this);
        menuItemNew.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        menuItemNew.setMnemonic('N');
        menuItemNew.setFocusable(true);
        menuItemNew.setActionCommand("New");
        menuFile.add(menuItemNew);

        menuItemOpen = new JMenuItem("Open");
        menuItemOpen.addActionListener(this);
        menuItemOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        menuItemOpen.setMnemonic('O');
        menuItemOpen.setFocusable(true);
        menuItemOpen.setActionCommand("Open");
        menuFile.add(menuItemOpen);

        menuItemSave = new JMenuItem("Save");
        menuItemSave.addActionListener(this);
        menuItemSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        menuItemSave.setMnemonic('S');
        menuItemSave.setFocusable(true);
        menuItemSave.setActionCommand("Save");
        menuFile.add(menuItemSave);

        menuItemSaveAs = new JMenuItem("Save as");
        menuItemSaveAs.addActionListener(this);
        menuItemSaveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
        menuItemSaveAs.setMnemonic('A');
        menuItemSaveAs.setFocusable(true);
        menuItemSaveAs.setActionCommand("SaveAs");
        menuFile.add(menuItemSaveAs);
        menuFile.addSeparator();

        menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(this);
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
        menuItemExit.setMnemonic('Q');
        menuItemExit.setFocusable(true);
        menuItemExit.setActionCommand("Exit");
        menuFile.add(menuItemExit);

        menuFile.setFont(new Font("Consolas", Font.BOLD, 14));
    }

    public void createFormatMenu() {
        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setAccelerator(KeyStroke.getKeyStroke("ctrl W"));
        iWrap.setMnemonic('W');
        iWrap.setFocusable(true);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        // Items Menu Font
        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCSMS);

        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        // Items Menu Font Size
        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("size8");
        menuFontSize.add(iFontSize8);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("size12");
        menuFontSize.add(iFontSize12);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("size16");
        menuFontSize.add(iFontSize16);

        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("size20");
        menuFontSize.add(iFontSize20);

        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("size24");
        menuFontSize.add(iFontSize24);

        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("size28");
        menuFontSize.add(iFontSize28);


        menuFont.setFont(new Font("Consolas", Font.BOLD, 14));
        menuFontSize.setFont(new Font("Consolas", Font.BOLD, 14));
    }

    public void createColorMenu() {
        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("Color1");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("Red");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Color2");
        menuColor.add(iColor2);

        iColor3 = new JMenuItem("Violet");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Color3");
        menuColor.add(iColor3);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();

        switch (command) {
            case "New":
                file.newFile();
                break;
            case "Open":
                file.open();
                break;
            case "Save":
                file.save();
                break;
            case "SaveAs":
                file.saveAs();
                break;
            case "Exit":
                file.exit();
                break;
            case "Arial":
                format.setFont(command);
                break;
            case "Comic Sans MS":
                format.setFont(command);
                break;
            case "Times New Roman":
                format.setFont(command);
                break;
            case "Word Wrap":
                format.wordWrap();
                break;
            case "size8":
                format.createFont(8);
                break;
            case "size12":
                format.createFont(12);
                break;
            case "size16":
                format.createFont(16);
                break;
            case "size20":
                format.createFont(20);
                break;
            case "size24":
                format.createFont(24);
                break;
            case "size28":
                format.createFont(28);
                break;
            case "Color1":
                colors.changeColor(command);
                break;
            case "Color2":
                colors.changeColor(command);
                break;
            case "Color3":
                colors.changeColor(command);
                break;
        }
    }
}
