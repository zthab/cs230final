
/**
 * Gui for the MemoryGame mini game.
 * 
 * @Known bugs-:
 * *The MemoryPanel does not resize as words are entered so after
 * 2 words you cannot see the chosen path display
 * *Right know there is no display occuring with expected instuctions and
 * also therefore no checking for if input was correct
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.1.18)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemoryGame 
{
   private static String scenario;//should this not be static? if not static, can't run in main
   
   public MemoryGame(String scenario){
       this.scenario = scenario;
   }    
       
   public String getScenario(){
       return scenario;
    }
    
   public static void main (String[] args){
       //for Testing sake
       // if(args!=null){
        // MemoryGame test = new MemoryGame(args[0]);
        // }
       
      JFrame frame = new JFrame ("Mini Game Challenge");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      
      //eventually we want to pass various senarios in the constructor
      MemoryPanel panel = new MemoryPanel("FYM");//game text panel 

      frame.setPreferredSize(new Dimension(610, 455));

      frame.add(panel);
      frame.pack();
      frame.setVisible(true);
   }
}
