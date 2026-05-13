import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;

public class GUI implements ActionListener {
    JFrame window;

    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;

    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;

    JMenuItem iNew, iOpen, iSave, iSaveas, iExit;

    JMenuItem iUndo, iRedo;

    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28;
    JMenu menuFont,menuFontSize;

    JMenuItem iColor1, iColor2, iColor3;

    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Color color = new Function_Color(this);
    Function_Edit edit = new Function_Edit(this);

    KeyHandler kHandler = new KeyHandler(this);

    UndoManager um =new UndoManager();

    JLabel statusBar;

    public static void main(String[] args){
        new GUI();
    }

    public GUI(){
        createWindow();
        createTextArea();
        createStatusBar();
        createMenuBar();
        createEditMenu();
        createFileMenu();
        createFormatMenu();
        createColorMenu();

        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();

        color.changeColor("White");

        window.setVisible(true);

    }

    public void updateStatus(){

        String text = textArea.getText();

        int characters = text.length();

        String wordsArray[] = text.trim().split("\\s+");

        int words = 0;

        if(text.trim().isEmpty()){
            words = 0;
        }
        else{
            words = wordsArray.length;
        }

        statusBar.setText("Words: " + words + " Characters: " + characters);
    }

    public void createWindow(){
        window = new JFrame("Notepad");
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
    }

    public void createTextArea(){

        textArea = new JTextArea();

        textArea.addKeyListener(kHandler);

        textArea.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                updateStatus();
            }

            public void removeUpdate(DocumentEvent e) {
                updateStatus();
            }

            public void changedUpdate(DocumentEvent e) {
                updateStatus();
            }
        });

        textArea.getDocument().addUndoableEditListener(
               new UndoableEditListener(){
                   public void undoableEditHappened(UndoableEditEvent e){
                       um.addEdit(e.getEdit());
                   }
               }
        );

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    public void createStatusBar(){

        statusBar = new JLabel("Words: 0 Characters: 0");

        window.add(statusBar, BorderLayout.SOUTH);
    }


    public void createMenuBar(){

        menuBar = new JMenuBar();
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

    public void createEditMenu() {

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);


    }

    public void createFileMenu(){

        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);

        iSaveas = new JMenuItem("SaveAs");
        iSaveas.addActionListener(this);
        iSaveas.setActionCommand("SaveAs");
        menuFile.add(iSaveas);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);

    }

    public void createFormatMenu(){

        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

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

    }

    public void createColorMenu() {

        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Black");
        menuColor.add(iColor2);

        iColor3 = new JMenuItem("Blue");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Blue");
        menuColor.add(iColor3);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        String command = e.getActionCommand();

        switch(command){
            case "New": file.newFile(); break;
            case "Open": file.open(); break;
            case "Save": file.save(); break;
            case "SaveAs": file.saveAs(); break;
            case "Exit": file.exit(); break;
            case "Undo": edit.undo(); break;
            case "Redo": edit.redo(); break;
            case "Word Wrap": format.wordWrap(); break;
            case "Arial": format.setFont("Arial"); break;
            case "Comic Sans MS": format.setFont("Comic Sans MS"); break;
            case "Times New Roman": format.setFont("Times New Roman"); break;
            case "size8": format.createFont(8); break;
            case "size12": format.createFont(12); break;
            case "size16": format.createFont(16); break;
            case "size20": format.createFont(20); break;
            case "size24": format.createFont(24); break;
            case "size28": format.createFont(28); break;
            case "White": color.changeColor("White"); break;
            case "Black": color.changeColor("Black"); break;
            case "Blue": color.changeColor("Blue"); break;

        }

    }
}
