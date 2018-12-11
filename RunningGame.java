
/**
 * 
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.1.18)
 */

import javax.swing.*;

public class  RunningGame
{
   //-----------------------------------------------------------------
   //  Creates and displays the main program frame.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame (); // The title of the window
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // by clicking the red "close window" button
      
      RunningPanel panel = new RunningPanel(); // The Panel file only thing that needs to change
      frame.getContentPane().add(panel);

      frame.pack();
      frame.setVisible(true);
   }
}
