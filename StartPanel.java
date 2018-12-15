import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    private JLayeredPane content;//holds two panels one of which is the background image
    private ImageIcon image;
    private JPanel background, foreground;

    /**
     * Constructs up a JPanel with two labels.
     */
    public StartPanel() {
        content = new JLayeredPane();//holds a background images and a panel on top
        background = new JPanel();//holds the scenario image
        foreground = foreground();

        //add image to the background
        try {
            //scaling all input files to be the same size
            image = new ImageIcon(ImageIO.read(new File("StartImage.jpg")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(610, 455,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
     
        content.setBounds(0, 0, 610, 455); //same as frame
        foreground.setBounds(260, 370, 100, 40);//((where the panel starts),(the panel size))
        background.setOpaque(true);
        background.setBounds(0, 0, 630, 455); 
        foreground.setOpaque(true);
        content.add(background, new Integer(0), 0); //sets to the background
        content.add(foreground, new Integer(1), 0);//sets to the foregound

        add(content, BorderLayout.CENTER);
    }
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

    private JPanel foreground(){
        foreground = new JPanel();//holds the scenario image

        JButton next = new JButton("Start Game");
        next.addActionListener(new ButtonListener());

        foreground.add(next,BorderLayout.SOUTH);
        
        return foreground;
    }
}
