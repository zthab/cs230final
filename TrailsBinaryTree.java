import java.io.*;
import javafoundations.*;
import java.util.Scanner;
import java.util.Vector; 
/**
 * Write a description of class TrailsBinaryTree here.
 *
 * @author (gbronzi, zthabet,nbryant2)
 * @version (12.1.18)
 */
public class TrailsBinaryTree
{
    private Vector<Vector<Situation>> years;
    /**
     * Constructor for objects of class TrailsBinaryTree
     */
    public TrailsBinaryTree(){

    }

    public TrailsBinaryTree(String txtFile){
        int count =0;
        String line="";
        String question;
        String option1;
        String option2;
        try{
            File file = new File(txtFile); 
            Scanner sc = new Scanner(file); 

            while (sc.hasNextLine()){ //make vector
                line=sc.nextLine();
                if (line.equals("")){
                    count++;
                }
                //make new situation so need to set up delimiter 
                Scanner scanLine = new Scanner(line);
                scanLine.useDelimiter(",");
                question= scanLine.next();
                option1=scanLine.next();
                option2=scanLine.next();

                years.get(count).add(new Situation(question,option1,option2));
                scanLine.close();
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

    }

    public String getYears(){
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

    public static void main(String []args){
        TrailsBinaryTree a = new TrailsBinaryTree("eee.txt");

    }

}
