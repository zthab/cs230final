import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
/**
 * Creates a panel for a Situation object to be displayed with. Buttons
 * selected in this panel have the capability to lead to other panels.
 * Buttons pressed in this panel also affect a Person object whose sleep,
 * smart, and social scores are displayed.
 *
 * @author  gbronzi
 * @author  nbryant2
 * @author  zthabet
 * @version 12.17.18
 */
public class SituationPanel extends JPanel{

    private Situation sit; //the situation to be displayed
    private String question; //the question to be displayed
    private Option option1, option2; //options of the corresponding Situation
    private Person player; //the Person object to be affected by the Situation

    //text areas to be displayed in the Panel
    private JTextArea questionText, playerStatus;
    //buttons corresponding to the options of the Situation
    private JButton option1Button, option2Button;

    //the TrailsBinaryTree that the Situation displayed in this Panel is in
    private TrailsBinaryTree tree;

    /**
     * Constructor for objects of class SituationPanel. Creates a Situation
     * panel that displays a Situation.
     * 
     * @param p Person to be affected by decisions
     * @param t TrailsBinaryTree containing the tree of relevant Situations
     */
    public SituationPanel(Person p,TrailsBinaryTree t)
    {
        player = p;

        setBackground (Color.green);
        tree=t; 

        sit = tree.getCurrent();
        question = sit.getQuestion();
        option1=sit.getOptionLeft();
        option2 = sit.getOptionRight();

        questionText = new JTextArea (question);
        playerStatus = new JTextArea(player.toString());
        option1Button = new JButton(option1.getDecision());
        option2Button = new JButton(option2.getDecision());

        option1Button.addActionListener(new ButtonListener());
        option2Button.addActionListener(new ButtonListener());

        questionText.setBackground(Color.green); //so that the JTextArea matches the panel background
        add (questionText);
        add (option1Button);
        add (option2Button);
        add(playerStatus);
    }

    private class ButtonListener implements ActionListener
    {
        //-----------------------------------------------------------------
        // Sets the text of the label depending on which radio
        // button was pressed.
        //-----------------------------------------------------------------
        public void actionPerformed (ActionEvent event)
        {
            JButton button = (JButton)event.getSource();
            JPanel buttonPanel = (JPanel)button.getParent();
            JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
            CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
            try{
                if (event.getSource().equals(option1Button)){
                    player.setSleepScore(player.getSleepScore()+option1.getPoints()[0]);
                    //player.addAllScores(option1.getPoints());
                    if (player.isAboveZero()){
                        tree.nextLeft();
                        SituationPanel nextPanel = new SituationPanel(player, tree); 
                        cardLayoutPanel.add(nextPanel,"left");
                        layout.show(cardLayoutPanel, "left");
                    }else{
                        DeathPanel death = new DeathPanel(); 
                        cardLayoutPanel.add(death,"loss");
                        layout.show(cardLayoutPanel, "loss");
                    }
                }else if (event.getSource().equals(option2Button)){
                    player.addAllScores(option2.getPoints());
                    if (player.isAboveZero()){
                        tree.nextRight();
                        SituationPanel nextPanel = new SituationPanel(player, tree); 
                        cardLayoutPanel.add(nextPanel,"right");
                        layout.show(cardLayoutPanel, "right");
                    }else{
                        DeathPanel death = new DeathPanel(); 
                        cardLayoutPanel.add(death,"loss");
                        layout.show(cardLayoutPanel, "loss");
                    }
                }
            }catch(ArrayIndexOutOfBoundsException e){
                GraduationPanel win = new GraduationPanel(); 
                cardLayoutPanel.add(win,"winPanel");
                layout.show(cardLayoutPanel, "winPanel");
            }
        }
    }
}
