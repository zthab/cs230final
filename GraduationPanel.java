import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class GraduationPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GraduationPanel extends JPanel 
{
    
    /**
     * Constructor for objects of class GraduationPanel
     */
    public GraduationPanel()
    {
        setBackground (Color.green);
       JTextArea l1 = new JTextArea ("graduation note");
                                    
       l1.setBackground(Color.green); //so that the JTextArea matches the panel background
       add (l1);
    }

}
