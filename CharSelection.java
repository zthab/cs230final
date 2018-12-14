
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
    private JPanel midPanel, botPanel;
    private JLabel instruct1, instruct2;
    private JTextArea pers;
    private JRadioButton athletic, hermit, horse, offCampus, society,wendy;
    private JButton next; 
    private Person player;
    private Icon curMouse;
    private Boolean hasSelectedBefore;
    private JPanel topRow, midRow, botRow;
    //private JButton next;
    public CharSelection()
    {
        //put in instructions 
        
        //setLayout(new CardLayout());
        
        hasSelectedBefore=false;
        JPanel nea = new JPanel();
        nea.setLayout(new BorderLayout());
        //player = new Person();
         instruct1 = new JLabel("Select an archetype and their sleep, smart and social points will display.");
        instruct2= new JLabel("When satsified with your choice, press the next button.");
        add(nea);
        nea.add(instruct1,BorderLayout.NORTH);
        nea.add(instruct2,BorderLayout.CENTER);
         
         topRow = new JPanel ();
         midRow = new JPanel();
         botRow = new JPanel();
        //midPanel.setLayout(new BorderLayout());
        add(topRow);
        add(midRow);
        add(botRow);
        
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
        
         pers = new JTextArea("");
         pers.setBackground(Color.green);

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
        
        try{
            BufferedImage AlexPic = ImageIO.read(new File("AthleticAlex.png"));
            JLabel AlexPicLabel = new JLabel(new ImageIcon(AlexPic));
            topRow.add(AlexPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        topRow.add (athletic);
        try{
            BufferedImage HarperPic = ImageIO.read(new File("HermitHarper.png"));
            JLabel HarperPicLabel = new JLabel(new ImageIcon(HarperPic));
            //HarperPicLabel.addMouseListener(new ScrollListener());
            
            //HarperPicLabel.setSize(new Dimension(100000,10000));
            //System.out.println(HarperPicLabel.size());
            topRow.add(HarperPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        topRow.add (hermit);
        try{
            BufferedImage GracePic = ImageIO.read(new File("HorseGirlGrace.png"));
            JLabel GracePicLabel = new JLabel(new ImageIcon(GracePic));
            topRow.add(GracePicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        topRow.add (horse);
        try{
            BufferedImage OlliePic = ImageIO.read(new File("OffCampusOllie.png"));
            JLabel OlliePicLabel = new JLabel(new ImageIcon(OlliePic));
            midRow.add(OlliePicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        midRow.add (offCampus);
        try{
            BufferedImage SkylarPic = ImageIO.read(new File("SocietySkylar.png"));
            JLabel SkylarPicLabel = new JLabel(new ImageIcon(SkylarPic));
            midRow.add(SkylarPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        midRow.add (society);
        try{
            BufferedImage WendyPic = ImageIO.read(new File("WendyWellesley.png"));
            JLabel WendyPicLabel = new JLabel(new ImageIcon(WendyPic));
            midRow.add(WendyPicLabel);
        }   catch(IOException e){
            System.out.println("Image not found in directory.");
        }
        midRow.add (wendy);
        botRow.add (pers);
        
        setBackground (Color.green);
        setPreferredSize (new Dimension(300, 100));
    }
    //*****************************************************************
    // Represents the listener for all radio buttons
    //*****************************************************************
    private class ButtonListener implements ActionListener
    {
        //-----------------------------------------------------------------
        // Sets the text of the label depending on which radio
        // button was pressed.
        //-----------------------------------------------------------------
        public void actionPerformed (ActionEvent event)
        {
            if(!(event.getSource()==next)){
            if (!hasSelectedBefore){
                System.out.println("hasn't");
                 next = new JButton("next");
                next.addActionListener(new ButtonListener());
                botRow.add(next);
            }
            
            JRadioButton source = (JRadioButton)event.getSource();
            String chosenName = source.getText();
            player = new Person(chosenName);
            pers.setText(player.toString());
            hasSelectedBefore = true;
        }else{
            JButton button = (JButton)event.getSource();
            JPanel buttonPanel = (JPanel)button.getParent();
            JPanel test = (JPanel)buttonPanel.getParent();
            JPanel cardLayoutPanel = (JPanel)test.getParent();
            CardLayout layout = (CardLayout)cardLayoutPanel.getLayout();
            
             TrailsBinaryTree tree = new TrailsBinaryTree("Situations.txt");
            SituationPanel nextPanel = new SituationPanel(player, tree); 
                        cardLayoutPanel.add(nextPanel,"startSit");
                        layout.show(cardLayoutPanel, "startSit");
        }
            //add button 
        }
    }
    private class ScrollListener extends MouseAdapter
    {
        //-----------------------------------------------------------------
        // Sets the text of the label depending on which radio
        // button was pressed.
        //-----------------------------------------------------------------
        public void mouseEntered (MouseEvent event)
        {
            JLabel source = (JLabel)event.getSource();
            curMouse = source.getIcon();
            source.setIcon(null);
            
            source.setText("hi");
        }
        
        public void mouseExited(MouseEvent event){
            JLabel source = (JLabel)event.getSource();
            source.setIcon(curMouse);
            source.setText("");
        }
    }
}