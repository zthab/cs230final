
/**
 * Write a description of class PushCounterPanel here.
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.10.18)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;
import java.awt.Color;

public class  RunningPanel extends JPanel
{
   private int count, time;
   private JButton push;
   private JLabel label;
   private Timer timeClock;

   public RunningPanel ()
   {
      count = 0; 
      
      // timeClock = new Timer(1000, new TimerListener () );
      // timeClock.setActionCommand("Start running!");
      // timeClock.setRepeats(true);
      // timeClock.start();

      push = new JButton ("Run!");
      push.addActionListener (new ButtonListener() );

      label = new JLabel ("Steps: " + count);

      add (push); // it meand this.add(push) b/c that's the default panel
      add (label);
      
      push.setVisible(true);

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
         label.setText("Steps: " + count);
         
         if (count%10==1)
            setBackground (Color.green);
            
         if (count%10==2)
            setBackground (Color.magenta);
            
         if (count%10==3)
            setBackground (Color.yellow);
            
         if (count%10==4)
            setBackground (Color.blue);   
            
         if (count%10==5)
            setBackground (Color.black);
            
         if (count%10==6)
            setBackground (Color.yellow);
            
         if (count%10==7)
            setBackground (Color.lightGray);
            
         if (count%10==8)
            setBackground (Color.blue);
            
         if (count%10==9)
            setBackground (Color.orange);
            
         if (count%10==0)
            setBackground (Color.cyan);
      }
   }
}

