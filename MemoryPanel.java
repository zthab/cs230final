/**
 * A memory based mini game. The user is given a list of words and then 
 * prompted to input into a JTextField that same list of words in the correct 
 * order. The constructor takes a string as a parameter and if the string
 * is one of the hard coded scenarios in the circumstaces array, the constructor
 * forms the corresponding game for the scenario. 
 * </br> Acceptable parameters for the constructor: Tunnel, FYM, Squirrel
 * </br> Methods include a method for each panel of the game which creates that
 * panel, randomize() which takes the situational commands that the user must 
 * memorize and randomly puts them into memQueue, and a toString for memeQueue.
 * Two private classes are created, one for each action listener. One is for the
 * JTextField which the user inputs answers into and the other is for the 
 * variouse buttons. 
 *
 * Known bugs/details: 
 * -The font could be larger
 * -might be nice for the user input words to not be case sensitive if we have time
 * -the user has to manually clear the TextField each time, adds unnecessary user error
 * 
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.15.18)
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
    protected final static String [][] wordList={{"Right", "Left", "Center", 
                "Jump", "Crawl" },
            {"Zahra","Giulia", "Kalau", "Tamara", "NB"},
            {"Run", "Freeze", "Fight", "Scamper", "Hide"}};
    //references to the needed array memory words
    protected final static String [] circumstance = {"Tunnel","FYM","Squirrel"};
    //all of the text which is specific to each circumstance
    protected final static String [] welcomeList = {"Oh no, you got lost in the"+
            "Tunnels!\nIf you can't remember how to get out, you'll be stuck"+
            "down here forever!" ,"Welcome to your first year mentor group!" +
            "\nYou had better remember everyone's name, or else you'll die of" +
            "embarassment",
            "Ack! A rabbid squirrel has started to chase you!\nRun, fight, hide-" +
            "do what ever you can to escape before it is too late!           "};
    protected final static String [] losingList = {"You took a wrong turn and"+
            "now you will be stuck in the tunnels forever.",
            "How embarassing, you messed up someone's name!" +            
            "\nYou tried to hide in your room until you got over it, but missed"+
            "too many meals and died.",
            "Oh dear, you couldn't out smart the squirrel." +
            "\nYou got bitten and died."};  
    protected final static String [] winningList = {"You successfully made it"+
            "out of the tunnels, hurray!",
            "Great job, you learned everyone's name, and earned a few new "+ 
            "friends. Hurray!",
            "In the nick of time, you made it into the nearest building and "+
            "esccaped!\nThe squirrel moved on to it's next victim, and you"+
            "are one day closer to graduation."};             
    protected final static String [] images = {"Tunnel.jpg", "Campus.jpg", 
            "Squirrel.jpg"};                 
    protected Vector<String> answerKey; 
    protected String name;//the circumstance senario passed in the constructor;

    protected CardLayout cl,clBig;//containes to flip through the panels
    //holds two panels one of which is the background image
    protected JLayeredPane content;
    protected ImageIcon image;
    protected JLabel inputLabel, outputLabel,pic;
    protected JTextField answer;
    protected JTextArea path;
    protected JPanel scenario, instructions, game, gameOver, win, deck, 
    background, dying, deckBig;
    protected JButton next, start, dead, alive;
    protected int index,count;
    protected boolean life;//we might need this?
    
    protected Person player;
    protected TrailsBinaryTree tree;
    protected Boolean isLeft;

    /**
     * The constructor for the running game takes three parameters and 
     * creates the running mini game
     * 
     * @param Person p -the person class holding the current stats
     * @param TrailsBinary t -where the user is in the binaryTree determins 
     * their in game location
     * @Param Boolean direct -determins to which child of the current leaf 
     * the user goes to after the game
     */
    public MemoryPanel(Person p, TrailsBinaryTree t, Boolean direct, 
    String scenario){
        player=p;
        tree=t;
        isLeft=direct;
        name=scenario;
        
        index = -1;//hold the index of the desired wordList array
        //check that name is in circumstance
        for (int i = 0; i<circumstance.length;i++)
            if (circumstance[i].equals(name)) index=i;
        if (index == -1) {
            System.out.println("Nonvalid circumstance passed in constructor");  
            return;
        }

        randomize(); //initializes the random commands into memQueue
        //olds a background images and a panel on top
        content = new JLayeredPane();
        background = new JPanel();//holds the scenario image
        //container for the panels, switches between them like playingcards
        deck = new JPanel(new CardLayout());
        cl = (CardLayout)(deck.getLayout());//manages the deck

        //holds all the pieces as one card and the death scene as the other
        deckBig = new JPanel(new CardLayout());//the largest container
        clBig = (CardLayout)(deckBig.getLayout());//manages the big deck

        //The scene if the user loses
        dying = new DeathPanel();
        dying.setBounds(0, 0, 610, 455); 

        //add image to the background
        try {
            //scaling all input files to be the same size
            image = new ImageIcon(ImageIO.read(new File(images[index])));
            Image pic = image.getImage(); // transform it 
            Image newimg = pic.getScaledInstance(
                    610, 455,  java.awt.Image.SCALE_SMOOTH);  
            image = new ImageIcon(newimg);  // transform it back

            background.add(new JLabel(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //add all the panel to the deck so they can be flipped through
        deck.add(scenarioPanel(), "situation");
        deck.add(introPanel(), "rules");
        deck.add(game(), "game");
        deck.add(gameOver(), "loser");
        deck.add(win(), "winner");

        //the largest container, to flip between game and outside panel
        deckBig.add(content,"main");
        deckBig.add(dying,"dead");

        //set up how the gui is displayed
        setLayout(new BorderLayout());
        content.setBounds(0, 0, 610, 455); //same as frame
        deck.setBounds(85, 100, 450, 125);
        background.setOpaque(true);
        background.setBounds(0, 0, 610, 455); 
        deck.setOpaque(true);
        content.add(background, new Integer(0), 0); //sets to the background
        content.add(deck, new Integer(1), 0);//sets to the foregound      

        add(deckBig, BorderLayout.CENTER);
    }

    /**
     * Class for recieving text input from the user
     */
    protected class UserListener implements ActionListener {
        /**
         * Takes the user input and checks it with the correct answer by 
         * comparing it to the current item dequeued from memQueue.
         * If the answer is correct, the user may continue until all the 
         * answers have been inputted. If the answer is incorrect,
         * the user is taken to a game over screen.
         */
        public void actionPerformed (ActionEvent event) {      
            String input = answer.getText();//the command input by the user
            //displayes the user's previous choices
            if (count==0){
                path.setText("Your answer:\n" +input);
            } else {
                path.setText(path.getText()+ "\n" + input );
            }
            count++;
            //if word doesn't match the top of the queue, game over. 
            //If it does, just keep playing
            if (!input.equals(memQueue.dequeue())){
                //switch panels to a game over panel
                cl.show(deck, "loser");
            }
            //if the queue gets empty, it means all the words matched and 
            //they won
            if (memQueue.size()==0)
                cl.show(deck, "winner");
        }
    }

    /**
     * Class for the actions taken when the user clicks various buttons
     */
    protected class ButtonListener implements ActionListener {
        /**
         * Depending on which button is selected, a different action occures.
         * All of these buttons are screen transitions
         */
        public void actionPerformed(ActionEvent event){
            //all of these buttons are transitions for the user
            if (event.getSource() == next){
                cl.show(deck,"game");//changes to the game screen
            }else if (event.getSource() == start){
                cl.show(deck,"rules");//go to instructions
            }else if (event.getSource() == dead){
                life = false; //dunno if we need this but seems helpful
                clBig.show(deckBig, "dead");
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
        while (memQueue.size()<listLength){//until the memQueue is full
            int n = rand.nextInt(listLength);//pick a rand index in the list
            if (answerKey.indexOf(wordList[index][n])==-1){
                //keep answerKey in the dequeue order
                answerKey.add(wordList[index][n]);
                memQueue.enqueue(wordList[index][n]);//add it to memQueue
            }
        }
    }

    /**
     * First scenario sceen- welcome the user and set the scene
     * 
     * @return JPanel first screen
     */
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

    //second screen seen, instructions
    /**
     * Second scenario sceen- provides instructions for the game
     * 
     * @return JPanel second screen
     */
    protected JPanel introPanel() {
        //randomize();
        instructions = new JPanel();
        instructions.setPreferredSize(new Dimension(500,200));

        JLabel intro = new JLabel("Welcome to the memory mini game! Here is"+
                "how you play:");
        JTextArea segundo = new JTextArea("Below is a list of words. Memorize"+
                "them if you can!\nOn the next screen, you will input"+
                "those words in the correct order. When ready, hit the 'Go!'"+
                "Button to move on.");
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

    /**
     * Third scenario sceen- Where the user plays the game by inputting words
     * into a text field
     * 
     * @return JPanel third screen
     */
    protected JPanel game(){
        game = new JPanel();

        inputLabel = new JLabel ("Enter the words in the order in which they"+
            "appeared:");
        path = new JTextArea ();//displays which word the user has put in

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

    /**
     * One of two possible final scenario sceen, appears if the user does 
     * not input the correct word
     * 
     * @return JPanel loser screen
     */
    protected JPanel gameOver(){
        gameOver = new JPanel();
        JTextArea exit = new JTextArea(losingList[index] + 
                "\nPlease click 'Game Over' to move on");
        dead = new JButton("Game Over");

        dead.addActionListener(new ButtonListener());
        gameOver.setLayout(new FlowLayout());
        gameOver.add(exit);
        gameOver.add(dead);

        return gameOver;    
    }

    /**
     * One of two possible final scenario sceen, appears if the user inputs 
     * all the correct words
     * 
     * @return JPanel winner screen
     */
    protected JPanel win(){
        win = new JPanel();

        JTextArea congrats = new JTextArea(winningList[index] +
                "\nPlease click 'Back to School' to move on");
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
}
