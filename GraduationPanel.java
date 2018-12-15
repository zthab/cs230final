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
       
       JTextArea message1 = new JTextArea ("Congratulations, you made it to graduation!");                   
       JTextArea message2 = new JTextArea("You succesfully passed all your classes while balacing friends");
       JTextArea message3 = new JTextArea ("orgs, and all the terrors Wellesley has to offer.");
       JTextArea message4 = new JTextArea("Here is your diploma and good luck dealing with the real world!");
                                    
       message1.setBackground(new Color(0,0,0,0));
       message2.setBackground(new Color(0,0,0,0));
       message3.setBackground(new Color(0,0,0,0));
       message4.setBackground(new Color(0,0,0,0));
       
       Font font = new Font("Verdana", Font.BOLD, 20);
       message1.setFont(font);
       message2.setFont(font);
       message3.setFont(font);
       message4.setFont(font);
       
       
       add(message1);
       add(message2);
       add(message3);
       add(message4);
       
       
       try {
            //scaling all input files to be the same size
            ImageIcon image = new ImageIcon(ImageIO.read(new File("sevGreen.jpg")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(1200, 800,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
       try{
            BufferedImage diploma = ImageIO.read(new File("diploma.png"));
            JLabel picLabel = new JLabel(new ImageIcon(diploma));
            add(picLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
       
    }
}
