
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Creates a start panel for The Wellesley Trail
 *
 * @author zthabet
 * @author gbronzi
 * @author nbryant2
 * @version 12.17.18
 */
public class StartPanel extends JPanel
{
    /**
     * Constructs up a start panel as a JPanel
     */
    public StartPanel()
    {
        setBackground (Color.green);
        setLayout(new BorderLayout());
        JLabel l1 = new JLabel ("Welcome to Wellesley Trails!");
        l1.setHorizontalAlignment(SwingConstants.CENTER);

        JButton next = new JButton("Next");
        next.addActionListener(new ButtonListener());

        l1.setBackground(Color.green); //so that the JTextArea matches the panel background
        add(next,BorderLayout.SOUTH);
        add (l1, BorderLayout.CENTER);

    }
    /**
     * 
     */
    private class ButtonListener implements ActionListener
    {
        //-----------------------------------------------------------------
        // Sets the text of the label depending on which radio
        // button was pressed.
        //-----------------------------------------------------------------
        public void actionPerformed (ActionEvent event)
        {
            JButton button = (JButton)event.getSource();
            JPanel buttonPanel = (JPanel)button.getParent();
            JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
            //make new situation panel from source
            CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
            InstructionsPanel nextPanel = new InstructionsPanel(); 
            cardLayoutPanel.add(nextPanel,"Instructions");
            System.out.println("here");
            layout.show(cardLayoutPanel, "Instructions");
        }
    }

}
