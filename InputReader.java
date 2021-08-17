import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
/**
 * InputReader reads typed text input from the standard text terminal. 
 * The text typed by a user is returned.
 * 
 * @author     Michael Kölling, David J. Barnes, and DirtyDan
 * @version    0.1 (2016.02.29)
 */
public class InputReader
{
    private Scanner reader;
    private String inputLine;
    
    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public InputReader()
    {
        reader = new Scanner(System.in);
    }
    
    /**
     * Read a line of text from standard input (the text terminal),
     * and return it as a String.
     *
     * @return  A String typed by the user.
     */
    public String getInput()
    {
        System.out.print("> ");         // print prompt
        inputLine = reader.nextLine().trim()
        .toLowerCase().replace("'","")
        .replace("?", "").replace("!" ,"").replace(".", "");
    
        return inputLine;
    }
    /**
     * Returns an ArrayList containing each word of input
     * @param      A String of Input
     * @return     An ArrayList<String> of each word in Input
     */
    public ArrayList<String> getList(String input){
        String[] words = input.split(" ");
        ArrayList<String> output = new ArrayList<>(Arrays.asList(words));
        
        return output;
    }
}
