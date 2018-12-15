
/**
 * GUI which launches the running game created in RunningPanel
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.15.18)
 */

import javax.swing.* ;
import java.awt.*;

public class RunningGame extends javax.swing.JFrame {
    public RunningGame(){ 
        add (new RunningPanel());
        
        setPreferredSize(new Dimension(610, 455));
        pack();       
    }
       
    public static void main (String[] args){
        new RunningGame().setVisible(true);//launches the game
        
    }
}
