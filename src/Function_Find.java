import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import java.awt.*;

public class Function_Find {

    GUI gui;

    public Function_Find(GUI gui){
        this.gui = gui;
    }

    public void find(){

        String word = JOptionPane.showInputDialog("Enter word to find: ");

        if (word == null || word.isEmpty()){
            return;
        }

        String text = gui.textArea.getText();

        gui.textArea.getHighlighter().removeAllHighlights();

        int index = text.toLowerCase().indexOf(word.toLowerCase());

        while(index >= 0){
            try{
                gui.textArea.getHighlighter().addHighlight(
                        index,
                        index + word.length(),
                        new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW)
                );

                index = text.toLowerCase().indexOf(word.toLowerCase(), index + word.length());

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}
