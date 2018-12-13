import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Write a description of class DeathPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DeathPanel extends JPanel {
    private JPanel death, deck, background;
    private CardLayout cl;//container for the panels
    private JButton cont, quit;

    /**
     * Constructor for objects of class DeathPanel
     */
    public DeathPanel()
    {     
        deck = new JPanel(new CardLayout());//this lets me switch panels and it's dope
        cl = (CardLayout)(deck.getLayout());//manages the deck 

        deck.add(death(), "dying");            
        //^set up incase we want more panels.

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
        deck.setBounds(150, 150, 200, 50);//((where the panel starts),(the panel size))
        background.setOpaque(true);
        background.setBounds(0, 0, 610, 455); 
        deck.setOpaque(true);
        content.add(background, new Integer(0), 0); //sets to the background
        content.add(deck, new Integer(1), 0);//sets to the foregound    

        add(content);
    }

    protected class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if (event.getSource() == cont){
                //start over
            }else{
                //close the whole thing
            }
        }   
    }
    private JPanel death(){
        death = new JPanel();
        cont = new JButton("Try Again");
        quit = new JButton("Quit Game");
        
        death.add(cont);
        death.add(quit);
        
        return death;
    }
}
