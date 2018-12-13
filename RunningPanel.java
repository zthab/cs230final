
/**
 * Write a description of class PushCounterPanel here.
 *
 * known bugs:
 * -sizing things. totally works fine, isn't priority to fix, just looks kinda wierd
 * 
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.10.18)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;
import java.awt.Color;
import java.util.Random;

public class  RunningPanel extends JPanel {

    private int count, time;
    private JButton push, start, mainGame;
    private JLabel label, token;
    private Timer timeClock;
    private JPanel intro, game, outroWin, outroLose, deck;
    private CardLayout cl;

    final int TOTAL_TIME = 20; //seconds to play the game
    int counter = TOTAL_TIME;
    javax.swing.Timer refreshTimer;
    JLabel countdownTimerField ;

    private int finalCount;

    public RunningPanel (JLabel token){
        this.token=token;
        count = 0; 

        deck = new JPanel(new CardLayout());//this lets me switch panels and it's dope
        cl = (CardLayout)(deck.getLayout());//manages the deck

        deck.add(intro(), "instructions");
        //deck.add(game(), "play");
        // deck.add(outroLose(), "lose");
        // deck.add(outroWin(), "win");
        add(deck);

        setPreferredSize (new Dimension(300, 40));
    }

    //*****************************************************************
    //  Represents a timer
    //*****************************************************************
    private class timeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            counter--;
            if (counter >= 0){
                //System.out.println(" ** " + counter);
                countdownTimerField.setText(" Time left: " + counter);
            }
            if (counter == 0){
                if(count>=75){//deterime if go to outroWin or outroLost and go there
                    refreshTimer.stop();
                    finalCount=count;
                    
                    //cl.removeLayoutComponent(game);
                    deck.add(outroWin(), "win");
                    cl.next(deck);
                }else{
                    refreshTimer.stop();
                    finalCount=count;
                    
                    //cl.removeLayoutComponent(game);
                    deck.add(outroLose(), "win");
                    cl.next(deck);
                }
            }
        }
    }

    private class ButtonListener implements ActionListener
    {
        //--------------------------------------------------------------
        //  Updates the counter and label when the button is pushed.
        //--------------------------------------------------------------
        public void actionPerformed (ActionEvent event){
            if (event.getSource() == push){
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
            }
            if (event.getSource() == start){
                cl.removeLayoutComponent(intro);
                deck.add(game(), "play");
                cl.next(deck);
            }
            if (event.getSource() == mainGame){
                //exit and go back to main Game, somehow
            }
        }
    }

    private JPanel game(){
        game = new JPanel();

        refreshTimer = new javax.swing.Timer(1000, new timeListener());
        countdownTimerField = token;
        refreshTimer.start();

        push = new JButton ("Run!"); //probs can be local
        push.addActionListener (new ButtonListener() );

        label = new JLabel ("Steps: " + count);
        add(countdownTimerField);
        game.add (push); 
        game.add (label);

        //push.setVisible(true);

        setBackground (Color.cyan);
        return game;
    }

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

    private JPanel outroWin(){
        outroWin = new JPanel();
        mainGame = new JButton("Back to the main game");
        mainGame.addActionListener (new ButtonListener() );
        JTextArea message = new JTextArea("Congrats, you ran " +count+ " steps. Wow!"+
                "\nBecause you ran so fast, you made it to the omlete line in Lulu before it got too long."+
                "\nWith a full belly, you make it one day closer to graduation.");

        outroWin.add(message);
        outroWin.add(mainGame);
        return outroWin;
    }

    private JPanel outroLose(){
        outroLose = new JPanel();
        JButton mainGame = new JButton("Back to the main game");
        mainGame.addActionListener (new ButtonListener() );
        JTextArea message = new JTextArea("Congrats, you ran " +count+ " steps. Wow!"+
                "\nSadly, you didn't make it to the omlete line in Lulu before it got too long."+
                "\nWhile waiting in line, you die.");

        outroLose.add(message);
        outroLose.add(mainGame);
        return outroLose;
    }
}

