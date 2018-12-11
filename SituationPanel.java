import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class SituationGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SituationPanel extends JPanel

{
    // instance variables - replace the example below with your own
    private Situation sit;

    /**
     * Constructor for objects of class SituationGUI
     */
    public SituationPanel(Situation s)
    {
        sit = s;
        setBackground (Color.green);
       JTextArea l1 = new JTextArea (s.getQuestion());
                                    
       l1.setBackground(Color.green); //so that the JTextArea matches the panel background
       add (l1);
       //code for switching 
       JButton button = (JButton)e.getSource();
JPanel buttonPanel = (JPanel)button.getParent();
JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
layout.show(cardLayoutPanel, "2");
    }
private class ButtonListener implements ActionListener
    {
        //-----------------------------------------------------------------
        // Sets the text of the label depending on which radio
        // button was pressed.
        //-----------------------------------------------------------------
        public void actionPerformed (ActionEvent event)
        {
            
        }
    }//can use jlayered panel for selection

}
