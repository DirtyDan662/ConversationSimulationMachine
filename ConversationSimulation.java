import java.util.ArrayList;
/**
 * This class implements a technical support system. It is the top level class 
 * in this project. The support system communicates via text input/output 
 * in the text terminal.
 * 
 * This class uses an object of class InputReader to read input from the user,
 * and an object of class Responder to generate responses. It contains a loop
 * that repeatedly reads input and generates output until the users wants to 
 * leave.
 * 
 * @author     Michael Kölling, David J. Barnes, and DirtyDan
 * @version    0.1 (2016.02.29)
 */
public class ConversationSimulation
{
    private InputReader reader;
    private Responder responder;
    private int lengthLimit = 53;
    
    /**
     * Creates a technical support system.
     */
    public ConversationSimulation()
    {
        reader = new InputReader();
        responder = new Responder();
    }

    /**
     * Start the technical support system. This will print a welcome
     * message and enter into a dialog with the user, until the user
     * ends the dialog.
     */
    public void start()
    {
        boolean finished = false;

        printWelcome();

        while(!finished) {
            String input = reader.getInput();
            if(input.contains("bye")) {
                finished = true;
            }
            else {
                String response = responder.generateResponse(input, reader.getList(input));
                System.out.println(checkLength(response));
            }
        }

        printGoodbye();
    }
    /**
     * Ensures response does not exceed specified limit too much
     * @param   A response from the Responder Class
     */
    private String checkLength(String response){
        if(response.length() > lengthLimit){
            ArrayList<String> words = new ArrayList<>();
            words = reader.getList(response);
            int i = 1;
            response ="";
            for(String word: words){
                response +=" "+ word;
                if(response.length() > lengthLimit*i){
                    response += System.lineSeparator();
                    i++;
                }
                response.trim();
            }
        }
        return response;
    }

    /**
     * Print a welcome message to the screen.
     */
    private void printWelcome()
    {
        System.out.println("Welcome to the Conversation Simulation Machine!");
        System.out.println();
        System.out.println("You are now talking with Emma!");
        System.out.println("She probably doesn't want to talk to you!");
        System.out.println("Please type 'bye' to exit the system.");
    }

    /**
     * Print a good-bye message to the screen.
     */
    private void printGoodbye()
    {
        System.out.println("Aight then...");
    }
    /**
     * Reloads files by reinitalizing responder class
     */
    public void reloadFiles(){
        responder = new Responder();
    }
    /**
     * set a limit on how many characters per line can be displayed
     * default is 53
     * @param lengthLimit
     */
    public void setLengthLimit(int lengthLimit){
        this.lengthLimit = lengthLimit;
    }
    
    
}
