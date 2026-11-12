package modelo;
public class Empleado extends Persona {
    public Empleado(int id,String dni,String nombres,String apellidos){
        super(id,dni,nombres,apellidos);
    }
    @Override public String getTipoPersona(){return "Empleado";}
}
