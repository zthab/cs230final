
/**
 * At some point, we will want to be able to change the backgound according to which
 * senario the game is being played in I suppose. I'm assumeing we will want to pass 
 * the senario as command line args?
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
    private ArrayQueue<String> memQueue; //holds the words given to player 
    //The commands given in different situations
    private final static String [][] wordList={{"Right", "Left", "Center",},{"Zahra","Giulia", "Kalau",
                "Tamara", "NB"}};
    //references to the needed array memory words
    private final static String [] circumstance = {"Tunnel","FYM"};
    private final static String [] welcomeList = {"Oh no, you got lost in the Tunnels!"+
            "\nIf you can't remember how to get out, you'll be stuck down here forever!" ,
            "Welcome to your first year mentor group!" +
            "\nYou had better remember everyone's name, or else you'll die of embarassment"};
    private final static String [] losingList = {"You took a wrong turn and now you will be stuck in the tunnels forever.",
            "How embarassing, you messed up someone's name!" +
            "\nYou tried to hide in your room until you got over it, but missed too many meals and died."};                             
    private final static String [] images = {"Tunnel.jpg", "Campus.jpg"};                 
    private Vector<String> answerKey; 
    private String name;//the circumstance senario passed in the constructor;

    private CardLayout cl;
    private JLayeredPane content;
    private JLabel inputLabel, outputLabel;
    private JTextField answer;
    private JTextArea path;
    private JPanel scenario, instructions, game, gameOver, win, deck, background;
    private JButton next, start;
    private int index,count,x,y; //to resize the panel as word are added, maybe

    //-----------------------------------------------------------------
    //  Constructor: Sets up the main GUI components.
    //-----------------------------------------------------------------
    public MemoryPanel(String name){//call the senario, a string in circumstance
        this.name = name;
        //check that name is in circumstance
        index = -1;//hold the index of the desired wordList array
        for (int i = 0; i<circumstance.length;i++)
            if (circumstance[i].equals(name)) index=i;
        if (index == -1) {
            System.out.println("Nonvalid circumstance passed in constructor");  
            return;
        }
       
        randomize(); //initializes the random commands into memQueue

        content = new JLayeredPane();
        background = new JPanel();
        deck = new JPanel(new CardLayout());//this lets me switch panels and it's dope
        cl = (CardLayout)(deck.getLayout());//manages the deck

        //add image to the background
        try {
            background.add(new JLabel(new ImageIcon(ImageIO.read(new File(images[index])))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        deck.add(scenarioPanel(), "situation");
        deck.add(introPanel(), "rules");
        deck.add(game(), "game");
        deck.add(gameOver(), "loser");
        deck.add(win(), "winner");

        setPreferredSize(new Dimension(610, 455));
        setLayout(new BorderLayout());
        content.setBounds(0, 0, 610, 455); //same as frame
        //panel needs to rezise as words are given or we just take out the display, also would work
        deck.setBounds(110, 100, 400, 125);//((where the panel starts),(the panel size))
        background.setOpaque(true);
        background.setBounds(0, 0, 600, 400); 
        deck.setOpaque(true);
        content.add(background, new Integer(0), 0); //sets to the background
        content.add(deck, new Integer(1), 0);//sets to the foregound      
        //add(deck);
        add(content, BorderLayout.CENTER);       
    }

    private class TempListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {      
            String input = answer.getText();
            if (count==0){
                path.setText("Your chosen tunnel path:\n" +input);
            } else {
                path.setText(path.getText()+ "\n" + input );
            }
            count++;
            if (!input.equals(memQueue.dequeue())){
                //switch panels to a game over panel
                cl.next(deck);
            }
            if (memQueue.size()==0)
                cl.last(deck);
        }
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if (event.getSource() == next){
                cl.next(deck);//changes to the game screen
            }
            if (event.getSource() == start){
                cl.next(deck);//go to instructions
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
    private JPanel scenarioPanel(){
        scenario = new JPanel();

        JTextArea welcome = new JTextArea(welcomeList[index]);

        scenario.add(welcome);
        start = new JButton ("Start");
        start.addActionListener(new ButtonListener());
        scenario.add(start);

        return scenario;
    }

    //second screen seen
    private JPanel introPanel() {
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

        instructions.add(intro);
        instructions.add(segundo);
        instructions.add(commands);
        instructions.add(next);
        
        return instructions;
    }

    //game panel
    private JPanel game(){
        game = new JPanel();

        inputLabel = new JLabel ("Enter the words in the order in which they appeared:");
        outputLabel = new JLabel ("Your answers: ");
        path = new JTextArea ();

        answer = new JTextField (5); //box for the user input
        TempListener listener = new TempListener();
        answer.addActionListener (listener);
        count = 0;

        game.add (inputLabel);
        game.add(answer);
        //add (outputLabel);
        game.add (path);
        return game;
    }

    //gameOver panel
    private JPanel gameOver(){
        gameOver = new JPanel();

        JTextArea exit = new JTextArea(losingList[index]);
        gameOver.add(exit);

        return gameOver;    
    }

    private JPanel win(){
        win = new JPanel();

        JLabel congrats = new JLabel ("You made it out! Hurray!");
        win.add(congrats);

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
