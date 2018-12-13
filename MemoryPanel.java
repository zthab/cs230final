
/**
 * A memory based mini game. The user is given a list of words and then prompted to input into a JTextField
 * that same list of words in the correct order. The constructor takes a string as a parameter and if the string
 * is one of the hard coded scenarios in the circumstaces array, the constructor forms the corresponding game for
 * the scenario. 
 * </br> Acceptable parameters for the constructor: Tunnel, FYM, Squirrel
 * </br> yada yada about methods
 *
 * Known bugs/details: 
 * -The font could be larger
 * -might be nice for the user input words to not be case sensitive if we have time
 * -the user has to manually clear the TextField each time
 * 
 * @author (nbryant2, zthabet, gbronzi)
 * @version (a version number or a date)
 */
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javafoundations.*;

public class MemoryPanel extends JPanel {
    protected ArrayQueue<String> memQueue; //holds the words given to player 
    //The commands given in different situations
    protected final static String [][] wordList={{"Right", "Left", "Center", "Jump", "Crawl" },
            {"Zahra","Giulia", "Kalau", "Tamara", "NB"},
            {"Run", "Freeze", "Fight", "Scamper", "Hide"}};
    //references to the needed array memory words
    protected final static String [] circumstance = {"Tunnel","FYM","Squirrel"};
    protected final static String [] welcomeList = {"Oh no, you got lost in the Tunnels!"+
            "\nIf you can't remember how to get out, you'll be stuck down here forever!" ,
            "Welcome to your first year mentor group!" +
            "\nYou had better remember everyone's name, or else you'll die of embarassment",
            "Ack! A rabbid squirrel has started to chase you!" +
            "\nRun, fight, hide- do what ever you can to escape before it is too late!           "};
    protected final static String [] losingList = {"You took a wrong turn and now you will be stuck in the tunnels forever.",
            "How embarassing, you messed up someone's name!" +
            "\nYou tried to hide in your room until you got over it, but missed too many meals and died.",
            "Oh dear, you couldn't out smart the squirrel." +
            "\nYou got bitten and died."};  
    protected final static String [] winningList = {"You successfully made it out of the tunnels, hurray!",
            "Great job, you learned everyone's name, and earned a few new friends. Hurray!",
            "In the nick of time, you made it into the nearest building and esccaped!" +
            "\nThe squirrel moved on to it's next victim, and you are one day closer to graduation."};             
    protected final static String [] images = {"Tunnel.jpg", "Campus.jpg", "Squirrel.jpg"};                 
    protected Vector<String> answerKey; //could be taken out, I think
    protected String name;//the circumstance senario passed in the constructor;

    protected CardLayout cl;
    protected JLayeredPane content;
    protected ImageIcon image;
    protected JLabel inputLabel, outputLabel,pic;
    protected JTextField answer;
    protected JTextArea path;
    protected JPanel scenario, instructions, game, gameOver, win, deck, background, dying;
    protected JButton next, start, dead, alive;
    protected int index,count,x,y; //to resize the panel as word are added, maybe
    protected boolean life;

    //-----------------------------------------------------------------
    //  Constructor: Sets up the main GUI components.
    //-----------------------------------------------------------------
    public MemoryPanel(String name){//call the senario, a string in circumstance
        this.name = name;
        life = true;
        //check that name is in circumstance
        index = -1;//hold the index of the desired wordList array
        for (int i = 0; i<circumstance.length;i++)
            if (circumstance[i].equals(name)) index=i;
        if (index == -1) {
            System.out.println("Nonvalid circumstance passed in constructor");  
            return;
        }

        randomize(); //initializes the random commands into memQueue

        content = new JLayeredPane();//can hold all the things
        background = new JPanel();//holds the scenario image
        deck = new JPanel(new CardLayout());//this lets me switch panels and it's dope
        cl = (CardLayout)(deck.getLayout());//manages the deck
        dying = new DeathPanel();

        //add image to the background
        try {
            //scaling all input files to be the same size
            image = new ImageIcon(ImageIO.read(new File(images[index])));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(610, 455,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //add all the panel to the deck so the can be flipped through
        deck.add(scenarioPanel(), "situation");
        deck.add(introPanel(), "rules");
        deck.add(game(), "game");
        deck.add(gameOver(), "loser");
        deck.add(win(), "winner");
        deck.add(dying, "death");

        //setPreferredSize(new Dimension(610, 455));
        setLayout(new BorderLayout());
        content.setBounds(0, 0, 610, 455); //same as frame
        //panel needs to rezise as words are given or we just make the display bigger
        deck.setBounds(85, 100, 450, 125);//((where the panel starts),(the panel size))
        background.setOpaque(true);
        background.setBounds(0, 0, 610, 455); 
        deck.setOpaque(true);
        content.add(background, new Integer(0), 0); //sets to the background
        content.add(deck, new Integer(1), 0);//sets to the foregound      

        add(content, BorderLayout.CENTER);       
    }

    protected class UserListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {      
            String input = answer.getText();
            if (count==0){
                path.setText("Your answer:\n" +input);
            } else {
                path.setText(path.getText()+ "\n" + input );
            }
            count++;
            if (!input.equals(memQueue.dequeue())){
                //switch panels to a game over panel
                cl.show(deck, "loser");
            }
            if (memQueue.size()==0)
                cl.show(deck, "winner");
        }
    }

    protected class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if (event.getSource() == next){
                cl.show(deck,"game");//changes to the game screen
            }else if (event.getSource() == start){
                cl.show(deck,"rules");//go to instructions
            }else if (event.getSource() == dead){
                life = false;
                // background.setOpaque(false);
                // dying.setBounds(0, 0, 610, 455);
                // deck.setOpaque(false);
                // content.add(dying, new Integer(1), 0);//sets to the foregound 
                // revalidate();
                //^This does't work either, yet
                
                //cl.show(deck, "death");->this places the images in the small little box in the center, no good
                
            }else{
                //go back to main game
            }
        }
    }

    /**
     * Randomizes order of words in one of the arrays in wordList and puts them 
     * in memory queue.
     */
    public void randomize(){

        memQueue = new ArrayQueue<String>();
        Random rand = new Random();
        answerKey = new Vector<String>();

        int listLength = wordList[index].length;
        while (memQueue.size()<listLength){
            int n = rand.nextInt(listLength);
            if (answerKey.indexOf(wordList[index][n])==-1){//if it isn't in the anwerKey
                answerKey.add(wordList[index][n]);//keep answerKey in the dequeue order
                memQueue.enqueue(wordList[index][n]);
            }
        }
    }

    //first, scenario sceen
    protected JPanel scenarioPanel(){
        scenario = new JPanel();

        JTextArea welcome = new JTextArea(welcomeList[index]);
        start = new JButton ("Start");
        start.addActionListener(new ButtonListener());

        //scenario.setLayout(new BoxLayout(scenario, BoxLayout.Y_AXIS));  
        scenario.setLayout(new FlowLayout());
        scenario.add(welcome);
        scenario.add(start);

        return scenario;
    }

    //second screen seen
    protected JPanel introPanel() {
        //randomize();
        instructions = new JPanel();
        instructions.setPreferredSize(new Dimension(500,200));

        JLabel intro = new JLabel("Welcome to the memory mini game! Here is how you play:");
        JTextArea segundo = new JTextArea("Below is a list of words. Memorize them if you can!" +
                "\nOn the next screen, you will input those words in the correct order."
                + "\nWhen ready, hit the 'Go!' Button to move on.");
        next = new JButton ("Go!");
        next.addActionListener(new ButtonListener());

        JLabel commands = new JLabel("Memory words: " + answerKey.toString());

        //instructions.setLayout(new BoxLayout(instructions, BoxLayout.Y_AXIS));
        instructions.setLayout(new FlowLayout());
        instructions.add(intro);
        instructions.add(segundo);
        instructions.add(commands);
        instructions.add(next);

        return instructions;
    }

    //game panel
    protected JPanel game(){
        game = new JPanel();

        inputLabel = new JLabel ("Enter the words in the order in which they appeared:");
        path = new JTextArea ();

        answer = new JTextField (5); //box for the user input
        UserListener listener = new UserListener();
        answer.addActionListener (listener);
        count = 0;

        game.setLayout(new FlowLayout());
        game.add (inputLabel);
        game.add(answer);
        game.add (path);

        return game;
    }

    //gameOver panel
    protected JPanel gameOver(){
        gameOver = new JPanel();
        JTextArea exit = new JTextArea(losingList[index] + "\nPlease click 'Game Over' to move on");
        dead = new JButton("Game Over");

        dead.addActionListener(new ButtonListener());
        gameOver.setLayout(new FlowLayout());
        gameOver.add(exit);
        gameOver.add(dead);

        return gameOver;    
    }

    protected JPanel win(){
        win = new JPanel();

        JTextArea congrats = new JTextArea(winningList[index] + "\nPlease click 'Back to School' to move on");
        alive = new JButton("Back to School");

        win.setLayout(new FlowLayout());
        win.add(congrats);
        win.add(alive);

        return win;    
    }

    /**
     * Returns String of memQueue in order.
     */
    public String toString(){
        String result = "";
        for (int i = 0; i<memQueue.size();i++){
            String temp = memQueue.dequeue();
            result+=temp + ", ";
            memQueue.enqueue(temp);
        }
        return result;
    }

    public static void main (String[] args){
        //Testing code
        //MemoryPanel test = new MemoryPanel("FYM");
        //test.randomize();
        //test.toString();
        //Testing randomize()
        //System.out.println(test.randomize());

    }
}
