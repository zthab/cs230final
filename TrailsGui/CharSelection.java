package TrailsGui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Write a description of class CharSelection here.
 *
 * @author Zahra Thabet
 * @version (a version number or a date)
 */
public class CharSelection extends JPanel
{
    private JLabel quote;
    private JRadioButton comedy, philosophy, carpentry;
    //private String comedyQuote, philosophyQuote, carpentryQuote;
    public CharSelection()
    {
        //comedyQuote = "Take my wife, please.";
        //philosophyQuote = "I think, therefore I am.";
        //carpentryQuote = "Measure twice. Cut once.";
        //quote = new JLabel (comedyQuote);
        //quote.setFont (new Font ("Helvetica", Font.BOLD, 24));
        //was working right here need to figure out how to get tooltiptext to work for showing stats. 
        //might add pic of avatar
        comedy = new JRadioButton ("Wendy Wellesley", true);
        comedy.setToolTipText("Click this button to disable the middle button.");

        comedy.setBackground (Color.green);
        philosophy = new JRadioButton ("Philosophy");
        philosophy.setBackground (Color.green);
        carpentry = new JRadioButton ("Carpentry");
        carpentry.setBackground (Color.green);
        ButtonGroup group = new ButtonGroup();
        group.add (comedy);
        group.add (philosophy);
        group.add (carpentry);
        QuoteListener listener = new QuoteListener();
        comedy.addActionListener (listener);
        philosophy.addActionListener (listener);
        carpentry.addActionListener (listener);
        //add (quote);
        add (comedy);
        add (philosophy);
        add (carpentry);
        setBackground (Color.green);
        setPreferredSize (new Dimension(300, 100));
    }
    //*****************************************************************
    // Represents the listener for all radio buttons
    //*****************************************************************
    private class QuoteListener implements ActionListener
    {
        //-----------------------------------------------------------------
        // Sets the text of the label depending on which radio
        // button was pressed.
        //-----------------------------------------------------------------
        public void actionPerformed (ActionEvent event)
        {
            Object source = event.getSource();
            if (source == comedy){
               // quote.setText (comedyQuote);
            }else if (source == philosophy){
                //quote.setText (philosophyQuote);
            }else{
               // quote.setText (carpentryQuote);
            }
        }
    }//can use jlayered panel for selection

}
