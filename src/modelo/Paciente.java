package modelo;
import java.time.LocalDate;
public class Paciente extends Persona {
    private String telefono;
    private LocalDate fechaNacimiento;
    public Paciente(int id,String dni,String nombres,String apellidos,LocalDate fechaNacimiento,String telefono){
        super(id,dni,nombres,apellidos); 
        this.fechaNacimiento=fechaNacimiento;this.telefono=telefono;
    }
     public void setTelefono(String telefono) {this.telefono = telefono;}
    public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}
    public LocalDate getFechaNacimiento(){return fechaNacimiento;}
    public String getTelefono(){return telefono;}
    
    @Override public String getTipoPersona(){return "Paciente";}
}
