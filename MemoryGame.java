
/**
 * Gui for the MemoryGame mini game. Displays the memory game based on a scenario
 * 
 * Known bugs:
 * -eventually we will want this gui to take the scenario string in the constructor
 *
 * @author (nbryant2, zthabet, gbronzi)
 * @version (12.1.18)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemoryGame extends javax.swing.JFrame {
    private String scenario;//should this be static? if not static, can't run in main

    public MemoryGame(String scenario){
        this.scenario = scenario;
    }    

    // public String getScenario(){
        // return scenario;
    // }

    public static void main (String[] args){
        JFrame frame = new JFrame ("Mini Game Challenge");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        MemoryPanel panel = new MemoryPanel("Squirrel");//game text panel 
        //eventually we want to pass various senarios in the constructor or some such
        //MemoryPanel panel = new MemoryPanel(scenario); like this

        frame.setPreferredSize(new Dimension(610, 455));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        //I think this is all we need to have memoryGame take an input
        //new MemoryGame(scenario).setVisible(true);
    }
}
