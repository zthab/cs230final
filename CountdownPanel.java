
/**
 * Write a description of class Timer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;


class CountdownPanel extends JPanel implements ActionListener {
    final int TOTAL_TIME = 20; //seconds to play the game
    int counter = TOTAL_TIME;
    javax.swing.Timer refreshTimer;
    //Creation of a JTextField
    JLabel countdownTimerField ;
    
    public CountdownPanel(JLabel f){
        refreshTimer = new javax.swing.Timer(1000, this);
        countdownTimerField = f;
        refreshTimer.start();
        setPreferredSize (new Dimension(300, 40));
    }
    
    public void actionPerformed(ActionEvent e) {
        counter--;
        if (counter >= 0){
            //System.out.println(" ** " + counter);
            countdownTimerField.setText(" Time left: " + counter);
        }
        if (counter == 0){
            //System.out.println("Time's UP! ");
            countdownTimerField.setText(" Time's UP!");
            refreshTimer.stop();
        }
    }
}