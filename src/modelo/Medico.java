package modelo;
public class Medico extends Persona {
    private String especialidad;
    public Medico(int id,String dni,String nombres,String apellidos,String especialidad){
        super(id,dni,nombres,apellidos);
        this.especialidad=especialidad;
    }
    public void setEspecialidad(String especialidad) {this.especialidad = especialidad;}
    public String getEspecialidad(){return especialidad;} 
    
    @Override public String getTipoPersona(){return "Médico";}
    @Override public String toString(){return getNombreCompleto()+" - "+especialidad;}
}
