
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
    //private int[] character; //array holds all the points for the choosen character
    private String name;
    
    //the six archetypes, each have a different array of sleep, smart, social points
    private final static int[] athletic = {2, 2, 4};
    private final static int[] hermit = {4, 5, 0};
    private final static int[] horse = {0, 0, 0};
    private final static int[] offCampus = {0, 3, 4};
    private final static int[] society = {1, 2, 4};
    private final static int[] wendy = {2, 3, 3};
    
    //array of arrays, each index has an array of ints
    private final static String[] names = {"Athletic Alex", "Hermit Harper", "Horse Girl Grace", "Off Campus Ollie", "Society Skylar", "Wendy Wellesley"};
    private final static int[][] allChars = {athletic,hermit,horse,offCampus,society,wendy};
    /**
     * Constructor for objects of class Person
     * 
     * @param charInt 
     */
    public Person(int charInt)
    {
        //based on arch picked, a number is inputted which is associated to a index in characters[]
        int[] character = allChars[charInt];
        name = names[charInt];
        //sleep, smart and social in index 0,1,2 repectively
        sleepScore = character[0]+5;
        smartScore = character[1]+5;
        socialScore = character[2]+5;
        
    }
    
    public Person()
    {
        
        
        //sleep, smart and social in index 0,1,2 repectively
        sleepScore = 0;
        smartScore = 0;
        socialScore = 0;
        name = "";
    }
    
    public Person(String n){
         //based on arch picked, a number is inputted which is associated to a index in characters[]
        int[] character = allChars[getIndex(n)];
        name = n;
        //sleep, smart and social in index 0,1,2 repectively
        sleepScore = character[0]+5;
        smartScore = character[1]+5;
        socialScore = character[2]+5;
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
    
    public void addAllScores(int[] scores){
        
        sleepScore += scores[0];
        //System.out.println(scores[1]);
        smartScore += scores[1];
       // System.out.println(smartScore);
        socialScore += scores[2];
    }
    
    /**
     * Checks to see if scores are above zero
     * 
     * @return boolean true if all scores above zero
     */
    public boolean isAboveZero(){       
        return sleepScore > 0 && smartScore > 0 && socialScore > 0;      
    }
    
    public String getName(){
        return name;
    }
    
    private static int getIndex(String n){
        for (int i =0; i<names.length;i++){
            if (names[i].equals(n)){
                return i;
            }
        }
        return -1;
    }
    public String toString(){
        String ret = name + "\nSleep Score: " + sleepScore + "\nSmart Score: " + smartScore + "\nSocial Score: "+socialScore;
        return ret;
    }
    
}
