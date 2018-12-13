
/**
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.10.18)
 */

import javax.swing.* ;
import java.awt.*;

public class RunningGame extends javax.swing.JFrame {
    //private JSplitPane splitPanel;  // split the window in top and bottom
    private JPanel countPanel;
    private JPanel runPanel;
    
    public RunningGame(){        
        JLabel timerField = new JLabel();
        
        add (new RunningPanel(timerField));
        
        setPreferredSize(new Dimension(700, 250));
        pack();       
    }
       
    public static void main (String[] args){
        new RunningGame().setVisible(true);
        
    }
}
