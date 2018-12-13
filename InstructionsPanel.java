import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class InstructionsPanel here.
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.1.18)
 */
public class InstructionsPanel extends JPanel 
{
       /**
     * Constructs up a JPanel with two labels.
     */
    public InstructionsPanel()
    {
       setBackground (Color.green);
       
       
      
       JTextArea l1 = new JTextArea ("Welcome to The Wellesley Trail!\n The goal of this game is to" +
                                     " make it to graduation without getting lost in the tunnels," + 
                                     " attacked by geese, or dying from sleep deprivation." + 
                                     "\nFirst you will pick from six different archtypes; each have" +
                                     " a different set of points for the sleep, smart and social" +
                                     " categories. \nYou will then be prompted with questions and will" +
                                     " have two options to pick from." + 
                                     "\nBased on some decisions, you will have to play a mini-game." +
                                      " If you loose this mini-game, you loose everything." +
                                      "\nYou will also gain or loose points in the three categories" +
                                      " based on the decisions you choose. \nIf " +
                                      " any of these categories drop below zero, you loose, so " +
                                      " choose wisely and good luck!!");
                       
       l1.setBackground(Color.green); //so that the JTextArea matches the panel background
       add (l1);
       
       JButton next = new JButton("Choose your character -->");
       next.addActionListener(new ButtonListener());
       add(next);
    }
    
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event){
            JButton button = (JButton)event.getSource();
            JPanel buttonPanel = (JPanel)button.getParent();
            JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
            //make new situation panel from source
            CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
            CharSelection nextPanel = new CharSelection(); 
            cardLayoutPanel.add(nextPanel,"3");
            layout.show(cardLayoutPanel, "3");
        }
    }
}
