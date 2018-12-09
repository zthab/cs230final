
/**
 * Write a description of class Person here.
 *
 * @author (gbronzi, zthabet,nbryant2)
 * @version (12.1.18)
 */
public class Person
{
    int sleepScore, smartScore, socialScore;
    int[] character;
    
    private final static int[] athletic = {2, 2, 4};
    private final static int[] hermit = {4, 5, 0};
    private final static int[] horse = {0, 0, 0};
    private final static int[] offCampus = {0, 3, 4};
    private final static int[] society = {1, 2, 3};
    private final static int[] wendy = {2, 3, 3};
    
    private final static int[][] characters = {athletic,hermit,horse,offCampus,society,wendy};
    //was thinking we could do array of arrays for characters, through gui, each button for 
    //the characters has a different number assigned, which will be the charInt inputted 
    //to create the char
    /**
     * Constructor for objects of class Person
     * 
     * @param charInt 
     */
    public Person(int charInt)
    {
        character = characters[charInt];
        sleepScore = character[0];
        smartScore = character[1];
        socialScore = character[2];
        
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
    
}
