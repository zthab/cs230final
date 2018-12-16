
/**
 * ModMemoryGame is identical to MemoryGame exxcept the user plays the game by 
 * selecting from button options instead of inputing
 * the answers.
 * <br>Overridded methods from MemoryGame are game() which constructs the game
 * panel and both private classes of action listeners are overridded to 
 * accomedate the buttons version.  
 * 
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

public class ModMemoryPanel extends MemoryPanel  {
    protected JButton opt1,opt2,opt3,opt4,opt5;
    /**
     * Constructor for objects of class ModMemoryGame
     */
    public ModMemoryPanel(Person p, TrailsBinaryTree t, Boolean direct, 
    String Scenario)
    {
        super(p,t,direct,Scenario);
    }

    /**
     * The screen where the user plays the game by selecting the buttons in 
     * the correct order
     * 
     * @override
     * @return JPanel game which contains the modified game mechanisms
     */
    protected JPanel game(){
        game = new JPanel();
        opt1 = new JButton(wordList[index][0]);
        opt2 = new JButton(wordList[index][1]);
        opt3 = new JButton(wordList[index][2]);
        opt4 = new JButton(wordList[index][3]);
        opt5 = new JButton(wordList[index][4]);
        path = new JTextArea ();

        //a bit artificial but the spaces are to force the buttons to a new
        //row, not ideal
        inputLabel = new JLabel ("       Select the words in the order in"+
            " which they appeared:        ");
        opt1.addActionListener(new ButtonListener());
        opt2.addActionListener(new ButtonListener());
        opt3.addActionListener(new ButtonListener());
        opt4.addActionListener(new ButtonListener());
        opt5.addActionListener(new ButtonListener());
        inputLabel.setFont(font);
        opt1.setFont(font);
        opt2.setFont(font);
        opt3.setFont(font);
        opt4.setFont(font);
        opt5.setFont(font);
        path.setFont(font);
        count = 0;

        game.setLayout(new FlowLayout());
        game.add (inputLabel);
        game.add (path);
        game.add(opt1);
        game.add(opt2);
        game.add(opt3);
        game.add(opt4);
        game.add(opt5);

        return game;
    }

    /**
     * Instead of a JTextField, this recieves the words from the buttons
     * @Override
     */
    protected class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if (event.getSource() == next){
                cl.next(deck);//changes to the game screen
            }
            if (event.getSource() == start){
                cl.next(deck);//go to instructions
            }          
            if (event.getSource() == opt1) push(opt1);
            if (event.getSource() == opt2) push(opt2);
            if (event.getSource() == opt3) push(opt3);
            if (event.getSource() == opt4) push(opt4);
            if (event.getSource() == opt5) push(opt5);

        }
    }

    /**
     * Instead of a JTextField, this recieves the words from the buttons and 
     * displays the user choices based which button was clicked
     * 
     * @Override
     */
    public void push(JButton click){
        String input = click.getText();
        if (count==0){
            path.setText("Your answer:\n" +input);
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
