
/**
 * The Person class holds all the information for each of the six archetypes. There are getter and 
 * setter methods that are used to get and set the three different score categories (smart, social, 
 * sleep), as well as a method that checks whether all the scores are above zero.
 *
 * @author (gbronzi, zthabet,nbryant2)
 * @version (12.10.18)
 */
public class Person
{
    //instance variables
    private int sleepScore, smartScore, socialScore; // three scores that are based on choosen character
    private int[] character; //array holds all the points for the choosen character
    
    //the six archetypes, each have a different array of sleep, smart, social points
    private final static int[] athletic = {2, 2, 4};
    private final static int[] hermit = {4, 5, 0};
    private final static int[] horse = {0, 0, 0};
    private final static int[] offCampus = {0, 3, 4};
    private final static int[] society = {1, 2, 4};
    private final static int[] wendy = {2, 3, 3};
    
    //array of arrays, each index has an array of ints
    private final static int[][] allChars = {athletic,hermit,horse,offCampus,society,wendy};
    /**
     * Constructor for objects of class Person
     * 
     * @param charInt 
     */
    public Person(int charInt)
    {
        //based on arch picked, a number is inputted which is associated to a index in characters[]
        character = allChars[charInt];
        
        //sleep, smart and social in index 0,1,2 repectively
        sleepScore = character[0]+10;
        smartScore = character[1]+10;
        socialScore = character[2]+10;
        
    }

    /**
     * Returns the sleep score
     * 
     * @return sleepScore
     */
    public int getSleepScore(){
        return sleepScore;
    }
    
    /**
     * Returns the smart score
     * 
     * @return smartScore
     */
    public int getSmartScore(){
        return smartScore;
    }
    
    /**
     * Returns the social score
     * 
     * @return socialScore
     */
    public int getSocialScore(){
        return socialScore;
    }
    
    /**
     * Changes the sleep score to the inputted value
     * 
     * @param int score
     */
    public void setSleepScore(int score){
        sleepScore = score;
    }
    
    /**
     * Changes the smart score to the inputted value
     * 
     * @param int score
     */
    public void setSmartScore(int score){
        smartScore = score;
    }
    
    /**
     * Changes the social score to the inputted value
     * 
     * @param int score
     */
    public void setSocialScore(int score){
        socialScore = score;
    }
    
    public void detractAllScores(int[] scores){
        for (int i =0 ; i <character.length; i ++){
            character[i]-=scores[i];
        }
    }
    
    public void addAllScores(int[] scores){
        for (int i =0 ; i <character.length; i ++){
            character[i]+=scores[i];
        }
    }
    
    /**
     * Checks to see if scores are above zero
     * 
     * @return boolean true if all scores above zero
     */
    public boolean isAboveZero(){       
        return sleepScore > 0 && smartScore > 0 && socialScore > 0;      
    }
    
    public String toString(){
        String ret = "Sleep Score: " + sleepScore + " Smart Score: " + smartScore + " social Score: "+socialScore;
        return ret;
    }
    
}
