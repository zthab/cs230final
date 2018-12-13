import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class DeathPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DeathPanel extends JPanel {
    private JPanel death, deck;
    private CardLayout cl;//container for the panels
    
    /**
     * Constructor for objects of class DeathPanel
     */
    public DeathPanel()
    {     
       deck = new JPanel(new CardLayout());//this lets me switch panels and it's dope
       cl = (CardLayout)(deck.getLayout());//manages the deck 
        
       deck.add(death(), "dying");
       
       add(deck);
    }
    
    private JPanel death(){
       death = new JPanel();
       JTextArea l1 = new JTextArea ("death note");
       
       death.setBackground (Color.green);                                           
       l1.setBackground(Color.green); //so that the JTextArea matches the panel background
       
       death.add(l1);

       return  death;
    }
}
