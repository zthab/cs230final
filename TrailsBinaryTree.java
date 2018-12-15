import java.io.*;
import javafoundations.*;
import java.util.Scanner;
import java.util.Vector;

import com.sun.accessibility.internal.resources.accessibility_pt_BR; 
/**
 *Creates a vector of binary trees of situations. Created specifically to 
 *represent choices made in the four years of the Wellesley Trail. Each Vector
 *represents a different year or period of time in the Wellesley experience.
 *
 * @author  zthabet
 * @author  gbronzi
 * @author  nbryant2
 * 
 * @version 12.17.18
 */
public class TrailsBinaryTree
{

    private Vector<Vector<Situation>> years;
    private int vecIndex;//the current vectorIndex
    private int sitIndex; //the current situationIndex

    /**
     * Constructor for objects of class TrailsBinaryTree. Instantiates the 
     * years instance variable as an empty vector.
     */
    public TrailsBinaryTree(){
        years = new Vector<Vector<Situation>>();
        vecIndex=-1;
        sitIndex=-1;
    }

    /**
     * Constructor for objects of class TrailsBinary Tree. Takes parameter of a
     * text file's name. 
     * 
     * @param txtFile Contains the Situation classes that the instance variable
     *                years' vectors will get. Expected format of 
     *                question/dec1/dec1sleep/dec1smart/dec1soc/dec2/dec2sleep/
     *                dec2smart/dec2soc <br></br>
     *                One blank line expected between the different vectors. 
     */
    public TrailsBinaryTree(String txtFile){
        //tracks which situation to get for The Wellesley Trails
        vecIndex = -1;//if a tree exists in the text file, will later get 0
        sitIndex=-1;
        
        years=new Vector<Vector<Situation>>();
        int count =-1; //tracks number of vectors for instantiating years

        String line=""; //line of textfile

        String question=""; //stores question of a specific Situation
        //array to store options of a specific Situation text in.
        Option[] opts =new Option[2];
        //array to store the decisions of the specific options in
        String[] decs = new String[2];

        try{
            File file = new File(txtFile); 
            Scanner sc = new Scanner(file); 

            while (sc.hasNextLine()){
                line=sc.nextLine(); //String of Situation object
                //Array to store the options' points of a specific Situation
                //text in. Initalized inside while loop to prevent Situations
                //from continuously updating their Options' points.
                int[][] decPoints = new int[2][3];
                //if an empty line is detected, number of vectors is increased
                if (line.equals("")){
                    count++;
                    years.add(new Vector<Situation>());
                }else{
                    //parses Situation from line
                    Scanner scanLine = new Scanner(line);
                    scanLine.useDelimiter("/"); 
                    //checks to make sure that line at least has a question
                    if(scanLine.hasNext()){
                        question= scanLine.next();
                        //loops through two Options of the line
                        for (int i = 0; i <decs.length;i++){
                            //checks to make sure that an Option's decision 
                            //text exists
                            if (scanLine.hasNext()){
                                //assigns the decision text to a respective
                                //index of the decision array
                                decs[i]=scanLine.next();
                                //loops through the three points of each Option
                                for (int j=0;j<decPoints[i].length;j++){
                                    //checks for if the points are valid 
                                    //integers
                                    if(scanLine.hasNextInt()){
                                        //assigns option points to the index
                                        //of its corresponding decision in
                                        //decPoints
                                        String p = scanLine.next();
                                        decPoints[i][j]=Integer.parseInt(p);
                                    }else{
                                        throw new IllegalArgumentException(
                                            "File formatted incorrectely at "+
                                            "line: "+ line);
                                    }
                                }
                                //after a decision and its options are assigned
                                //creates a new Option with these values and 
                                //assigns it to the correct index in opts for 
                                //its situation
                                opts[i]=new Option(decs[i],decPoints[i]);
                            }else{
                                throw new IllegalArgumentException("File "+
                                    "formatted incorrectely at line: "+line);
                            }
                        }
                        //after a Situation is created, assigns it to the end
                        //of the last Vector in years
                        //when a situation is created situation index becomes 0
                        sitIndex=0;
                    }else{
                        throw new IllegalArgumentException("File formatted "+
                            "incorrectely at line: " + line);
                    }
                    years.get(count).add(new Situation(
                                                    question,opts[0],opts[1]));
                }
                //when a vector is created vector index becomes 0
                vecIndex=0;
            }
            System.out.println(years.get(0).get(0));
            //Once the textFile runs out of lines, closes the scanner.
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    /**
     * Sets the current vector and situation indices to the left child of the 
     * current situation.
     */
    public void nextLeft(){
        //checks if there are enough situations in the specific vector of years
        if (!((2*(sitIndex+1)-1)>=years.get(vecIndex).size())){
            //Sets Situation index to the (2n-1)th Situation from current index
            sitIndex=2*(sitIndex+1)-1;
        //checks that if there are not enough situations in a vector, if there
        //are more vectors in years
        }else if (vecIndex+1<years.size()){
            //increments the vector index by one, sets the situation index to
            //the beginning of the next the vector 
            vecIndex++;
            sitIndex=0;
        //if there are no more vectors in years, throws exception 
        }else{
            throw(new ArrayIndexOutOfBoundsException("graduated"));
        }
    }

    /**
     * Sets the current vector and situation indices to the right child of the
     * current situation.
     * */
    public void nextRight(){
        //checks if there are enough Situations in the specific vector of years
        if (!((2*(sitIndex+1))>=years.get(vecIndex).size())){
            //Sets Situation index to the (2n)th Situation from current index
            sitIndex=2*(sitIndex+1);
        //checks that if there are not enough Situations in a vector, if there
        //are more vectors in years
        }else if (vecIndex+1<years.size()){
            //increments the vector index by one, sets the situation index to
            //the beginning of the next the vector 
            vecIndex++;
            sitIndex=0;
        //if there are no more vectors in years, throws exception 
        }else{
            throw(new ArrayIndexOutOfBoundsException("graduated"));
        }
    }

    /**
     * Gets the current Situation according to the vector and situation indices
     * 
     * @return the current Situation object
     */
    public Situation getCurrent(){
        try{
            return years.get(vecIndex).get(sitIndex);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Valid vector and situation indices weren't entered.");
        }
        return null;
    }
    
    /**
     * Resets the index and vector values to 0
     */
    public void reset(){
        vecIndex = 0;
        sitIndex = 0;
    }

    /**
     * Gets a String representation of the TrailsBinaryTree. Displays levels in
     * of the tree in different lines.
     * 
     * @return a String of instance variable years
     */
    public String toString(){
        String ret = "";
        //loops through all the Vectors in years
        for (Vector<Situation> i : years){
            //Keeps track of what level of the tree the for loop is on so that
            //new lines can be inserted at proper intervals.
            int twoPow = 0;
            int lineVal = 1; //how many elements have been printed for a level
            for (Situation sit : i ){
                //if all the members of a level have been printed
                if(lineVal>=Math.pow(2,twoPow)){
                    //resets num of objects in a level that have been printed
                    lineVal = 1; 
                    //increases the amount of Situations to be in a level
                    twoPow++; 
                    //concatenates a new line after printing out a level
                    ret+=sit+"\n";
                }else{
                    //if not all Situations of a level have been printed, adds
                    //next one in the Vector to ret and increases the lineVal
                    ret+=sit+" ";
                    lineVal++;
                }
            }
            //adds three empty lines between the Vectors of years 
            ret+="\n\n\n";
        }
        return ret;
    }
}
