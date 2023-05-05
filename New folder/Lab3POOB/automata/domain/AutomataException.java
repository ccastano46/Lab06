package domain;

public class AutomataException extends Exception{
    public static final String IN_PROCESS = "En proceso";
    public AutomataException(String message){
        super(message);
    }
}