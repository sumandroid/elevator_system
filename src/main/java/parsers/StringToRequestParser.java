package parsers;
import domain.models.Request;

public class StringToRequestParser {

    public static Request parseString(String command) throws RuntimeException{
        Request request = new Request();
        String[] tokens = command.split(" ");
        if(tokens.length != 2){
            throw new IllegalArgumentException("Illegal command");
        }
        try{
            request.setFloor(Integer.parseInt(tokens[1]));
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("Illegal floor number");
        }
        return request;
    }
}
