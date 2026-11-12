package modelo;

public abstract class Persona {
    private int id; 
    private String dni, nombres, apellidos;
    protected Persona(int id,String dni,String nombres,String apellidos){
        this.id=id;
        this.dni=dni;
        this.nombres=nombres;
        this.apellidos=apellidos;
    }
    public void setDni(String dni) {this.dni = dni;}
    public void setNombres(String nombres) {this.nombres = nombres;}
    public void setApellidos(String apellidos) {this.apellidos = apellidos;}
    public String getDni(){return dni;}
    public String getNombres(){return nombres;}
    public String getApellidos(){return apellidos;}
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getNombreCompleto(){return nombres+" "+apellidos;}
    
    public abstract String getTipoPersona();
    @Override public String toString(){return getNombreCompleto();}
}
