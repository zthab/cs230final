import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
/**
 * Write a description of class GraduationPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GraduationPanel extends JPanel 
{
    
    /**
     * Constructor for objects of class GraduationPanel
     */
    public GraduationPanel()
    {
       JPanel background = new JPanel();
       background.setLayout(new BorderLayout());
       Font font = new Font("Verdana", Font.BOLD, 20);
       
       JPanel deck = new JPanel(new CardLayout());//container for the panels, switches between them like playingcards
       CardLayout cl = (CardLayout)(deck.getLayout());//manages the deck
        
       background.setLayout(new BorderLayout());
       background.setOpaque(true);

       //adding sev green photo to background
       try {
            //scaling all input files to be the same size
            ImageIcon image = new ImageIcon(ImageIO.read(new File("sevGreen.jpg")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(1200, 770,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image), BorderLayout.CENTER);
       }catch (IOException e) {
           e.printStackTrace();
       }
       deck.add(background, "screen");
       add(deck, BorderLayout.CENTER);
    }
}
