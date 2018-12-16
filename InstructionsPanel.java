import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Instructions panel for The Wellesley Trail
 *
 * @author  gbronzi
 * @author  nbryant2
 * @author  zthabet
 * @version 12.17.18
 */
public class InstructionsPanel extends JPanel 
{
    private JPanel content;
    
    /**
     * Constructs a JPanel with two labels.
     * Provides the instructions of the game 
     */
    public InstructionsPanel(){
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());
        
        JPanel foreground = new JPanel();
        foreground.setLayout(new BorderLayout());
        
        JButton next = new JButton("Choose your character -->");
        next.addActionListener(new ButtonListener());


        JTextArea top = 
                    new JTextArea("          Welcome to the Wellesley Trail!");
                    
        JTextArea left = 
            new JTextArea("Goals:\nMake it to graduation without:\n "+
                          "  Being attacked by geese" +
                          "\n   Getting lost in the tunnels\n " +
                          "  Dying of sleep deprivation\n   Or any of " + 
                          "the other perils of Wellesley College");
                          
        JTextArea right = new JTextArea("Instructions:          \n" + 
                "Most importantly, you must keep all three of your stats" + 
                "\nabove zero. These stats are sleep, smart, and social " +
                "\npoints. The decisions you make in this game will impact" +
                "\nthose stats. If any drop below zero, you lose!" + 
                 "\nAdditionally, some decisions will lead you to mini games."
                 + "\nIf you lose any of those, you lose it all!");

        //importing background image
         try {
            //scaling all input files to be the same size
            ImageIcon image = 
                   new ImageIcon(ImageIO.read(new File("images/OregonTrail.jpg")));
                  
            Image pic = image.getImage(); // transform it 
            
            // scale it the smooth way
            Image newimg =
                pic.getScaledInstance(1200, 800,  java.awt.Image.SCALE_SMOOTH);       
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image), BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //setting the font sizes        
        Font font = new Font("Verdana", Font.BOLD, 20);
        Font fontBig = new Font("Verdana", Font.BOLD, 48);
        top.setFont(fontBig);
        top.setForeground(Color.BLUE);
        left.setFont(font);
        right.setFont(font);
        next.setFont(font);
        
        //makes all the boxes transparent
        top.setBackground(new Color(0,0,0,0));
        top.setPreferredSize(new Dimension(1200,250));
        left.setBackground(new Color(0,0,0,0));
        right.setBackground(new Color(0,0,0,0));       
        
        //holds a background images and a panel on top  
        JLayeredPane content = new JLayeredPane();  
        
        //adding componants to foreground
        setLayout(new BorderLayout());
        foreground.add(top, BorderLayout.NORTH);
        foreground.add(left, BorderLayout.LINE_START);
        foreground.add(right, BorderLayout.LINE_END);  
        
        //add both back and foreground to the container
        content.setBounds(0, 0, 1200, 800); //same as frame
        //((where the panel starts),(the panel size))
        foreground.setBounds(0, 00, 1200, 800);
        background.setOpaque(true);
        background.setBounds(0, 00, 1200, 800); 
        foreground.setOpaque(false);
        //transparent
        foreground.setBackground(new Color(0,0,0,0)); 
        //sets to the background
        content.add(background, new Integer(0), 0); 
        //sets to the foregound 
        content.add(foreground, new Integer(1), 0);
        
        add(content, BorderLayout.CENTER);
        add(next,BorderLayout.SOUTH);
    }

    /**
     * Sets the listener for the action which will 
     * occur when the user clicks the button
     */
    private class ButtonListener implements ActionListener
    {
        /**
         * When the button is selected, create a character 
         * panel and go to it
         */
        public void actionPerformed (ActionEvent event){
            JButton button = (JButton)event.getSource();
            JPanel buttonPanel = (JPanel)button.getParent();
            JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
            //make new situation panel from source
            CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
            CharSelectionPanel nextPanel = new CharSelectionPanel(); 
            cardLayoutPanel.add(nextPanel,"charSelec");
            layout.show(cardLayoutPanel, "charSelec");
        }
    }
}
