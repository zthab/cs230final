
/**
 * The Situation class creates an object that holds a situational question and 
 * the two options that go with the question. The class allows for manipulation
 * of the question and options. A situation object was designed specifically for
 * implementation in a binary tree of Situation objects in that the selection
 * of an Option object will lead to one of two other Situation objects.
 *
 * @author  Zahra Thabet 
 * @author  Giulia Bronzi
 * @version 12.17.18
 */
public class Situation
{
    String question; //question that user is prompted with 
    //Option in response to the question that leads to left in a binary tree
    Option optionLeft; 
    //Option in response to the question that leads to right in a binary tree.
    Option optionRight; 

    /**
     * Constructor for objects of class Situation.
     * 
     * @param q  
     * @param oL
     * @param oR
     */
    public Situation(String q, Option oL, Option oR)
    {
        question = q;
        optionLeft = oL;
        optionRight = oR;
    }
    
    /**
     * Gets the question
     * 
     * @return question
     */
    public String getQuestion(){
        return question;
    }
    
    /**
     * Gets the left-pointing option 
     * 
     * @return optionLeft
     */
    public Option getOptionLeft(){
        return optionLeft;
    }
    
    /**
     * Gets the right-pointing option
     * 
     * @return optionRight
     */
    public Option getOptionRight(){
        return optionRight;
    }
    
    /**
     * Gets a String representation of the Situation object
     * 
     * @return String of the Situation object
     */
    public String toString(){
        return question + " Choice 1: " + optionLeft + " Choice 2: " + 
               optionRight;
    }
}
