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
    private String question;
    private Option option1, option2;
    private JTextArea questionText;
    private JButton option1Button, option2Button;
    private Person player;

    /**
     * Constructor for objects of class SituationGUI
     */
    public SituationPanel(Situation s, Person p)
    {
        player = p;
        sit = s;
        question = sit.getQuestion();
        option1=sit.getOption1();
        option2 = sit.getOption2();
        setBackground (Color.green);
        
        questionText = new JTextArea (question);
        option1Button = new JButton(option1.getDecision());
        option2Button = new JButton(option2.getDecision());
        
        option1Button.addActionListener(new ButtonListener());
        option2Button.addActionListener(new ButtonListener());
        
        questionText.setBackground(Color.green); //so that the JTextArea matches the panel background
        add (questionText);
        add (option1Button);
        add (option2Button);
        //code for switching 

    }
    
    private class ButtonListener implements ActionListener
    {
        //-----------------------------------------------------------------
        // Sets the text of the label depending on which radio
        // button was pressed.
        //-----------------------------------------------------------------
        public void actionPerformed (ActionEvent event)
        {
            if (event.getSource().equals(option1Button)){
                player.detractAllScores(option1.getPoints());
                
                JButton button = (JButton)event.getSource();
            JPanel buttonPanel = (JPanel)button.getParent();
            JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
            CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
            layout.show(cardLayoutPanel, "2");
            }else if (event.getSource().equals(option2Button)){
                player.detractAllScores(option2.getPoints());
            }
            
        }
    }//can use jlayered panel for selection

}
