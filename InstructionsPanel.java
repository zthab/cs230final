import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Instructions panel for The Wellesley Trail
 *
 * @author  gbronzi
 * @author  nbryant2
 * @author  zthabet
 * @version 12.17.18
 */
public class InstructionsPanel extends JPanel 
{
    private JPanel content;
    
    /**
     * Constructs up a JPanel with two labels.
     */
    public InstructionsPanel(){
        // setBackground (Color.green);

        // JTextArea l1 = new JTextArea ("Welcome to The Wellesley Trail!\n The "+
        // "goal of this game is to make it to graduation without "+
        // "getting lost in the tunnels, attacked by geese, or "+
        // "dying from sleep deprivation.\nFirst you will pick "+
        // "from six different archtypes; each have a different set"
        // +" of points for the sleep, smart and social categories."+
        // "\nYou will then be prompted with questions and will "+
        // "have two options to pick from.\nBased on some "+
        // "decisions, you will have to play a mini-game. If you "+
        // "lose this mini-game, you lose everything.\nYou will "+
        // "also gain or loose points in the three categories based"
        // +" on the decisions you choose.\nIf any of these "+
        // "categories drop below zero, you lose, so choose wisely "
        // +"and good luck!!");

        // l1.setBackground(Color.green); 
        // add (l1);

        JButton next = new JButton("Choose your character -->");
        next.addActionListener(new ButtonListener());
        // add(next);

        JTextArea top = new JTextArea("Welcome to the Wellesley Trail!");
        JTextArea left = new JTextArea("Goals:\nMake it to graduation without being:\n   Attacked by geese" +
                 "\n   Lost in the tunnels\n   Dying of sleep deprivation\n   Or any of the other perils of Wellesley College");
        JTextArea right = new JTextArea("Instructions:          \n" + "Most importantly, you must keep all three of your stats" + 
                         "\nabove zero. These stats are sleep, smart, and social \npoints." + 
                         " The decisions you make in this game will impact\nthose stats. If any drop below zero, you lose!" +
                         "\nAdditionally, some decisions will lead you to mini games.\nIf you lose any of those, you lose it all!");
        JTextArea center = new JTextArea("");

        //top.setFont(JTextArea.getFont().deriveFont(16f));
        //editingArea(top.getFontName(), top.getStyle(), top.getSize()+5);
        top.setPreferredSize(new Dimension(1200, 100));

        setLayout(new BorderLayout());
        add(next,BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(left, BorderLayout.LINE_START);
        add(right, BorderLayout.LINE_END);  
    }

    /**
     * Sets the listener for the action which will occure when the user clicks the button
     */
    private class ButtonListener implements ActionListener
    {
        /**
         * When the button is selected, create a character panel and go to it
         */
        public void actionPerformed (ActionEvent event){
            JButton button = (JButton)event.getSource();
            JPanel buttonPanel = (JPanel)button.getParent();
            JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
            //make new situation panel from source
            CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
            CharSelection nextPanel = new CharSelection(); 
            cardLayoutPanel.add(nextPanel,"charSelec");
            layout.show(cardLayoutPanel, "charSelec");
        }
    }

    // private JPanel content(){
        // JPanel content = new JPanel();     
        // JTextArea top = new JTextArea("Welcome to the Wellesley Trail!");
        // top.setFont(JTextArea.getFont().deriveFont(16f));
        
        // return content;
    // }
}
