import java.awt.*;
import java.awt.event.*;
import javax.swing.* ;
import java.awt.Color;
import java.util.Random;

/**
 * RunningPanel is the running based mini game. The user must click a button representing steps taken in a run 
 * and their success in the game is determined by how far they 'run'.
 * <br> Contains two private classes for the action listeners: one is a timer and one contains all the button actions. Additionally
 * there is are individual methods for the creation of each panel which represents a game screen 
 * 
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.15.18)
 */

public class  RunningPanel extends JPanel {
    private int count, time;
    private JButton push, start, mainGameDead, mainGameAlive;
    private JLabel label;
    private Timer timeClock;
    private JPanel intro, game, outroWin, outroLose, deck, dying, deckBig;//panels for moving through the game
    private CardLayout cl, clBig;//container for the panels

    final int TOTAL_TIME = 20;//seconds to play the game, should be 20, set to 5 for testing
    int counter = TOTAL_TIME;
    javax.swing.Timer refreshTimer;
    JLabel countdownTimerField ;

    private int finalCount;
    private Person player;
    private TrailsBinaryTree tree;
    private Boolean isLeft;
    private Font font;

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
    public RunningPanel (Person p, TrailsBinaryTree t, Boolean direct){
        player = p; 
        tree=t;
        isLeft = direct;

        count = 0; 
        deck = new JPanel(new CardLayout());//this holds the panels and switches between them
        cl = (CardLayout)(deck.getLayout());//manages the deck

        font = new Font("Verdana", Font.BOLD, 20);

        game = game();
        dying = new DeathPanel();//the final game over screen
        dying.setPreferredSize(new Dimension(1200,800));

        //all the screens that the user sees
        deck.add(intro(), "instructions");

        //created for ease of going from the game into the final screen
        deck.add(game,"main");
        deck.add(dying,"dead");

        add(deck, BorderLayout.CENTER);
    }

    /**
     * Timer which begins with the game panel. User is only allowed to play the game for as long as it runs
     * and they are automatically taken to the results screen when the timer reaches 0.
     */
    private class timeListener implements ActionListener{
        /**
         * When the game panel is launched, timer for the game begins
         */
        public void actionPerformed(ActionEvent e) {
            counter--;
            if (counter >= 0){
                countdownTimerField.setText(" Time left: " + counter);
            }
            if (counter == 0){
                if(count>=40){//if they win, go to the winning panel
                    refreshTimer.stop();
                    finalCount=count;

                    deck.add(outroWin(), "win"); 
                    cl.show(deck, "win");
                }else{//else they lost, go to the losing panel
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
                    game.setBackground (Color.green);

                if (r==2)
                    game.setBackground (Color.magenta);

                if (r==3)
                    game.setBackground (Color.yellow);

                if (r==4)
                    game.setBackground (Color.blue);   

                if (r==5)
                    game.setBackground (Color.pink);

                if (r==6)
                    game.setBackground (Color.yellow);

                if (r==7)
                    game.setBackground (Color.lightGray);

                if (r==8)
                    game.setBackground (Color.blue);

                if (r==9)
                    game.setBackground (Color.orange);

                if (r==0)
                    game.setBackground (Color.cyan);
            }else if (event.getSource() == start){
                //added here so the time starts on this screen
                deck.add(game(), "play");
                cl.last(deck);
            }else if (event.getSource() == mainGameDead){
                JPanel pare = (JPanel) deck.getParent();
                JPanel cardLayoutPanel = (JPanel) pare.getParent();
                CardLayout layout = (CardLayout) cardLayoutPanel.getLayout(); 

                //exit and go to the Death Screen, sad sad sad 
                cardLayoutPanel.add(dying, "dead");
                layout.show(cardLayoutPanel,"dead");
            } else{ 
                JPanel pare = (JPanel) deck.getParent();
                JPanel cardLayoutPanel = (JPanel) pare.getParent();
                CardLayout layout = (CardLayout) cardLayoutPanel.getLayout(); 
                try{
                    if (isLeft){
                        //incremements the tree so that the current Situation
                        //is the left child of the current Situation
                        tree.nextLeft();
                        //shows a SituationPanel of the new current Situation
                        SituationPanel nextPanel = new SituationPanel(player,
                                tree); 
                        cardLayoutPanel.add(nextPanel,"left");
                        layout.show(cardLayoutPanel, "left");
                    }else{
                        //incremements the tree so that the current Situation
                        //is the left child of the current Situation
                        tree.nextRight();
                        //shows a SituationPanel of the new current Situation
                        SituationPanel nextPanel = new SituationPanel(player, 
                                tree); 
                        cardLayoutPanel.add(nextPanel,"right");
                        layout.show(cardLayoutPanel, "right");
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    GraduationPanel win = new GraduationPanel(); 
                    cardLayoutPanel.add(win,"winPanel");
                    layout.show(cardLayoutPanel, "winPanel");
                }
            }
        }
    }

    /**
     * The game panel where the user is challenged the push a button as many 
     * times as they can for 20 seconds
     * 
     * @return JPanel game contains game mechanisms
     */
    private JPanel game(){
        Font gameFont = new Font("Verdana", Font.BOLD, 32);

        game = new JPanel();
        JLabel token = new JLabel();
        refreshTimer = new javax.swing.Timer(1000, new timeListener());
        countdownTimerField = token;
        token.setFont(gameFont);

        refreshTimer.start();

        game.setPreferredSize(new Dimension(1200,800));
        push = new JButton ("Run!"); 
        push.setFont(gameFont);
        push.addActionListener (new ButtonListener() );        
        push.setPreferredSize(new Dimension(1200, 100));

        game.setLayout(new GridLayout(3,1,30,30));
        label = new JLabel ("Steps: " + count);
        label.setFont(gameFont);        
        countdownTimerField.setAlignmentX(Component.CENTER_ALIGNMENT);
        push.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        game.add(countdownTimerField);       
        game.add(push);
        game.add(label);

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
        JTextArea instructions = new JTextArea("Welcome to the running mini"+
                " game! Here is how you play:\nOn the next screen, you will"+
                " click the button as many times as you can in 20 seconds."+
                "\nEach click is a step in your run, and if you don't run far"+ 
                " enough, there are consequnces!\nGood luck, and click the"+
                " Start button when ready");

        start.setFont(font);
        instructions.setFont(font);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

        intro.setLayout(new GridLayout(3,1,30,30));
        intro.add(instructions);
        intro.add(start);
        intro.setBackground(new Color(0,39,118));

        return intro;
    }

    /**
     * One of two final screens, displays if the user 'ran' enough 'steps' 
     * and takes them back to the main game
     * 
     * @return JPanel winner scenario
     */
    private JPanel outroWin(){
        Font winFont = new Font("Verdana", Font.BOLD, 150);        
        outroWin = new JPanel();

        mainGameAlive = new JButton("Back to the main game");
        mainGameAlive.addActionListener (new ButtonListener() );
        JTextArea message = new JTextArea("Congrats, you ran " +finalCount+ 
                " steps. Wow! You're one day closer to graduation.");
        JTextArea space = new JTextArea("TIME'S UP");
        JPanel panel = new JPanel();   
        panel.add(space, BorderLayout.CENTER);

        mainGameAlive.setFont(font);
        message.setFont(font);   
        space.setFont(winFont);
        mainGameAlive.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        outroWin.setLayout(new GridLayout(3,1,30,30));
        outroWin.add(panel);
        outroWin.add(message);       
        outroWin.add(mainGameAlive);
        outroWin.setBackground(new Color(0,39,118));

        return outroWin;
    }

    /**
     * One of two final screens, displays if the user did not 'run' enough 
     * 'steps' and takes them to the final game over screen
     * 
     * @return JPanel loser scenario
     */
    private JPanel outroLose(){
        outroLose = new JPanel();
        Font loseFont = new Font("Verdana", Font.BOLD, 150);  

        mainGameDead = new JButton("Game Over");
        mainGameDead.addActionListener (new ButtonListener() );
        JTextArea message = new JTextArea("You ran " +count+ " steps."+
                "\nSadly, you didn't make it to the omlete line in Lulu"+
                "before it got too long."+
                "\nWhile waiting in line, you die." +
                "\nPlease click 'Game Over' to move on");
        JTextArea space = new JTextArea("TIME'S UP");
        JPanel panel = new JPanel();   
        panel.add(space, BorderLayout.CENTER);

        mainGameDead.setFont(font);
        message.setFont(font);
        space.setFont(loseFont);
        mainGameDead.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        outroLose.setLayout(new GridLayout(3,1,30,30));
        outroLose.add(panel);
        outroLose.add(message);
        outroLose.add(mainGameDead);
        outroLose.setBackground(new Color(0,39,118));

        return outroLose;
    }
}

