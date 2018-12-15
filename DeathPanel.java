import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Creates a death panel for when a player dies in The Wellesley Trail. 
 * Provides the user with options of restarting or quitting the game.
 *
 * @author nbryant2
 * @author gbronzi
 * @author zthabet
 * @version 12.17.18
 */
public class DeathPanel extends JPanel {
    private JPanel death, deck, background;
    private JButton cont, quit;

    /**
     * Constructor for objects of class DeathPanel
     */
    public DeathPanel()
    {     
        deck = death();

        JLayeredPane content = new JLayeredPane();//can hold all the things
        background = new JPanel();//holds the scenario image
        try {
            //scaling all input files to be the same size
            ImageIcon image = new ImageIcon(ImageIO.read(new File("deathScreen.jpg")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(1200, 800,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(1200, 800));
        setLayout(new BorderLayout());
        content.setBounds(0, 0, 1200, 800); //same as frame
        deck.setBounds(150, 150, 200, 100);//((where the panel starts),(the panel size))
        background.setOpaque(true);
        background.setBounds(0, 0, 1200, 800); 
        deck.setOpaque(true);
        deck.setBackground(new Color(0,0,0,0));
        content.add(background, new Integer(0), 0); //sets to the background
        content.add(deck, new Integer(1),0);//sets to the foregound    
        //this.getParent();
        add(content);
    }

    protected class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if (event.getSource() == cont){
                JFrame frame = new JFrame("Wellesley Trails");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TrailGUI newGame = new TrailGUI();
                newGame.addComponents(frame.getContentPane());
                frame.pack();
                frame.setVisible(true);
            }else{
                JButton button = (JButton)event.getSource();
                JPanel deathPanel = (JPanel)button.getParent().getParent().getParent();
                JPanel startPanel = (JPanel)deathPanel.getParent().getParent();
                JLayeredPane layeredPane = (JLayeredPane)startPanel.getParent().getParent().getParent();
                JRootPane rootPane = (JRootPane)layeredPane.getParent();
                JFrame frame = (JFrame) rootPane.getParent();
                frame.dispose();
            }
        }   
    }
    private JPanel death(){
        death = new JPanel();
        cont = new JButton("Try Again");
        quit = new JButton("Quit Game");

        cont.addActionListener(new ButtonListener());
        quit.addActionListener(new ButtonListener());
        //death.prefferedSize(

        death.add(cont);
        death.add(quit);

        return death;
    }
}
