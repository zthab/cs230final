
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
import java.util.Random;

public class  RunningPanel extends JPanel
{
   private int count, time;
   private JButton push;
   private JLabel label;
   private Timer timeClock;
   
   final int TOTAL_TIME = 20; //seconds to play the game
    int counter = TOTAL_TIME;
    javax.swing.Timer refreshTimer;
    //Creation of a JTextField
    JLabel countdownTimerField ;

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
      
      //setPreferredSize (new Dimension(300, 40));
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
         Random rand = new Random();
         int r = rand.nextInt(9);
         
         if (r==1)
            setBackground (Color.green);
            
         if (r==2)
            setBackground (Color.magenta);
            
         if (r==3)
            setBackground (Color.yellow);
            
         if (r==4)
            setBackground (Color.blue);   
            
         if (r==5)
            setBackground (Color.black);
            
         if (r==6)
            setBackground (Color.yellow);
            
         if (r==7)
            setBackground (Color.lightGray);
            
         if (r==8)
            setBackground (Color.blue);
            
         if (r==9)
            setBackground (Color.orange);
            
         if (r==0)
            setBackground (Color.cyan);
      }
   }
   
}

