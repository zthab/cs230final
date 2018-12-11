
/**
 * The sitution class creates an object that holds a situational question and the two options that
 * go with the question (which are the three instance variables). The class contains getter and 
 * setter methods for the all instance variables. 
 *
 * @author (zthabet,nbryant2,gbronzi)
 * @version (12.10.18)
 */
public class Situation
{
    //instance variable
    String question; //question that user is prompted with 
    String option1; //first option that goes with question
    String option2; //second option that goes with question

    /**
     * Constructor for objects of class Situation, has three parameters that are used to set
     * the question and two option instance variables
     * 
     * @param q,o1,o2 
     */
    public Situation(String q, String o1, String o2)
    {
        question = q;
        option1 = o1;
        option2 = o2;
    }
    
    /**
     * Returns the question
     * 
     * @return question
     */
    public String getQuestion(){
        return question;
    }
    
    /**
     * Returns the first option 
     * 
     * @return option1
     */
    public String getOption1(){
        return option1;
    }
    
    /**
     * Returns the second option
     * 
     * @return option2
     */
    public String getOption2(){
        return option2;
    }
    
    /**
     * Sets the question to the inputted string
     * 
     * @param str
     */
    public void setQuestion(String str){
        question = str;
    }
    
    /**
     * Sets the first option to the inputted string
     * 
     * @param opt
     */
    public void setOption1(String opt){
        option1 = opt;
    }
    
    /**
     * Sets the second option to the inputted string
     * 
     * @param opt
     */
    public void setOption2(String opt){
        option2 = opt;
    }
    
    public String toString(){
        return question + "/nChoice 1: " + option1 + "/nChoice 2: " + option2;
    }
    
}
