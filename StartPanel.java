
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Creates a start panel for The Wellesley Trail
 *
 * @author  Nolen Belle Bryant
 * @version 12.17.18
 */

public class StartPanel extends JPanel
{
    /**
     * Constructs up a start panel as a JPanel
     */
    public StartPanel(){
        //container for the panels, switches between them like playingcards
        JPanel deck = new JPanel(new CardLayout());
        CardLayout cl = (CardLayout)(deck.getLayout());//manages the deck
        
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());
                
        Font font = new Font("Verdana", Font.BOLD, 20);
        JButton next = new JButton("Begin Your Wellesley Experience");
        next.addActionListener(new ButtonListener());
        next.setFont(font);
       
        //adding an exciting welcome screen to the background
        try {
            //scaling all input files to be the same size
            ImageIcon image = 
                new ImageIcon(ImageIO.read(new File("images/StartImage.jpg")));
                
            Image pic = image.getImage(); // transform it 
           
            // scale it the smooth way  
            Image newimg = 
                pic.getScaledInstance(1200, 760,  java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image), BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        background.add(next, BorderLayout.SOUTH);
        deck.add(background, "screen");
        
        add(deck, BorderLayout.CENTER);
    }
    
    /**
     * Sets the listener for the action which will occur when 
     * the user clicks the button
     */
    private class ButtonListener implements ActionListener{
        /**
         * When the button is selected, creates an instructions panel and go to
         * it
         * 
         * @param event action of button being selected
         */
        public void actionPerformed (ActionEvent event){
            JButton button = (JButton)event.getSource();
            JPanel buttonPanel = (JPanel)button.getParent();
            JPanel cardLayoutPanel = (JPanel)buttonPanel.getParent();
            //make new situation panel from source
            CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
            InstructionsPanel nextPanel = new InstructionsPanel(); 
            cardLayoutPanel.add(nextPanel,"Instructions");
            //System.out.println("here in start button"); testing code
            layout.show(cardLayoutPanel, "Instructions");
        }
    }
}