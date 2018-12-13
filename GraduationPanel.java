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
        setBackground (Color.green);
       JTextArea l1 = new JTextArea ("Congratulations, you made it to graduation! You succesfully " +
                                     "passed all your classes while balacing friends, orgs, and " +
                                     "all the terrors Wellesley has to offer. Here is your diploma " +
                                     "and good luck dealing with the real world!");
                                    
       l1.setBackground(Color.green); //so that the JTextArea matches the panel background
       add (l1);
       
       try{
            BufferedImage diploma = ImageIO.read(new File("diploma.png"));
            JLabel picLabel = new JLabel(new ImageIcon(diploma));
            add(picLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
       
    }

}
