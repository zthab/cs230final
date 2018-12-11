
/**
 * Write a description of class Option here.
 *
 * @author (zthabet,nbryant2,gbronzi)
 * @version (12.1.18)
 */
public class Option
{
    private String option;
    private int[] points;
    
    /**
     * Constructor for objects of class Option, option and point are inputted
     */
    public Option(String opt, int[] pts)
    {
        option = opt;
        points = pts;
    }

    public String getOption(){
        return option;
    }
    
    public int[] getPoints(){
        return points;
    }
    
    public void setOption(String o){
        option = o;
    }
    
    public void setPoints(int[] p){
        points = p;
    }
    
    public String toString(){
        return "Option: " + option + "/nSleep: " + points[0] + "/nSmart: " + points[1] 
                + "/nSocial: " + points[2]; 
    }

}
