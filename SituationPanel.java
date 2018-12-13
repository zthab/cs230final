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
    private JTextArea questionText;
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
        vecIndex=v;
        sitIndex = s;

        sit = treeYears.get(vecIndex).get(sitIndex);
        question = sit.getQuestion();
        option1=sit.getOption1();
        option2 = sit .getOption2();

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
                System.out.println(player);
                player.addAllScores(option1.getPoints());
                if (player.isAboveZero()){
                    if (!((2*(sitIndex+1)-1)>treeYears.get(vecIndex).size())){
                        JButton button = (JButton)event.getSource();
                        JPanel buttonPanel = (JPanel)button.getParent();
                        JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
                        //make new situation panel from source
                        CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
                        SituationPanel nextPanel = new SituationPanel(player, tree, vecIndex,2*(sitIndex+1)-1); 
                        cardLayoutPanel.add(nextPanel,"3");
                        layout.show(cardLayoutPanel, "3");
                    }else{
                        //show first one of next vector, increase vec index 
                    }

                }else{
                    //go to you died panel
                }

            }else if (event.getSource().equals(option2Button)){
                player.addAllScores(option2.getPoints());
                if (player.isAboveZero()){
                    if (!((2*(sitIndex+1))>treeYears.get(vecIndex).size())){
                        System.out.println("here");
                        JButton button = (JButton)event.getSource();
                        JPanel buttonPanel = (JPanel)button.getParent();
                        JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
                        //make new situation panel from source
                        CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
                        SituationPanel nextPanel = new SituationPanel(player, tree, vecIndex,2*(sitIndex+1)); 
                        cardLayoutPanel.add(nextPanel,"3");
                        layout.show(cardLayoutPanel, "3");
                    }else{
                        //show first one of next vector, increase vec index 
                    }

                }else{
                    //go to you died panel
                }
            }

        }
    }//can use jlayered panel for selection

}
