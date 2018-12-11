
/**
 * This is working! Partly! The timer and the counter are in the same frame. 
 * Where I am stuck is in how to get the info from the counter (ie when it is 0)
 * and commnuicate it to the Main frame so it will stop displaying the the counter
 * and display the results. Gonna need a brain storm
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.10.18)
 */

import javax.swing.* ;
import java.awt.*;

public class RunningMain extends javax.swing.JFrame {
    private JSplitPane splitPanel;  // split the window in top and bottom
    private JPanel countPanel;
    private JPanel runPanel;
    
    public RunningMain(){
        splitPanel = new JSplitPane();
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        JLabel timerField = new JLabel();
        
        
        JPanel runPanel = new RunningPanel();
        JPanel countPanel = new CountdownPanel(timerField);
        countPanel.add(timerField);
        
        //getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPanel);
       
        //Set the panel details
        splitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPanel.setDividerLocation(160); 
        splitPanel.setTopComponent(countPanel);
        splitPanel.setBottomComponent(runPanel);
        
        //int time = countPanel.getCounter(); 
        //not sure how to get the state of the counter from CountdownPanel

        setPreferredSize(new Dimension(400, 400));
        pack();       
    }
       
    public static void main (String[] args){
        new RunningMain().setVisible(true);
        
    }
}
