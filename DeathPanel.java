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
            ImageIcon image = 
                    new ImageIcon(ImageIO.read(new File("deathScreen.jpg")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(1200, 800, 
                                                  java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(1200, 800));
        setLayout(new BorderLayout());
        content.setBounds(0, 0, 1200, 800); //same as frame
        //((where the panel starts),(the panel size))
        deck.setBounds(500, 300, 200, 100);
        background.setOpaque(true);
        background.setBounds(0, 0, 1200, 800); 
        deck.setOpaque(true);
        deck.setBackground(new Color(0,0,0,0));
        content.add(background, new Integer(0), 0); //sets to the background
        content.add(deck, new Integer(1),0);//sets to the foregound    

        add(content);
    }

    /**
     * Impliments the action listeners for the two button option on this panel
     */
    protected class ButtonListener implements ActionListener {
        /**
         * Depending on which button is selected, users will either be taken to
         * the home screen or the window will close
         */
        public void actionPerformed(ActionEvent event){
            //restarts the game
            if (event.getSource() == cont){
                JFrame frame = new JFrame("Wellesley Trails");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                TrailGUI newGame = new TrailGUI();
                newGame.addComponents(frame.getContentPane());
                frame.pack();
                frame.setVisible(true);
            //quits the game
            }else{
                JPanel startPanel = (JPanel)death.getParent().getParent();
                JLayeredPane layeredPane = 
                  (JLayeredPane)startPanel.getParent().getParent().getParent().getParent();
                JRootPane rootPane = (JRootPane)layeredPane.getParent();
                JFrame frame = (JFrame) rootPane.getParent();
                frame.dispose();
            }
        }   
    }
    
    /**
     * Creates the game over panel which holds two buttons
     * 
     * @return JPanel the game over panels
     */
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
