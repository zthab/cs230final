import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class InstructionsPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class InstructionsPanel extends JPanel 
{
       /**
     * Constructs up a JPanel with two labels.
     */
    public InstructionsPanel()
    {
       setBackground (Color.green);
       JTextArea l1 = new JTextArea ("put in instructions");
                                    
       l1.setBackground(Color.green); //so that the JTextArea matches the panel background
       add (l1);
       
    }
}
