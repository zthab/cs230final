
/**
 * Write a description of class Timer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;

class CountdownTimer implements ActionListener {
    final int TOTAL_TIME = 20;
    int counter = TOTAL_TIME;
    javax.swing.Timer refreshTimer;
    //Creation of a JTextField
    JTextField countdownTimerField ;
    
    public CountdownTimer(JTextField f){
        refreshTimer = new javax.swing.Timer(1000, this);
        countdownTimerField = f;
        start();
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
            stop();
        }
    }
    
    public void start(){
        refreshTimer.start();
    }
    
    public void stop(){
        refreshTimer.stop();
    }
    
    public void reset(){
        counter = TOTAL_TIME;
    }
    
    public static void main(String[] args)
    {
        JTextField timerField = new JTextField();
        
        CountdownTimer cTimer = new CountdownTimer(timerField);
        
        JFrame frame = new JFrame("Countdown Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(timerField);
        frame.pack();
        frame.setVisible(true);
    }
}