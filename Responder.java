import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
/**
 * The responder class represents a response generator object.
 * It is used to generate an automatic response to an input string.
 * 
 * @author     Michael Kölling David J. Barnes, and DirtyDan
 * @version    0.1 (2016.02.29)
 */
public class Responder{
    private ArrayList<String> genericResponses;
    /** list of keys with spaces to be iterated over */
    private ArrayList<String> keys; 
    /** Hash Map of all mapped responses*/
    private HashMap<String,String> responseMap;
    /** random number generator*/
    private Random numGen;
    /** File Reader from file reader class */
    private FileReader reader;
    /** Path to response files */
    private String path = "../responses/";
    /** Last Generated Default Response */
    private String lastResponse = "";
    /**
     * Construct a Responder - initzalise declared values
     * fill generic and mapped responses
     */
    public Responder()
    {
        genericResponses = new ArrayList<>();
        keys = new ArrayList<>();
        responseMap = new HashMap<>();
        
        numGen = new Random();
        reader = new FileReader();
        
        addResponses();
        fillMap();
    }
    
    private void addResponses(){
        genericResponses = reader.fillGeneric(path + "default.txt");
    }
    
    private void fillMap(){
        responseMap = reader.fillMapDiff(path + "map.txt");
        responseMap.entrySet().stream()
                    .filter(entry -> entry.getKey().contains(" "))
                    .forEach(entry -> keys.add(entry.getKey()));
    }
    
    /**
     * Generate a response.
     * @return   A string that should be displayed as the response
     */
    public String generateResponse(String inputLine, ArrayList<String> input)
    {
      String response = responseMap.get(inputLine);
        if(response != null){
            return response;
       }
        else{
            for(String word:input){
                String skeletonKey = "";
                for(String key:keys){ //for each of the keys
                    for(String words:input){ //iterate over every word input
                        if(key.contains(words)){//if key contains word
                            skeletonKey +=  " "+  words;//add word to key
                            skeletonKey = skeletonKey.trim();
                        }
                        response = responseMap.get(skeletonKey);
                        if(response != null){
                            return response;
                        }
                    }
                    skeletonKey = ""; //reset skeletonKey when move to next key
                }
                response = responseMap.get(word); //if not check word by worf
                if(response != null){
                    return response;
                }
            }   
            return getDefaultResponse(input);
        } 
    }
    private String getDefaultResponse(ArrayList<String> input){
        int i = numGen.nextInt(genericResponses.size());
        String response = genericResponses.get(i);
        if(response.equals(lastResponse)){
            response = input.get(input.size()-1) +"?";
        }
        lastResponse = response;
        return response;
    }
    private String listToString(ArrayList<String> list){
        String toString = list.toString().replace(",", "")
        .replace("[","")
        .replace("]","");
        return toString;
    }
}

