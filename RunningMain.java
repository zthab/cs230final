
/**
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.10.18)
 */

import javax.swing.* ;
import java.awt.*;

public class RunningMain extends javax.swing.JFrame {
    //private JSplitPane splitPanel;  // split the window in top and bottom
    private JPanel countPanel;
    private JPanel runPanel;
    
    public RunningMain(){        
        JLabel timerField = new JLabel();
        
        add (new RunningPanel(timerField));
        
        setPreferredSize(new Dimension(600, 200));
        pack();       
    }
       
    public static void main (String[] args){
        new RunningMain().setVisible(true);
        
    }
}
