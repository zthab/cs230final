
/**
 * Write a description of class Situation here.
 *
 * @author (zthabet,nbryant2,gbronzi)
 * @version (12.1.18)
 */
public class Situation
{
    String question;
    String option1;
    String option2;

    /**
     * Constructor for objects of class Situation
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
    
}
