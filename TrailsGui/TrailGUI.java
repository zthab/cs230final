package TrailsGui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class WellesleyTrailGUI here.
 * look into copyright 
 * @author (zthabet,nbryant2,gbronzi)
 * @version (12.1.18)
 */
public class TrailGUI implements ItemListener
{
        JPanel trailGame; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";
    JButton nextB;
    public void addComponents(Container pane){
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout ///change this to button
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL }; //change this to tree?
        //JComboBox cb = new JComboBox(comboBoxItems);
        //cb.setEditable(false);
        //cb.addItemListener(this);
        //comboBoxPane.add(cb); //change this 
         nextB = new JButton ("Next");
         nextB.addItemListener(this);
        //make Jpanels here 
        JPanel card1 = new StartPanel();
   
        JPanel card2 = new CharSelection();
 
         
        //Create the panel that contains the "cards".
        trailGame = new JPanel(new CardLayout());
        trailGame.add(card1, BUTTONPANEL);
        trailGame.add(card2, TEXTPANEL);
         
        pane.add(nextB, BorderLayout.PAGE_START); //prev code's switch method
        pane.add(trailGame, BorderLayout.CENTER);
    }
        public void itemStateChanged(ItemEvent evt) { //want to use this when next button is clicked
        CardLayout cl = (CardLayout)(trailGame.getLayout());
        System.out.println("HERE");
        cl.show(trailGame, TEXTPANEL); //change what it shows 
    }
    public static void main (String[] args) 
    {

                JFrame frame = new JFrame("Wellesley Trails");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        TrailGUI demo = new TrailGUI();
        demo.addComponents(frame.getContentPane());
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        

    } 
}
