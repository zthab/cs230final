
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

/**
 * TrailGUI controls the game. It begins at the startPanel and moves forward
 * through the other panels and games
 * 
 * @author zthabet
 * @author nbryant2
 * @author gbronzi
 * @version (12.1.18)
 */
public class TrailGUI 
{
    Person p;
    JPanel trailGame; //a panel that uses CardLayout
    Vector<Vector<Situation>> tree;
    int counter; 
    
    /**
     * Creates a new startPanel and begins the game 
     */
    public void addComponents(Container pane){
        JPanel start = new StartPanel();

        //Create the panel that contains the "cards".
        trailGame = new JPanel(new CardLayout());
        trailGame.add(start, "StartPanel");
        trailGame.setPreferredSize(new Dimension(1200,800));

        pane.add(trailGame, BorderLayout.CENTER);
    }

    public static void main (String[] args) 
    {
        
        JFrame frame = new JFrame("Wellesley Trails");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TrailGUI game = new TrailGUI();
        game.addComponents(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    } 
}
