package domain;

public class AutomataException extends Exception{
    public static final String IN_PROCESS = "En proceso";
    public static final String NO_FILE = "Ningun archivo a sido seleccionado";
    public AutomataException(String message){
        super(message);
    }
}