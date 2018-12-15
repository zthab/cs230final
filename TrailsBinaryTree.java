import java.io.*;
import javafoundations.*;
import java.util.Scanner;
import java.util.Vector; 
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
        vecIndex = -1;//if a tree exists in the text file, will later get 0
        sitIndex=-1;
        //sets the initial amount of vectors in years to 1
        years=new Vector<Vector<Situation>>();
        years.add(new Vector<Situation>());
        int count =0;

        String line=""; //line of textfile

        String question; //stores question of a specific Situation in
        //array to store options of a specific Situation text in 
        Option[] opts =new Option[2];
        //array to store the decisions of the specific options in
        String[] decs = new String[2];
        //array to store the options' points of a specific Situation text in
        int[][] decPoints = new int[2][3];

        try{
            File file = new File(txtFile); 
            Scanner sc = new Scanner(file); 

            while (sc.hasNextLine()){

                line=sc.nextLine(); //String of Situation object

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
                        //loops through two options of the line
                        for (int i = 0; i <decs.length;i++){
                            //checks to make sure that an option's decision 
                            //text exists
                            if (scanLine.hasNext()){
                                //assigns the decision text to a respective
                                //index of the decision array
                                decs[i]=scanLine.next();
                                //loops through three points of each option
                                for (int j=0;j<decPoints[i].length;j++){
                                    //checks for if the points are valid 
                                    //integers
                                    if(scanLine.hasNextInt()){
                                        //assigns option points to the index
                                        //of its corresponding decision in
                                        //decPoints
                                        int p = Integer.parseInt(
                                                scanLine.next());
                                        decPoints[i][j]=p;
                                    }else{
                                        throw new IllegalArgumentException(
                                            "File formatted incorrectely at line: "
                                            + line);
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
                        years.get(count).add(new Situation(question,opts[0],
                                opts[1]));
                        //when a situation is entered, situation index is no longer -1
                        sitIndex=0;
                    }else{
                        throw new IllegalArgumentException("File formatted "+
                            "incorrectely at line: " + line);
                    }
                }
                //when a vector is created vector index becomes 0
                vecIndex=0;
            }
            //Once the textFile runs out of lines, closes the scanner.
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    /**
     * Gets the Vector instance variable years 
     * 
     * @return years
     */
    public Vector<Vector<Situation>> getYears(){
        return years;
    }

    /**
     * Sets the current vector and situation indices to the left child of the 
     * current situation.
     */
    public void nextLeft(){
        int tempVec =vecIndex;
        int tempSit = sitIndex;

        if (!((2*(sitIndex+1)-1)>=years.get(vecIndex).size())){
            sitIndex=2*(sitIndex+1)-1;
        }else if (vecIndex+1<years.size()){
            vecIndex++;
            sitIndex=0;
        }else{
            throw(new ArrayIndexOutOfBoundsException("graduated"));
        }
    }

    /**
     * Sets the current vector and situation indices to the right child of the
     * current situation.
     * */
    public void nextRight(){
        int tempVec =vecIndex;
        int tempSit = sitIndex;

        if (!((2*(sitIndex+1))>=years.get(vecIndex).size())){
            sitIndex=2*(sitIndex+1);
        }else if (vecIndex+1<years.size()){
            vecIndex++;
            sitIndex=0;
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
