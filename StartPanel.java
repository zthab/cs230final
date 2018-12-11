
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class StartPanel here.
 *
 * @author Zahra Thabet
 * @version 12.7.18
 */
public class StartPanel extends JPanel
{
    
        /**
     * Constructs up a JPanel with two labels.
     */
    public StartPanel()
    {
       setBackground (Color.green);
       JTextArea l1 = new JTextArea ("Welcome to Wellesley Trails!");
                                    
       l1.setBackground(Color.green); //so that the JTextArea matches the panel background
       add (l1);
       
    }

}
