import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Creates a panel for the end of the game. Does not lead to any other panel.
 * 
 * @author gbronzi
 * @author nbryant2
 * @author zthabet
 * @version 12.17.18
 */
public class GraduationPanel extends JPanel 
{
    /**
     * Constructor for objects of class GraduationPanel. Creates a graduation 
     * panel
     */
    public GraduationPanel()
    {
       //creates background panel
       JPanel background = new JPanel();
        
       background.setLayout(new BorderLayout());
       background.setOpaque(true);

       //adding sev green photo to background
       try {
            //scaling all input files to be the same size
            ImageIcon image = 
                    new ImageIcon(ImageIO.read(new File("sevGreen.jpg")));
                    
            Image pic = image.getImage(); // transform it 

            // scale it the smooth way  
            Image newimg = 
                   pic.getScaledInstance(1200, 770,java.awt.Image.SCALE_SMOOTH); 

            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image), BorderLayout.CENTER);
       }catch (IOException e) {
           e.printStackTrace();
       }
       add(background);
    }
}
