
/**
 * Write a description of class PushCounterPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class  RunningPanel extends JPanel
{
   private int count;
   private JButton push;
   private JLabel label;

   //-----------------------------------------------------------------
   //  Constructor: Sets up the GUI.
   //-----------------------------------------------------------------
   public RunningPanel ()
   {
      count = 0;

      push = new JButton ("Push Me!");
      push.addActionListener (new ButtonListener() );

      label = new JLabel ("Pushes: " + count);

      add (push); // it meand this.add(push) b/c that's the default panel
      add (label);

      setBackground (Color.cyan);
      setPreferredSize (new Dimension(300, 40));
   }

   //*****************************************************************
   //  Represents a listener for button push (action) events.
   //*****************************************************************
   private class ButtonListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Updates the counter and label when the button is pushed.
      //--------------------------------------------------------------
      public void actionPerformed (ActionEvent event)
      {
         count++; // When ANY button is clicked! (If there were more...)
         label.setText("Pushes: " + count);
      }
   }
}
