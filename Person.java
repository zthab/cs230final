
/**
 * The Person class holds all the information for each of the six archetypes. 
 * A person object can have its scores manipulated.
 *
 * @author  Giulia Bronzi
 * @author  Zahra Thabet
 * @author  Nolen Belle Bryant
 * @version 12.17.18
 */
public class Person
{
    private int sleepScore, smartScore, socialScore; 
    private String name;
    
    //the 6 archetypes have different sleep, smart, social score arrays
    private final static int[] athletic = {2, 2, 4};
    private final static int[] hermit = {4, 5, 0};
    private final static int[] horse = {0, 0, 0};
    private final static int[] offCampus = {0, 3, 4};
    private final static int[] society = {1, 2, 4};
    private final static int[] wendy = {2, 3, 3};
    
    //each index of allChars has an array of ints
    private final static int[][] allChars = {athletic,hermit,horse,offCampus,
                                            society,wendy};
    //index of these names correspond to the index of their scores in allChars
    private final static String[] names = {"Athletic Alex", "Hermit Harper",
                                        "Horse Girl Grace", "Off Campus Ollie",
                                         "Society Skylar", "Wendy Wellesley"}; 

    /**
     * Constructor for objects of class Person.
     * 
     * @param charInt the index of the desired archetype in allChars
     */
    public Person(int charInt)
    {
        int[] character = allChars[charInt]; //the archetype's scores
        name = names[charInt];
        //sleep, smart and social in index 0,1,2 respectively
        sleepScore = character[0]+5;
        smartScore = character[1]+5;
        socialScore = character[2]+5;
        
    }
    
    /**
     * Constructor for objects of class Person.
     * 
     * @param n the name of the desired archetype
     */
    public Person(String n){
        try{
        int[] character = allChars[getIndex(n)]; //the archetype's scores
        name = n;
        //sleep, smart and social in index 0,1,2 respectively
        sleepScore = character[0]+5;
        smartScore = character[1]+5;
        socialScore = character[2]+5;
        }catch(IndexOutOfBoundsException e){
            System.out.println("The desired name is not an archetype and thus"+
                                "cannot be found");
        }
    }

    /**
     * Gets the sleep score
     * 
     * @return sleep score
     */
    public int getSleepScore(){
        return sleepScore;
    }
    
    /**
     * Gets the smart score
     * 
     * @return smartScore
     */ 
    public int getSmartScore(){
        return smartScore;
    }
    
    /**
     * Gets the social score
     * 
     * @return socialScore
     */
    public int getSocialScore(){
        return socialScore;
    }
    
    /**
     * Sets the sleep score to the inputted value.
     * 
     * @param int score the desired sleep score value
     */
    public void setSleepScore(int score){
        sleepScore = score;
    }
    
    /**
     * Sets the smart score to the inputted value.
     * 
     * @param int score the desired smart score value
     */
    public void setSmartScore(int score){
        smartScore = score;
    }
    
    /**
     * Sets the social score to the inputted value.
     * 
     * @param int score the desired social score value
     */
    public void setSocialScore(int score){
        socialScore = score;
    }
    
    /**
     * Adds an array of scores to the Person object's scores 
     * 
     * @param scores the scores to be appended to the object's scores
     */
    public void addAllScores(int[] scores){
        sleepScore += scores[0];
        smartScore += scores[1];
        socialScore += scores[2];
    }
    
    /**
     * Checks if scores are above zero
     * 
     * @return true if all scores are above zero
     */
    public boolean isAboveZero(){       
        return sleepScore > 0 && smartScore > 0 && socialScore > 0;      
    }
    
    /**
     * Gets the name of the archetype 
     * 
     * @return name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Given an archetype name, returns the index of its points in all chars.
     * Is a helper method for the constructor that takes a name as its input.
     * Returns -1 if the name cannot be found. 
     * 
     * @param n the name of the archetype
     * @return the index of the archetype's points
     */
    private static int getIndex(String n){
        for (int i =0; i<names.length;i++){
            if (names[i].equals(n)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets a String representation of the Person object.
     * 
     * @return a string representation of the Person object
     */
    public String toString(){
        String ret = name + "\nSleep Score: " + sleepScore + "\nSmart Score: "+
                    smartScore + "\nSocial Score: "+socialScore;
        return ret;
    }
}
