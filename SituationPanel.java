import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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

    private JLayeredPane content;//holds the background and foreground
    private JPanel topRow, midRow; //holds the buttons

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
        tree=t; 
        setPreferredSize(new Dimension(1200,800));
        topRow = new JPanel ();
        topRow.setBackground(new Color(0,39,118));
        topRow.setOpaque(false);
        midRow = new JPanel();
        midRow.setBackground(new Color(0,39,118));
        midRow.setOpaque(false);

        JPanel a = new JPanel();
        a.setBackground(new Color(0,39,118));
        a.setOpaque(false);
        //a.add(topRow);
        //a.add(midRow);
        a.setLayout(new GridLayout(2,1,30,30));
        //r236,g222,b187

        Font font = new Font("Verdana", Font.BOLD, 28);

        //accesses all the relevant Situation object components 
        sit = tree.getCurrent();
        question = sit.getQuestion();
        option1=sit.getOptionLeft();
        option2 = sit.getOptionRight();

        //displays the Situation's question
        questionText = new JTextArea (question);
        questionText.setFont(font);
        questionText.setLineWrap(true);
        questionText.setBackground(new Color(0,39,118));
        questionText.setForeground(new Color(236,222,187));

        //displays the players current point totals
        playerStatus = new JTextArea(player.toString());
        playerStatus.setBackground(new Color(236,222,187));
        playerStatus.setFont(font);

        //displays the Situation's two Options' decisions as buttons
        option1Button = new JButton(option1.getDecision());
        option1Button.setFont(font);
        option2Button = new JButton(option2.getDecision());
        option2Button.setFont(font);

        a.add(option1Button);
        a.add(option2Button);

        option1Button.addActionListener(new ButtonListener());
        option2Button.addActionListener(new ButtonListener());

        setBounds(0, 00, 1200, 800); 
        setBackground(new Color(0,39,118));

        setLayout(new BorderLayout());
        add(questionText, BorderLayout.NORTH);
        add(a, BorderLayout.CENTER);
        add(playerStatus,BorderLayout.SOUTH);
    }

    /**
     * Represents the listeners for all buttons 
     */
    private class ButtonListener implements ActionListener
    {
        /**
         * Sets the card to the correct panel based on which button is pressed
         * and where the current situation in the TrailsBinaryTree is
         * 
         * @param event action event that triggers the action 
         */
        public void actionPerformed (ActionEvent event)
        {
            JButton button = (JButton)event.getSource();//option
            JPanel buttonPanel = (JPanel)button.getParent(); //grid layout panel
            JPanel buttonPanContainer = (JPanel) buttonPanel.getParent(); //border layout panel
            //JPanel borderPanel = (JPanel) buttonPanContainer.getParent(); //
            //JPanel a = (JPanel) borderPanel.getParent();
            //JPanel b = (JPanel) a.getParent();
            
            JPanel cardLayoutPanel = (JPanel)buttonPanContainer.getParent();
            
            CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
            try{
                //if the source is the option 1 button
                if (event.getSource().equals(option1Button)){
                    if(option1.getDecision().toLowerCase().contains("run")){
                        RunningPanel run = new RunningPanel(player, tree, true); 
                        cardLayoutPanel.add(run,"runningGame");
                        layout.show(cardLayoutPanel, "runningGame");
                    }else if(option1.getDecision().toLowerCase().contains("vocab")){

                    }else if(option1.getDecision().toLowerCase().contains("equations")){
                        
                    }  else{
                        //add the associated points to the player's points
                        player.addAllScores(option1.getPoints());
                        //if the players points are still above zero, continue to
                        //the left child SituationPanel
                        if (player.isAboveZero()){
                            //incremements the tree so that the current Situation
                            //is the left child of the current Situation
                            tree.nextLeft();
                            //shows a SituationPanel of the new current Situation
                            SituationPanel nextPanel = new SituationPanel(player, tree); 
                            cardLayoutPanel.add(nextPanel,"left");
                            layout.show(cardLayoutPanel, "left");
                            //if the player's points are not above zero after pressing
                            //the button, shows the death panel
                        
                        }else{
                            DeathPanel death = new DeathPanel(); 
                            cardLayoutPanel.add(death,"loss");
                            layout.show(cardLayoutPanel, "loss");
                        }
                    }

                    //if the source is the option 1 button
                }else{
                    if(option2.getDecision().toLowerCase().contains("run")){
                        RunningPanel run = new RunningPanel(player, tree, false); 
                        cardLayoutPanel.add(run,"runningGame");
                        layout.show(cardLayoutPanel, "runningGame");
                    }//else if (is mem){}
                    else{
                        //add the associated points to the player's points
                        player.addAllScores(option2.getPoints());
                        //if the players points are still above zero, continue to
                        //the right child SituationPanel
                        if (player.isAboveZero()){
                            //incremements the tree so that the current Situation
                            //is the right child of the current Situation
                            tree.nextRight();
                            //shows a SituationPanel of the new current Situation
                            SituationPanel nextPanel = new SituationPanel(player, tree); 
                            cardLayoutPanel.add(nextPanel,"right");
                            layout.show(cardLayoutPanel, "right");
                            //if the player's points are not above zero after pressing
                            //the button, shows the death panel
                        }else{
                            DeathPanel death = new DeathPanel(); 
                            cardLayoutPanel.add(death,"loss");
                            layout.show(cardLayoutPanel, "loss");
                        }
                    }
                }
                //if there is no child Situation, proceeds to the graduation panel
            }catch(ArrayIndexOutOfBoundsException e){
                GraduationPanel win = new GraduationPanel(); 
                cardLayoutPanel.add(win,"winPanel");
                layout.show(cardLayoutPanel, "winPanel");
            }

        }
    }
}
