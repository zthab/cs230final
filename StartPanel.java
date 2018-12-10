
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class StartPanel here.
 *
 * @author Zahra Thabet
 * @version 12.7.18
 */
public class StartPanel extends JPanel
{
    JButton nextB;
    
        /**
     * Constructs up a JPanel with two labels.
     */
    public StartPanel()
    {
       setBackground (Color.green);
       JTextArea l1 = new JTextArea ("Welcome to Wellesley Trails!");
       nextB = new JButton ("Next");
                                    
       l1.setBackground(Color.green); //so that the JTextArea matches the panel background
       add (l1);
       add(nextB);
       
       nextB.addActionListener(new ButtonListener());
       
    }
    private class ButtonListener implements ActionListener{
        /**
         * Creates a School object when a button is clicked. Only creates
         * it if all the required comboListeners are selected, otherwise 
         * an error will print. Text displaying the input characteristics 
         * will appear in the lowest panel. 
         * 
         * @param Action the event of the button being clicked
         */
        public void actionPerformed(ActionEvent event){
            //need a new frame here 
            
        }
    }

}
