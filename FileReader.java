import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Read a text file and create either a HashMap<String, String> or
 * ArrayList<String> with its contents
 *
 * @author DirtyDan
 * @version 2.1.15
 */
public class FileReader
{
    /** textFile to be read */
    private File textFile;
    /** reader with which to read */
    private Scanner reader;
    /** ArrayList containg generic Responses */
    private ArrayList<String> returnArray;
    /** HashMap containg mapped respones */
    private HashMap<String,String> returnMap;

    /**
     * Constructor for objects of class FileReader - nothin to do!
     */
    public FileReader()
    {
    }

    /**
     * Fill Generic Response ArrayList
     *
     * @param     String containg the filename including .txt
     * @return    ArrayList<String> of generic responses
     */
    public ArrayList<String> fillGeneric(String filename){
        textFile = new File(filename);
        returnArray = new ArrayList<>();
        try{
            reader = new Scanner(textFile);
            while(reader.hasNextLine()){
               String data = reader.nextLine();
               returnArray.add(data);
            }
            reader.close();
        }
        
        catch (FileNotFoundException e) {
          System.out.println("An error occurred while lookin for file " + filename);
          e.printStackTrace();
        }
        return returnArray;
    }
    /**
     * Fill HashMap based off a returnArray
     * Does not readfile
     *
     * @param     String containg the filename including .txt
     * @return    HashMap<String,String> of generic responses
     */
    
    public HashMap<String,String> fillMap(String filename){
        returnMap = new HashMap<>();
        returnArray = fillGeneric(filename);
        for(int i=0; i<=returnArray.size(); i= i+2){
            String key = returnArray.get(i);
            String value = returnArray.get(i+1);
            returnMap.put(key,value);
        }
        return returnMap;
    }
    /**
     * Fill HashMap based textfile
     * Does  readfile
     *
     * @param     String containg the filename including .txt
     * @return    HashMap<String,String> of generic responses
     */
    
    public HashMap<String,String> fillMapDiff(String filename){
        returnMap = new HashMap<>();
        textFile = new File(filename); //new file
        try{
           reader = new Scanner(textFile); //call scanner on file
           while(reader.hasNextLine()){
               String key = reader.nextLine();//key = odd lines
               String value = reader.nextLine();//value = even lines
               returnMap.put(key,value);//put key to key value to value
            }
           reader.close();
        }
        
        catch (FileNotFoundException e) {
          System.out.println("An error occurred finding file " + filename);
          //e.printStackTrace();
        }
        return returnMap;
    }
    
    private void printArray(){
        returnArray.stream()
            .forEach(System.out::println);
    }
    private int arraySize(){
        return returnArray.size();
    }
}
