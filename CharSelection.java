
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
    private JLabel quote;
    private JRadioButton athletic, hermit, horse, offCampus, society,wendy;
    private Person player;
    public CharSelection()
    {
        player = new Person();
        
        athletic = new JRadioButton ("Athletic Alex", true);
        athletic .setBackground (Color.green);

        hermit = new JRadioButton ("Hermit Harper\nhi");
        hermit.setBackground (Color.green);

        horse = new JRadioButton ("Horse Girl Grace");
        horse.setBackground (Color.green);

        offCampus = new JRadioButton ("Off-Campus Ollie");
        offCampus.setBackground (Color.green);

        society = new JRadioButton ("Society Skylar");
        society.setBackground (Color.green);

        wendy = new JRadioButton ("Wendy Wellesley");
        wendy.setBackground (Color.green);

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
        JLabel a = new JLabel();
        a.add(athletic);
        a.add(new JLabel("hahah"));
        add (a);
        //add(new JLabel("hahah"));
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
            Object source = event.getSource();
            if (source == athletic){
                player=new Person(0);
            }else if (source == hermit){
                player = new Person(1);
            }else if (source == horse){
                player = new Person(2);
            }else if (source == offCampus){
                player=new Person(3);
            }else if (source == society){
                player = new Person(4);
            }else{
                player = new Person(5);
            }
        }
    }
}//can use jlayered panel for selectio