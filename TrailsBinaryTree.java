import java.io.*;
import javafoundations.*;
import java.util.Scanner;
/**
 * Write a description of class TrailsBinaryTree here.
 *
 * @author (gbronzi, zthabet,nbryant2)
 * @version (12.1.18)
 */
public class TrailsBinaryTree
{
    private LinkedBinaryTree<Situation>[] arrYears;
    /**
     * Constructor for objects of class TrailsBinaryTree
     */
    public TrailsBinaryTree(){

    }

    public TrailsBinaryTree(String txtFile){
        int count =0;
        
        try{
            File file = new File(txtFile); 
             Scanner sc = new Scanner(file); 
  
    while (sc.hasNextLine()) 
      //System.out.println(sc.nextLine()); 
      if (sc.nextLine()==" "){
        System.out.println("yes");}
            
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        
        
    }
    public static void main(String []args){
        TrailsBinaryTree a = new TrailsBinaryTree("eee.txt");
    }

}
