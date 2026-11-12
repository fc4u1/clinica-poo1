package util;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
public final class Validaciones {
    private Validaciones(){}
    public static String obligatorio(String texto,String nombre){
        if(texto==null||texto.trim().isEmpty())throw new IllegalArgumentException(nombre+" no puede estar vacío.");
        return texto.trim();
    }
    public static String dni(String texto){
        texto=obligatorio(texto,"DNI");
        if(!texto.matches("[0-9]{8}"))throw new IllegalArgumentException("El DNI debe tener 8 dígitos.");
        return texto;
    }
    public static int entero(String texto,String nombre){
        try{return Integer.parseInt(obligatorio(texto,nombre));}
        catch(NumberFormatException ex){throw new IllegalArgumentException(nombre+" debe ser un número entero.");}
    }
    public static LocalDate fecha(String texto){
        try{return LocalDate.parse(obligatorio(texto,"Fecha"));}
        catch(DateTimeParseException ex){throw new IllegalArgumentException("Fecha inválida. Usa AAAA-MM-DD.");}
    }
    public static LocalTime hora(String texto){
        try{return LocalTime.parse(obligatorio(texto,"Hora"));}
        catch(DateTimeParseException ex){throw new IllegalArgumentException("Hora inválida. Usa HH:MM (24 h).");}
    }
}
