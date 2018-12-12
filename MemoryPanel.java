
/**
 * At some point, we will want to be able to change the backgound according to which
 * senario the game is being played in I suppose. I'm assumeing we will want to pass 
 * the senario as command line args?
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javafoundations.*;


public class MemoryPanel<T> extends JPanel {
   private ArrayQueue<T> memQueue; //holds the words given to player 
   //The commands given in different situations
   private final static String [][] wordList={{"Right", "Left", "Center",},{"Zahra","Giulia"}};
   //references to the needed array memory words
   private final static String [] circumstance = {"Tunnel","FYM"};
   private Vector<T> answerKey;
   
   private JLabel inputLabel, outputLabel;
   private JTextField answer;
   private JTextArea path;
   private int count,x,y; //to resize the panel as word are added

   //-----------------------------------------------------------------
   //  Constructor: Sets up the main GUI components.
   //-----------------------------------------------------------------
   public MemoryPanel(){
      inputLabel = new JLabel ("Enter the words in the order in which they appeared:");
      outputLabel = new JLabel ("Your chosen tunnel path: ");
      path = new JTextArea ();
      count = 0;
      x=300;
      y=75;

      answer = new JTextField (5); //box for the user input
      TempListener listener = new TempListener();
      answer.addActionListener (listener);//records user input


      add (inputLabel);
      add (answer);
      //add (outputLabel);
      add (path);

      //setPreferredSize (new Dimension(x, y));
      //setBackground (Color.blue);
   }

   private class TempListener implements ActionListener {
      public void actionPerformed (ActionEvent event)
      {
          String input = answer.getText();
          if (count==0){
            path.setText("Your chosen tunnel path:\n" +input);
          } else {
            path.setText(path.getText()+ "\n" + input );
            //y += 20;
          }
          //y+=20;
          count++;
      }
   }
   
   /**
    * memory(String name)
     name is the name of the specific string array of words to be used in this game 
     from WordLists.
     Instantiates memQueue to empty queue.
     Calls the randomize method to fill memQueue.
    */
   public void memory(String name){
       //check that name is in circumstance
       int listIndex = -1;//hold the index of the desired wordList array
       for (int i = 0; i<circumstance.length;i++)
            if (circumstance[i].equals(name)) listIndex=i;
       if (listIndex == -1) System.out.println("Nonvalid circumstance passed in memory");     
       
       randomize(name);//fills memQueue randomly
   }
   
   /**
    * Randomizes order of words in one of the arrays in wordList and puts them 
    * in memory queue.
    */
   public void randomize(String name){
       //don't need to check for valid string name bc always called in memory after the check
       int listIndex = -1;//hold the index of the desired wordList array
       for (int i = 0; i<circumstance.length;i++)
            if (circumstance[i].equals(name)) listIndex=i;
       
       ArrayQueue<String> memQueue = new ArrayQueue<String>();//changed from T to string
       Random rand = new Random();
       Vector<String> answerKey = new Vector<String>();
       
       int listLength = wordList[listIndex].length;
       while (memQueue.size()<listLength){
           int n = rand.nextInt(listLength);
           if (answerKey.indexOf(wordList[listIndex][n])==-1){//if it isn't in the anwerKey
               answerKey.add(wordList[listIndex][n]);//keep answerKey in the dequeue order
               memQueue.enqueue(wordList[listIndex][n]);
            }
       }
   }
   
   /**
    * Returns String of memQueue in order.
    */
   public String toString(){
       String result = answerKey.toString();       
       return result;
    }
 }
