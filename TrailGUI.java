
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
/**
 * Write a description of class WellesleyTrailGUI here.'
 * 
 * @author (zthabet,nbryant2,gbronzi)
 * @version (12.1.18)
 */
public class TrailGUI 
{
    Person p;
    JPanel trailGame; //a panel that uses CardLayout
    Vector<Vector<Situation>> tree;
    int counter; 
    
    public void addComponents(Container pane){
        TrailsBinaryTree theTree = new TrailsBinaryTree("Situations.txt");
        tree = theTree.getYears();
        p=new Person(0);//initializes it
        JPanel start = new StartPanel();

        JPanel card2 = new CharSelection();
        
        JPanel card3 = new SituationPanel(p, theTree, 0,0);

        //Create the panel that contains the "cards".
        trailGame = new JPanel(new CardLayout());
        trailGame.add(card2, "StartPanel");
        trailGame.setPreferredSize(new Dimension(600,400));
        pane.add(trailGame, BorderLayout.CENTER);
        
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
