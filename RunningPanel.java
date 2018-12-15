
/**
 * RunningPanel is the running based mini game. The user must click a button representing steps taken in a run 
 * and their success in the game is determined by how far they 'run'.
 * <br> Contains two private classes for the action listeners: one is a timer and one contains all the button actions. Additionally
 * there is are individual methods for the creation of each panel which represents a game screen 
 *
 * known bugs/details:
 * -sizing things. totally works fine, isn't priority to fix, just looks kinda wierd
 * -will not currently go on to the death screen for whatever reason. It hates me. 
 * 
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.15.18)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;
import java.awt.Color;
import java.util.Random;

public class  RunningPanel extends JPanel {
    private int count, time;
    private JButton push, start, mainGameDead, mainGameAlive;
    private JLabel label;
    private Timer timeClock;
    private JPanel intro, game, outroWin, outroLose, deck, dying, deckBig;//panels for moving through the game
    private CardLayout cl, clBig;//container for the panels

    final int TOTAL_TIME = 2;//seconds to play the game, should be 20, set to 5 for testing
    int counter = TOTAL_TIME;
    javax.swing.Timer refreshTimer;
    JLabel countdownTimerField ;

    private int finalCount;

    public RunningPanel (){
        count = 0; 

        deck = new JPanel(new CardLayout());//this holds the panels and switches between them
        cl = (CardLayout)(deck.getLayout());//manages the deck

        //holds all the pieces as one card and the death scene as the other
        deckBig = new JPanel(new CardLayout());//holds the 'smaller' deck and the death panel
        clBig = (CardLayout)(deckBig.getLayout());//manages the big deck
        
        dying = new DeathPanel();//the final game over screen
        dying.setBounds(0, 0, 610, 455);
        
        //all the screens that the user sees
        deck.add(intro(), "instructions");

        //created for ease of going from the game into the final screen
        deckBig.add(deck,"main");
        deckBig.add(dying,"dead");
        
        add(deckBig, BorderLayout.CENTER);
    }

    /**
     * Timer which begins with the game panel. User is only allowed to play the game for as long as it runs
     * and they are automatically taken to the results screen when the timer reaches 0
     */
    private class timeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            counter--;
            if (counter >= 0){
                countdownTimerField.setText(" Time left: " + counter);
            }
            if (counter == 0){
                //remove(countdownTimerField);
                //cl.removeLayoutComponent(game());//make the clock go away?
                //^didn't work, just made the timer stop at 1?, not critical tho
                if(count>=100){//deterime if go to outroWin or outroLost and go there; should be 100
                    refreshTimer.stop();
                    finalCount=count;
                    
                    deck.add(outroWin(), "win"); 
                    cl.show(deck, "win");
                }else{
                    refreshTimer.stop();
                    finalCount=count;
                    
                    deck.add(outroLose(), "lose");                  
                    cl.show(deck, "lose");                    
                }
            }
        }
    }
    
    /**
     * Button actions. The game is centered around the push button and the rest are transitions from panel to panel for the user
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent event){
            if (event.getSource() == push){
                //everytime the user pushes this button, give them a step
                count++; 
                label.setText("Steps: " + count);
                Random rand = new Random();
                int r = rand.nextInt(9);

                //flashed through different colors at random, just for fun
                if (r==1)
                    setBackground (Color.green);

                if (r==2)
                    setBackground (Color.magenta);

                if (r==3)
                    setBackground (Color.yellow);

                if (r==4)
                    setBackground (Color.blue);   

                if (r==5)
                    setBackground (Color.pink);

                if (r==6)
                    setBackground (Color.yellow);

                if (r==7)
                    setBackground (Color.lightGray);

                if (r==8)
                    setBackground (Color.blue);

                if (r==9)
                    setBackground (Color.orange);

                if (r==0)
                    setBackground (Color.cyan);
            }else if (event.getSource() == start){
                //added here so the time starts on this screen
                deck.add(game(), "play");
                cl.last(deck);
            }else if (event.getSource() == mainGameDead){
                //exit and go to the Death Screen, sad sad sad 
                clBig.show(deckBig, "dead");
                //I just don't know why this is displaying so small instead of showing the whole image?
                //this exact thing works in memory. The only difference is the timer as far as I can tell
            } else{ 
                //back to the main game
            }
        }
    }

    /**
     * The game panel where the user is challenged the push a button as many times as they can for 20 seconds
     * 
     * @return JPanel game contains game mechanisms
     */
    private JPanel game(){
        game = new JPanel();
        JLabel token = new JLabel();
        refreshTimer = new javax.swing.Timer(1000, new timeListener());
        countdownTimerField = token;
        refreshTimer.start();

        push = new JButton ("Run!"); 
        push.addActionListener (new ButtonListener() );
        
        
        label = new JLabel ("Steps: " + count);
        add(countdownTimerField);
        game.add (push); 
        game.add(label);

        setBackground(Color.cyan);
        return game;
    }
    
    /**
     * The first screen the user sees. It provides the instructions
     * 
     * @return JPanel intro 
     */
    private JPanel intro(){
        intro = new JPanel();
        start = new JButton("Start");
        start.addActionListener (new ButtonListener() );
        JTextArea instructions = new JTextArea("Welcome to the running mini game! Here is how you play:" +
                "\nOn the next screen, you will click the button as many times as you can in 20 seconds."+
                "\nEach click is a step in your run, and if you don't run far enough, there are consequnces!" +
                "\nGood luck, and click the Start button when ready");
                
        intro.add(instructions);
        intro.add(start);
        return intro;
    }

    /**
     * One of two final screens, displays if the user 'ran' enough 'steps' and takes them back to the main game
     * 
     * @return JPanel winner scenario
     */
    private JPanel outroWin(){
        outroWin = new JPanel();
        mainGameAlive = new JButton("Back to the main game");
        mainGameAlive.addActionListener (new ButtonListener() );
        JTextArea message = new JTextArea("Congrats, you ran " +finalCount+ " steps. Wow!"+
                "\nBecause you ran so fast, you made it to the omlete line in Lulu before it got too long."+
                "\nWith a full belly, you make it one day closer to graduation.");

        outroWin.add(message);
        outroWin.add(mainGameAlive);
                
        return outroWin;
    }

    /**
     * One of two final screens, displays if the user did not 'run' enough 'steps' and takes them to the final game over screen
     * 
     * @return JPanel loser scenario
     */
    private JPanel outroLose(){
        outroLose = new JPanel();
        mainGameDead = new JButton("Game Over");
        mainGameDead.addActionListener (new ButtonListener() );
        JTextArea message = new JTextArea("You ran " +count+ " steps."+
                "\nSadly, you didn't make it to the omlete line in Lulu before it got too long."+
                "\nWhile waiting in line, you die." +
                "\nPlease click 'Game Over' to move on");

        outroLose.add(message);
        outroLose.add(mainGameDead);
        return outroLose;
    }
}

