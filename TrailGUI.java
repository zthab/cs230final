
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
/**
 * Write a description of class WellesleyTrailGUI here.
 * look into copyright 
 * @author (zthabet,nbryant2,gbronzi)
 * @version (12.1.18)
 */
public class TrailGUI 
{
    Person p;
    JPanel trailGame; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";
    JButton nextB;
    Vector<Vector<Situation>> tree;
    int counter; 
    public void addComponents(Container pane){
        TrailsBinaryTree theTree = new TrailsBinaryTree("Situations.txt");
        tree = theTree.getYears();
        
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout ///change this to button
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL }; //change this to tree?
        //JComboBox cb = new JComboBox(comboBoxItems);
        //cb.setEditable(false);
        //cb.addItemListener(this);
        //comboBoxPane.add(cb); //change this 
        nextB = new JButton ("Next");
        nextB.addActionListener(new ButtonListener());
        //make Jpanels here 

        JPanel card1 = new StartPanel();

        JPanel card2 = new CharSelection(p);

        //Create the panel that contains the "cards".
        trailGame = new JPanel(new CardLayout());
        trailGame.add(card1, BUTTONPANEL);
        trailGame.add(card2, TEXTPANEL);
        Option one = new Option ("left", new int[]{1,2,3});
        Option two = new Option ("right", new int[]{3,2,1});
        Situation a = new Situation("which way", one,two);
        trailGame.add (new SituationPanel(a,p),"card");
        //for (int i = 0 ; i < tree.size(); i ++){
         //   int j=0;
          //  while(j<tree.get(i).size()){
                
                //trailGame.add(new JPanel(Situation(tree.get(i).get(j))));
                //if ()
            //}
        //}}
        //while loop thats like if counter doesn't reach zero
        //if yes add this one if no add other one

        pane.add(nextB, BorderLayout.PAGE_START); //prev code's switch method
        pane.add(trailGame, BorderLayout.CENTER);
        
    }
    private class ButtonListener implements ActionListener{
        /**
         * Creates a School object when a button is clicked. Only creates
         * it if all the required comboListeners are selected, otherwise 
         * an error will print. Text displaying the input characteristics 
         * will appear in the lowest panel. 
         * 
         * @param Action the event of the button being clicked
         */
        public void actionPerformed(ActionEvent event){
            //iff next button then remove //need a new frame here 
            CardLayout cl = (CardLayout)(trailGame.getLayout());
            cl.show(trailGame, "card");
            //if first option is pressed, add right panel 
            //check to make sure it still has stuff 
        }
    }
    public static void main (String[] args) 
    {

        JFrame frame = new JFrame("Wellesley Trails");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TrailGUI demo = new TrailGUI();
        demo.addComponents(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);

    } 
}
