
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

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
    private JPanel instructRow, topRow, midRow, botRow;
    private JLabel instruct1, instruct2;
    private JTextArea persStats; //displays the selected archetype's points
    private JRadioButton athletic, hermit, horse, offCampus, society,wendy;
    private JButton next; 
    private Person player; //Person object to be modified
    private Boolean hasSelectedBefore; //tracks if next button should appear
    
    public CharSelection()
    {
        hasSelectedBefore=false;

        
        instructRow = new JPanel();
        instructRow.setBackground(Color.green);
        topRow = new JPanel ();
        topRow.setBackground(Color.green);
        midRow = new JPanel();
        midRow.setBackground(Color.green);
        botRow = new JPanel();
        botRow.setBackground(Color.green);
        setLayout(new BorderLayout());
JPanel a = new JPanel();
a.add(topRow);
a.add(midRow);
        //adds panels to the character selection panel
        add(instructRow, BorderLayout.NORTH);
        add(a, BorderLayout.CENTER);
        add(botRow,BorderLayout.SOUTH);
        

        instructRow.setLayout(new BorderLayout());
        instruct1 = new JLabel("Select an archetype and their sleep, smart and"
                               + " social points will display.");
        instruct2= new JLabel("When satsified with your choice, press the next"+
                              " button.");
        instructRow.add(instruct1,BorderLayout.NORTH);
        instructRow.add(instruct2,BorderLayout.CENTER);

        
        //creates archetype radio buttons
        athletic = new JRadioButton ("Athletic Alex");
        athletic.setBackground (Color.green);

        hermit = new JRadioButton ("Hermit Harper");
        hermit.setBackground (Color.green);

        horse = new JRadioButton ("Horse Girl Grace");
        horse.setBackground (Color.green);

        offCampus = new JRadioButton ("Off Campus Ollie");
        offCampus.setBackground (Color.green);

        society = new JRadioButton ("Society Skylar");
        society.setBackground (Color.green);

        wendy = new JRadioButton ("Wendy Wellesley");
        wendy.setBackground (Color.green);

        persStats = new JTextArea("");
        persStats.setBackground(Color.green);

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
            BufferedImage AlexPic = ImageIO.read(new File("AthleticAlex.png"));
            JLabel AlexPicLabel = new JLabel(new ImageIcon(AlexPic));
            topRow.add(AlexPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        topRow.add (athletic);

        try{
            BufferedImage HarperPic = ImageIO.read(new File(
                                                          "HermitHarper.png"));
            JLabel HarperPicLabel = new JLabel(new ImageIcon(HarperPic));
            topRow.add(HarperPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        topRow.add (hermit);

        try{
            BufferedImage GracePic = ImageIO.read(new File(
                                                        "HorseGirlGrace.png"));
            JLabel GracePicLabel = new JLabel(new ImageIcon(GracePic));
            topRow.add(GracePicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        topRow.add (horse);

        //adds the second half the arhcetypes' images and radio buttons to the 
        //midRow
        try{
            BufferedImage OlliePic = ImageIO.read(new File(
                                                        "OffCampusOllie.png"));
            JLabel OlliePicLabel = new JLabel(new ImageIcon(OlliePic));
            midRow.add(OlliePicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        midRow.add (offCampus);

        try{
            BufferedImage SkylarPic = ImageIO.read(new File(
                                                         "SocietySkylar.png"));
            JLabel SkylarPicLabel = new JLabel(new ImageIcon(SkylarPic));
            midRow.add(SkylarPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        midRow.add (society);

        try{
            BufferedImage WendyPic = ImageIO.read(new File(
                                                        "WendyWellesley.png"));
            JLabel WendyPicLabel = new JLabel(new ImageIcon(WendyPic));
            midRow.add(WendyPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        midRow.add (wendy);

        botRow.add (persStats);//adds the person's scores to the bottom row

        setBackground (Color.green);
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