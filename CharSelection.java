
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Write a description of class CharSelection here.
 *
 * @author Zahra Thabet
 * @version (a version number or a date)
 */
public class CharSelection extends JPanel
{
    private JTextArea pers;
    private JRadioButton athletic, hermit, horse, offCampus, society,wendy;
    private Person player;
    public CharSelection()
    {
        player = new Person();
        
        athletic = new JRadioButton ("Athletic Alex");
        athletic .setBackground (Color.green);

        hermit = new JRadioButton ("Hermit Harper");
        hermit.setBackground (Color.green);

        horse = new JRadioButton ("Horse Girl Grace");
        horse.setBackground (Color.green);

        offCampus = new JRadioButton ("Off-Campus Ollie");
        offCampus.setBackground (Color.green);

        society = new JRadioButton ("Society Skylar");
        society.setBackground (Color.green);

        wendy = new JRadioButton ("Wendy Wellesley");
        wendy.setBackground (Color.green);
        
         pers = new JTextArea("");

        ButtonGroup group = new ButtonGroup();
        group.add (athletic);
        group.add (hermit);
        group.add (horse);
        group.add(offCampus);
        group.add(society);
        group.add(wendy);
        
        QuoteListener listener = new QuoteListener();
        athletic.addActionListener (listener);
        hermit.addActionListener (listener);
        horse.addActionListener (listener);
        offCampus.addActionListener (listener);
        society.addActionListener (listener);
        wendy.addActionListener (listener);
        
        try{
            BufferedImage AlexPic = ImageIO.read(new File("AthleticAlex.png"));
            JLabel AlexPicLabel = new JLabel(new ImageIcon(AlexPic));
            add(AlexPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        add (athletic);
        try{
            BufferedImage HarperPic = ImageIO.read(new File("HermitHarper.png"));
            JLabel HarperPicLabel = new JLabel(new ImageIcon(HarperPic));
            add(HarperPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        add (hermit);
        try{
            BufferedImage GracePic = ImageIO.read(new File("HorseGirlGrace.png"));
            JLabel GracePicLabel = new JLabel(new ImageIcon(GracePic));
            add(GracePicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        add (horse);
        try{
            BufferedImage OlliePic = ImageIO.read(new File("OffCampusOllie.png"));
            JLabel OlliePicLabel = new JLabel(new ImageIcon(OlliePic));
            add(OlliePicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        add (offCampus);
        try{
            BufferedImage SkylarPic = ImageIO.read(new File("SocietySkylar.png"));
            JLabel SkylarPicLabel = new JLabel(new ImageIcon(SkylarPic));
            add(SkylarPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        add (society);
        try{
            BufferedImage WendyPic = ImageIO.read(new File("WendyWellesley.png"));
            JLabel WendyPicLabel = new JLabel(new ImageIcon(WendyPic));
            add(WendyPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        add (wendy);
        add (pers);
        setBackground (Color.green);
        setPreferredSize (new Dimension(300, 100));
    }
    //*****************************************************************
    // Represents the listener for all radio buttons
    //*****************************************************************
    private class QuoteListener implements ActionListener
    {
        //-----------------------------------------------------------------
        // Sets the text of the label depending on which radio
        // button was pressed.
        //-----------------------------------------------------------------
        public void actionPerformed (ActionEvent event)
        {
            JRadioButton source = (JRadioButton)event.getSource();
            String chosenName = source.getText();
            player = new Person(chosenName);
            pers.setText(player.toString());
        }
    }
}