
/**
 * Write a description of class Countdown here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;

public class Countdown
{
        
    public static void main(String[] args)
    {
        JLabel timerField = new JLabel();
        
        CountdownPanel cTimer = new CountdownPanel(timerField);
        
        JFrame frame = new JFrame("Countdown Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(timerField);
        //frame.pack();
        frame.setVisible(true);
   }
}
