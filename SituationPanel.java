import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
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
    private JTextArea questionText, playerStatus;
    private JButton option1Button, option2Button;
    private Person player;
    private TrailsBinaryTree tree;
    private Vector<Vector<Situation>> treeYears;
    private int vecIndex, sitIndex;

    /**
     * Constructor for objects of class SituationGUI
     */
    public SituationPanel(Person p,TrailsBinaryTree t, int v, int s)
    {
        player = p;

        setBackground (Color.green);
        tree=t;
        treeYears = tree.getYears(); 
        //System.out.println(tree);
        vecIndex=v;
        sitIndex = s;

        sit = treeYears.get(vecIndex).get(sitIndex);
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
            if (event.getSource().equals(option1Button)){
                
                player.addAllScores(option1.getPoints());
                System.out.println(player);
                if (player.isAboveZero()){
                    if (!((2*(sitIndex+1)-1)>=treeYears.get(vecIndex).size())){
                        SituationPanel nextPanel = new SituationPanel(player, tree, vecIndex,2*(sitIndex+1)-1); 
                        cardLayoutPanel.add(nextPanel,"left");
                        layout.show(cardLayoutPanel, "left");
                    }else if (vecIndex+1<treeYears.size()){ 
                        SituationPanel nextPanel = new SituationPanel(player, tree, vecIndex+1,0); 
                        cardLayoutPanel.add(nextPanel,"newVec");
                        layout.show(cardLayoutPanel, "newVec");
                    }else{
                        GraduationPanel win = new GraduationPanel(); 
                        cardLayoutPanel.add(win,"winPanel");
                        layout.show(cardLayoutPanel, "winPanel");
                    }
                }else{
                    DeathPanel death = new DeathPanel(); 
                    cardLayoutPanel.add(death,"loss");
                    layout.show(cardLayoutPanel, "loss");
                }
            }else if (event.getSource().equals(option2Button)){
                player.addAllScores(option2.getPoints());
                System.out.println(player);
                if (player.isAboveZero()){
                    if (!((2*(sitIndex+1))>=treeYears.get(vecIndex).size())){
                        SituationPanel nextPanel = new SituationPanel(player, tree, vecIndex,2*(sitIndex+1)); 
                        cardLayoutPanel.add(nextPanel,"right");
                        layout.show(cardLayoutPanel, "right");
                    }else if (vecIndex+1<treeYears.size()){ 
                        SituationPanel nextPanel = new SituationPanel(player, tree, vecIndex+1,0); 
                        cardLayoutPanel.add(nextPanel,"newVec");
                        layout.show(cardLayoutPanel, "newVec");
                    }else{
                        GraduationPanel win = new GraduationPanel(); 
                        cardLayoutPanel.add(win,"winPanel");
                        layout.show(cardLayoutPanel, "winPanel");
                    }
                }else{
                    DeathPanel death = new DeathPanel(); 
                    cardLayoutPanel.add(death,"loss");
                    layout.show(cardLayoutPanel, "loss");
                }
            }
        }
    }
}
