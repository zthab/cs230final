import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class DeathPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DeathPanel extends JPanel 
{
   

    /**
     * Constructor for objects of class DeathPanel
     */
    public DeathPanel()
    {
        setBackground (Color.green);
       JTextArea l1 = new JTextArea ("death note");
                                    
       l1.setBackground(Color.green); //so that the JTextArea matches the panel background
       add (l1);
    }

}
