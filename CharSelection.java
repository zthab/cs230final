
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
    private JRadioButton athletic, hermit, horse, offCampus, society,wendy;
    private Person charac;
    //private String comedyQuote, philosophyQuote, carpentryQuote;
    public CharSelection(Person pers)
    {
        Person charac = pers;
        //comedyQuote = "Take my wife, please.";
        //philosophyQuote = "I think, therefore I am.";
        //carpentryQuote = "Measure twice. Cut once.";
        //quote = new JLabel (comedyQuote);
        //quote.setFont (new Font ("Helvetica", Font.BOLD, 24));
        //was working right here need to figure out how to get tooltiptext to work for showing stats. 
        //might add pic of avatar
        athletic = new JRadioButton ("Athletic Alex", true);
        athletic.setToolTipText("Click this button to disable the middle button.");
        athletic .setBackground (Color.green);
        
        hermit = new JRadioButton ("Hermit Harper");
        hermit.setBackground (Color.green);
        
        horse = new JRadioButton ("Horse Girl Grace");
        horse.setBackground (Color.green);
        
        offCampus = new JRadioButton ("Off-Campus Ollie");
        offCampus.setBackground (Color.green);
        
        society = new JRadioButton ("Society Skylar");
        society.setBackground (Color.green);
        
        wendy = new JRadioButton ("Wendy Wellesley");
        wendy.setBackground (Color.green);
        
        ButtonGroup group = new ButtonGroup();
        group.add (athletic);
        group.add (hermit);
        group.add (horse);
        group.add(offCampus);
        group.add(society);
        group.add(wendy);
        
        QuoteListener listener = new QuoteListener();
        athletic.addActionListener (listener);
        hermit.addActionListener (listener);
        horse.addActionListener (listener);
        offCampus.addActionListener (listener);
        society.addActionListener (listener);
        wendy.addActionListener (listener);
        //add (quote);
        add (athletic);
        add (hermit);
        add (horse);
        add (offCampus);
        add (society);
        add (wendy);
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
            if (source == athletic){
               charac=new Person(0);
            }else if (source == hermit){
                charac = new Person(1);
            }else if (source == horse){
               charac = new Person(2);
            }else if (source == offCampus){
                charac=new Person(3);
            }else if (source == society){
                charac = new Person(4);
            }else{
                charac = new Person(5);
            }
        }
    }//can use jlayered panel for selection

}
