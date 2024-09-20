import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI implements ActionListener {

    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    JMenuItem menuItemNew, menuItemOpen, menuItemSave, menuItemSaveAs, menuItemExit, menuItemUndo,
            menuItemRedo, menuItemCut, menuItemCopy, menuItemPaste, menuItemSelectAll,
            menuItemClear, menuItemFont, menuItemBackgroundColor;
    private JPanel backgroundPanel;

    Function_File file = new Function_File(this);

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
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
        menuItemSaveAs.setMnemonic('W');
        menuItemSaveAs.setFocusable(true);
        menuItemSaveAs.setActionCommand("Save as");
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
        }
    }
}
