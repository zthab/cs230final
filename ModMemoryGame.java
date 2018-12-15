/**
 * Gui for the Modified-MemoryGame mini game. Displays the ModMemoryGame based on a given scenario
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

public class ModMemoryGame 
{
    private String scenario;//should this be static? if not static, can't run in main

    public ModMemoryGame(String scenario){
        this.scenario = scenario;
    }    

    public String getScenario(){
        return scenario;
    }

    public static void main (String[] args){
        JFrame frame = new JFrame ("Mini Game Challenge");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        //eventually we want to pass various senarios in the constructor or some such
        ModMemoryPanel panel = new ModMemoryPanel("Tunnel");//game text panel 

        frame.setPreferredSize(new Dimension(610, 455));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
