import java.awt.*;

public class Function_File {

    GUI gui;

    public Function_File(GUI gui){
        this.gui = gui;

    }

    public void newFile(){

        gui.textArea.setText("");
        gui.window.setTitle("New");
    }

    public void open() {

        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);
    }
}
