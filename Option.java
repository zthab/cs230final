
/**
 * Write a description of class Option here.
 *
 * @author (zthabet,nbryant2,gbronzi)
 * @version (12.1.18)
 */
public class Option
{
    private String decision;
    private int[] points;
    
    /**
     * Constructor for objects of class Option, option and point are inputted
     */
    public Option(String dec, int[] pts)
    {
        decision = dec;
        points = pts;
    }
    
    public Option(String dec, int sleep, int smart, int social){
        decision=dec;
        int[] temp = new int[3];
        temp[0]=sleep;temp[1]=smart;temp[2]=social;
    }

    public String getDecision(){
        return decision;
    }
    
    public Option getOption(){
        return this;
    }
    
    public int[] getPoints(){
        return points;
    }
    
    public void setDecision(String o){
        decision = o;
    }
    
    public void setPoints(int[] p){
        points = p;
    }
    
    public void setOption(Option op){
        decision=op.getDecision();
        points=op.getPoints();
    }
    
    public String toString(){
        return "Decision: " + decision + "/nSleep: " + points[0] + "/nSmart: " + points[1] 
                + "/nSocial: " + points[2]; 
    }

}
