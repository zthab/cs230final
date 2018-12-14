import java.io.*;
import javafoundations.*;
import java.util.Scanner;
import java.util.Vector; 
import java.lang.Throwable;
/**
 *Creates a vector of binary trees of situations. Created specifically to 
 *represent choices made in the four years of the Wellesley Trail. 
 *
 * @author gbronzi
 * @author nbryant2
 * @author zthabet
 * @version (12.1.18)
 */
public class TrailsBinaryTree
{
    private Vector<Vector<Situation>> years;
    /**
     * Constructor for objects of class TrailsBinaryTree.
     * Instantiates the years variable as an empty vector.
     */
    public TrailsBinaryTree(){
        years = new Vector<Vector<Situation>>();
    }

    /**
     * Constructor for objects of class TrailsBinary Tree. Takes parameter of a
     * text file's name. 
     * 
     * @param txtFile Contains the Situation classes that the instance variable
     *                years' vectors will get. Expected format of 
     *                question/dec1/dec1sleep/dec1smart/dec1soc/dec2/dec2sleep/
     *                dec2smart/decsoc <br></br>
     *                One blank line expected between the different vectors. 
     */
    public TrailsBinaryTree(String txtFile){
        //sets the initial amount of vectors in years to 1
        years=new Vector<Vector<Situation>>();
        int count =0;
        years.add(new Vector<Situation>());
        String line="";

        String question;
        Option[] opts =new Option[2];
        String[] decs = new String[2];
        int[][] decPoints = new int[2][3];

        try{
            File file = new File(txtFile); 
            Scanner sc = new Scanner(file); 

            while (sc.hasNextLine()){
                //make vector
                line=sc.nextLine();
                //System.out.println(line);
                //if an empty line is detected, number of vectors is increased
                if (line.equals("")){
                    count++;
                    years.add(new Vector<Situation>());
                }
                //parses Situation from line

                Scanner scanLine = new Scanner(line);
                scanLine.useDelimiter("/");
                if(scanLine.hasNext()){
                    question= scanLine.next();
                    //System.out.println("question " + question);
                    for (int i = 0; i <decs.length;i++){
                        if (scanLine.hasNext()){
                            decs[i]=scanLine.next();
                            //System.out.println(decs[i]);
                            for (int j=0;j<decPoints[i].length;j++){
                                if(scanLine.hasNextInt()){
                                    //System.out.println(i+" "+j);
                                    decPoints[i][j]=Integer.parseInt(scanLine.next());
                                    //System.out.println(decPoints[i][j]);
                                }else{
                                    throw new IllegalArgumentException("File formatted incorrectely at line:" + line);
                                }
                            }
                            opts[i]=new Option(decs[i],decPoints[i]);
                            //System.out.println("options "+ opts[i]);
                        }else{
                            throw new IllegalArgumentException("File formatted incorrectely at line: "+line);
                        }
                    }
                    //System.out.println("size "+ years.size());
                    //System.out.println("options length" + opts.length);
                    //System.out.println("question: " + question);
                    //System.out.println(opts[0]);
                    //System.out.println(opts[1]+"\n");
                    
                    years.get(count).add(new Situation(question,opts[0],opts[1]));
                    scanLine.close();
                }
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

    }

    public Vector<Vector<Situation>> getYears(){
        return years;
    }

    public String toString(){
        String ret = "";
        for (Vector<Situation> i : years){
            int twoPow = 0;//current power of two. Keeps track of what generation the while loop
            //is on so that new lines can be inserted at proper intervals.
            int lineVal = 1; //how many elements have printed so far for that generation
            for (Situation j : i ){

                //if all the members of a generation have been printed
                if(lineVal>=Math.pow(2,twoPow)){  
                    lineVal = 1; //resets amt of members in generation that have been printed
                    twoPow++; //increases the amount of people to be in a generation
                    //concatenates a new line after printing out a generation
                    ret+=j+"\n";
                }else{
                    //if not all members have been printed, adds the next one to the string
                    //and increases the lineVal
                    ret+=j+" ";
                    lineVal++;
                }

            }
            ret+="\n\n\n";
        }
        return ret;
    }
}
