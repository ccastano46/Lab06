package domain;

import javax.print.DocFlavor.STRING;

public class AutomataException extends Exception{
    public static final String IN_PROCESS = "En proceso";
    public static final String NO_FILE = "Ningun archivo ha sido seleccionado";
    public static final String WRONG_TYPE_FILE = "Extension no compatible";
    public static final String NO_CELL="No existe alguna celula con el nombre: ";
    public static final String ROW_NOT_NUMBER="No es un numero";
    public static final String OUT_OF_RANGE = "Fuera de rango";
    public static final String NO_VALID = "No valido";
    public AutomataException(String message){
        super(message);
    }
}