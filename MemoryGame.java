
/**
 * Gui for the MemoryGame mini game.
 * 
 * @Known bugs-:
 * *The MemoryPanel does not resize as words are entered so after
 * 2 words you cannot see the chosen path display
 * *Right know there is no display occuring with expected instuctions and
 * also therefore no checking for if input was correct
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.1.18)
 */
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;

public class MemoryGame 
{
   private JLabel contentPanel;//holds all the stuff, only thing added to frame
   private JPanel bgPanel; //background image
   
   public static void main (String[] args){
      JFrame frame = new JFrame ("Tunnel Challenge");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      JLayeredPane content = new JLayeredPane(); //container for panels
      
      MemoryPanel panel = new MemoryPanel(); //game text panel
      JPanel bgPanel = new JPanel(); //background image
      
      //add image from file
      //eventually we will want to change the image based on the senario
      try {
          bgPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("Tunnel.jpg")))));
      } catch (IOException e) {
          e.printStackTrace();
      }
      
      //formating the display
      frame.setPreferredSize(new Dimension(610, 455));
      frame.setLayout(new BorderLayout());
      frame.add(content, BorderLayout.CENTER);
      content.setBounds(0, 0, 610, 455); //same as frame
      //panel needs to rezise as words are given or we just take out the display, also would work
      panel.setBounds(160, 100, 300, 75);//((where the panel starts),(the panel size))
      bgPanel.setOpaque(true);
      bgPanel.setBounds(0, 0, 600, 400); 
      panel.setOpaque(true);
      content.add(bgPanel, new Integer(0), 0); //sets to the background
      content.add(panel, new Integer(1), 0);//sets to the foregound
      
      
      frame.pack();
      frame.setVisible(true);
   }
}
