
/**
 * Creates an Option object that stores a decision and its corresponding impact
 * on a person's sleep, smart, and social scores. The decision is a string 
 * answer to a question. The Option object was designed to be stored along with
 * a corresponding question in a Situation object.
 *
 * @author gbronzi
 * @author nbryant2
 * @author zthabet 
 * @version 12.17.18
 */
public class Option
{
    private String decision;
    private int[] points;
    
    /**
     * Constructor for objects of class Option that takes its points as a
     * parameter of an array of points.
     * 
     * @param dec the decision text
     * @param pts the points to be added
     */
    public Option(String dec, int[] pts)
    {
        if(pts.length==3){
            decision = dec;
            // for (int i: pts){
            // System.out.println(i);
            // }
            points = pts;
            // for (int i = 0 ; i <points.length; i ++){
            // System.out.println(points[i]);
            // }
            //System.out.println("lalala");
        }else{
            throw new IllegalArgumentException("The length of the inputted "+
                                               "array of points is not 3 "+
                                               "which it must be for an Option"
                                               +" object.");
        }
    }

    /**
     * Constructor for objects of class Option. Allows for it's points to be
     * entered as individual integers rather than an array of intergers.
     * 
     * @param dec    the decision text
     * @param sleep  the sleep points of the Option object
     * @param smart  the smart points of the Option object
     * @param social the social points of the Option object
     */
    public Option(String dec, int sleep, int smart, int social){
        decision=dec;
        points = new int[3];
        points[0]=sleep;
        points[1]=smart;
        points[2]=social;
    }

    /**
     * Gets the decision
     * 
     * @return decision
     */
    public String getDecision(){
        return decision;
    }

    
    /**
     * Gets an array of the points of the Option object.
     * 
     * @return an array of the points of the Option object.
     */
    public int[] getPoints(){
        //for (int i : points){
        //System.out.println("AAA" + points[i]);
        //}
        return points;
        
    }
    
    /**
     * Sets the decision of the Option object.
     * 
     * @param d the new decision String
     */
    public void setDecision(String d){
        decision = d;
    }
    
    /**
     * Sets the points
     * 
     * @param p an array of the points to be assigned
     */
    public void setPoints(int[] p){
        points = p;
    }
    
    /**
     * Gets a String representation of the Option object.
     * 
     * @return a String of the Option object
     */
    public String toString(){
        return "Decision: " + decision + "Sleep: " + points[0] + " Smart: "+
                points[1] + " Social: " + points[2]; 
    }

}
