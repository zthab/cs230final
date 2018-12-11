import java.io.*;
/**
 * Write a description of class TrailsBinaryTree here.
 *
 * @author (gbronzi, zthabet,nbryant2)
 * @version (12.1.18)
 */
public class TrailsBinaryTree
{

    /**
     * Constructor for objects of class TrailsBinaryTree
     */
    public TrailsBinaryTree(){

    }

    public TrailsBinaryTree(String txtFile){
        File file = new File(txtFile); 
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 

            String st; 
            while ((st = br.readLine()) != null) 
                System.out.println(st); 
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }catch(IOException e){
            System.out.println("File not formatted correctely.");
        }
        
        
    }

}
