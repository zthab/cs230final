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
            Image newimg = pic.getScaledInstance(610, 455,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //setPreferredSize(new Dimension(610, 455));
        setLayout(new BorderLayout());
        content.setBounds(0, 0, 610, 455); //same as frame
        deck.setBounds(150, 150, 200, 100);//((where the panel starts),(the panel size))
        background.setOpaque(true);
        background.setBounds(0, 0, 610, 455); 
        deck.setOpaque(true);
        deck.setBackground(new Color(0,0,0,0));
        add(background, new Integer(0), 0); //sets to the background
        add(deck, new Integer(1), 0);//sets to the foregound    

       // add(content);
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
                JPanel buttonPanel = (JPanel)button.getParent();
                System.out.println(buttonPanel);
                JPanel charPanel = (JPanel)buttonPanel.getParent();
                System.out.println(charPanel);
                JLayeredPane cardLayoutPanel = (JLayeredPane)charPanel.getParent();
                System.out.println(cardLayoutPanel);
                JPanel test = (JPanel)cardLayoutPanel.getParent();
                System.out.println(test);
                JPanel aa = (JPanel)test.getParent();
                System.out.println(aa);
                JPanel bb = (JPanel)aa.getParent();
                System.out.println(bb);
                JLayeredPane cc = (JLayeredPane)bb.getParent();
                System.out.println(cc);
                JRootPane dd = (JRootPane)cc.getParent();
                System.out.println(dd);
                JFrame ee = (JFrame) dd.getParent();
                System.out.println(ee);
                ee.dispose();
                //JPanel ree = (JPanel) test.getParent();
                
                //dispose();
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
