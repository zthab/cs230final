
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The character selection screen of The Wellesley Trail. Allows for a user to
 * to select an archetype to play as. Modifies a Person object with the 
 * archetype's sleep, smart, and social points.
 *
 * @author zthabet
 * @author gbronzi
 * @author nbryant2
 * 
 * @version 12.17.18
 */
public class CharSelection extends JPanel
{
    //creates panels to distinguish between the different parts of the 
    //character selection panel
    private JPanel instructRow, topRow, midRow, botRow, foreground, background;
    private JLabel instruct1, instruct2, instruct3;
    private JTextArea persStats; //displays the selected archetype's points
    private JRadioButton athletic, hermit, horse, offCampus, society,wendy;
    private JButton next; 
    private Person player; //Person object to be modified
    private Boolean hasSelectedBefore; //tracks if next button should appear
    private JLayeredPane content;//holds the background image and foreground displays

    public CharSelection()
    {
        background = new JPanel();
        background.setLayout(new BorderLayout());

        foreground = new JPanel();
        foreground.setLayout(new BorderLayout());
        
        Font font = new Font("Verdana", Font.BOLD, 20);

        hasSelectedBefore=false;

        instructRow = new JPanel();
        instructRow.setBackground(new Color(0,0,0,0));
        topRow = new JPanel ();
        topRow.setBackground(new Color(0,0,0,0));
        topRow.setOpaque(false);
        midRow = new JPanel();
        midRow.setBackground(new Color(0,0,0,0));
        midRow.setOpaque(false);
        botRow = new JPanel();
        botRow.setBackground(new Color(0,0,0,0));
        botRow.setOpaque(false);
        
        JPanel a = new JPanel();
        a.setBackground(new Color(0,0,0,0));
        a.setOpaque(false);
        a.add(topRow);
        a.add(midRow);

        //adds panels to the character selection panel in the foreground of image
        foreground.add(instructRow, BorderLayout.NORTH);
        foreground.add(a, BorderLayout.CENTER);
        foreground.add(botRow,BorderLayout.SOUTH);        

        //text which displays at top of gui 
        instructRow.setLayout(new BorderLayout());
        instruct1 = new JLabel("Select an archetype. Their sleep, smart and"
            + " social points will display below.");
        instruct1.setFont(font);    
        instruct2 = new JLabel("Each archetype comes with specialized points based on personality.");
        instruct2.setFont(font);    
        instruct3= new JLabel("When satsified with your choice, press the next"+
            " button.");
        instruct3.setFont(font);    
        instructRow.add(instruct1,BorderLayout.NORTH);
        instructRow.add(instruct2,BorderLayout.CENTER);
        instructRow.add(instruct3,BorderLayout.SOUTH);
        instructRow.setPreferredSize(new Dimension(1100,125));

        //creates archetype radio buttons
        athletic = new JRadioButton ("Athletic Alex");
        athletic.setBackground (new Color(0,0,0,0));
        athletic.setFont(font);
        athletic.setOpaque(false);

        hermit = new JRadioButton ("Hermit Harper");
        hermit.setBackground (new Color(0,0,0,0));
        hermit.setFont(font);
        hermit.setOpaque(false);

        horse = new JRadioButton ("Horse Girl Grace");
        horse.setBackground (new Color(0,0,0,0));
        horse.setFont(font);
        horse.setOpaque(false);

        offCampus = new JRadioButton ("Off Campus Ollie");
        offCampus.setBackground (new Color(0,0,0,0));
        offCampus.setFont(font);
        offCampus.setOpaque(false);

        society = new JRadioButton ("Society Skylar");
        society.setBackground (new Color(0,0,0,0));
        society.setFont(font);
        society.setOpaque(false);

        wendy = new JRadioButton ("Wendy Wellesley");
        wendy.setBackground (new Color(0,0,0,0));
        wendy.setFont(font);
        wendy.setOpaque(false);

        persStats = new JTextArea("");
        persStats.setBackground(new Color(0,0,0,0));
        persStats.setFont(font);
        persStats.setOpaque(false);

        ButtonGroup group = new ButtonGroup();
        group.add (athletic);
        group.add (hermit);
        group.add (horse);
        group.add(offCampus);
        group.add(society);
        group.add(wendy);

        ButtonListener listener = new ButtonListener();
        athletic.addActionListener (listener);
        hermit.addActionListener (listener);
        horse.addActionListener (listener);
        offCampus.addActionListener (listener);
        society.addActionListener (listener);
        wendy.addActionListener (listener);

        //adds half of the archetype images and radio buttons to the top panel
        try{
            ImageIcon image = new ImageIcon(ImageIO.read(new File("AthleticAlex.png")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(100, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back
            
            topRow.add(new JLabel(image));
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        topRow.add (athletic);

        try{
            ImageIcon image = new ImageIcon(ImageIO.read(new File( "HermitHarper.png")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(100, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back
            
            topRow.add(new JLabel(image));
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        topRow.add (hermit);

        try{
            ImageIcon image = new ImageIcon(ImageIO.read(new File( "HorseGirlGrace.png")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(100, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back
            
            topRow.add(new JLabel(image));
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        topRow.add (horse);

        //adds the second half the arhcetypes' images and radio buttons to the 
        //midRow
        try{
            ImageIcon image = new ImageIcon(ImageIO.read(new File("OffCampusOllie.png")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(100, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back
            
            midRow.add(new JLabel(image));
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        midRow.add (offCampus);

        try{
            ImageIcon image = new ImageIcon(ImageIO.read(new File("SocietySkylar.png")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(100, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back
            
            midRow.add(new JLabel(image));
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        midRow.add (society);

        
        try{
            ImageIcon image = new ImageIcon(ImageIO.read(new File("WendyWellesley.png")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(100, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back
            
            midRow.add(new JLabel(image));
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        midRow.add (wendy);

        //importing background image
        try {
            //scaling all input files to be the same size
            ImageIcon image = new ImageIcon(ImageIO.read(new File("CharScreen.jpg")));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(1200, 800,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image), BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //holds a background images and a panel on top 
        content = new JLayeredPane();

        //add both back and foreground to the container
        content.setBounds(0, 0, 1200, 800); //same as frame
        foreground.setBounds(0, 00, 1200, 800);//((where the panel starts),(the panel size))
        background.setOpaque(true);
        background.setBounds(0, 00, 1200, 800); 
        foreground.setOpaque(false);
        foreground.setBackground(new Color(0,0,0,0)); //transparent
        content.add(background, new Integer(0), 0); //sets to the background
        content.add(foreground, new Integer(1), 0);//sets to the foregound 

        setLayout(new BorderLayout());
        botRow.add (persStats);//adds the person's scores to the bottom row
        
        add(content, BorderLayout.CENTER);
        add(botRow,BorderLayout.SOUTH);

    }
    /**
     * Represents the listener for all buttons
     */
    private class ButtonListener implements ActionListener
    {
        /**
         * Sets the archetype of the Person depending on which radio button was
         * pressed.
         * 
         * @param event action event that triggers the action
         */
        public void actionPerformed (ActionEvent event)
        {
            //if the source of the action is an archetype's radio button
            if(!(event.getSource()==next)){
                //if this is the first radio button pressed, display the next 
                //button
                if (!hasSelectedBefore){
                    next = new JButton("next");
                    next.addActionListener(new ButtonListener());
                    next.setBackground(new Color(0,0,0,0));
                    botRow.setBackground(new Color(0,0,0,0));
                    botRow.add(next); //adds the next button to the bottom row
                }
                //the Person object gets the selected archetype's points
                JRadioButton source = (JRadioButton)event.getSource();
                String chosenName = source.getText();
                player = new Person(chosenName);
                persStats.setText(player.toString());

                //signals that the next button doesn't need to be xreated again
                hasSelectedBefore = true; 
                //if the action is caused by the next button, go to first situation
            }else{ 
                //feteches the CardLayout
                JButton button = (JButton)event.getSource();
                JPanel buttonPanel = (JPanel)button.getParent();
                JPanel charPanel = (JPanel)buttonPanel.getParent();
                JPanel cardLayoutPanel = (JPanel)charPanel.getParent();
                CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();

                //creates a new situation tree from a text file
                TrailsBinaryTree tree = new TrailsBinaryTree("Situations.txt");
                //creates a new SituationPanel from the player and the tree
                SituationPanel nextPanel = new SituationPanel(player, tree); 
                //adds a new card to the CardLayout layout
                cardLayoutPanel.add(nextPanel,"startSit");
                //shows this new card 
                layout.show(cardLayoutPanel, "startSit");
            }
        }
    }
}