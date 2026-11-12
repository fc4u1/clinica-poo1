package modelo;
public class Usuario {
    private int id, idRol, idEmpleado; 
    private String usuario, claveHash, rol; 
    private boolean activo;
    public Usuario(int id,String usuario,String claveHash,int idRol,String rol,int idEmpleado,boolean activo){
        this.id=id;
        this.usuario=usuario;
        this.claveHash=claveHash;
        this.idRol=idRol;
        this.rol=rol;
        this.idEmpleado=idEmpleado;
        this.activo=activo;
    }
    public int getId(){return id;} 
    public String getUsuario(){return usuario;} 
    public String getClaveHash(){return claveHash;}
    public int getIdRol(){return idRol;} 
    public String getRol(){return rol;}
    public int getIdEmpleado(){return idEmpleado;}
    public void setId(int id) {this.id = id;}
    public void setIdRol(int idRol) {this.idRol = idRol;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public void setClaveHash(String claveHash) {this.claveHash = claveHash;}
    public void setRol(String rol) {this.rol = rol;}
    public boolean isActivo(){return activo;}
    public void setActivo(boolean activo){this.activo=activo;}
    
    public void asignarRol(int id,String nombre){idRol=id;rol=nombre;}
    public void actualizarNombre(String nuevo){usuario=nuevo;}
    public void cambiarClaveHash(String nueva){claveHash=nueva;}
    
    @Override public String toString(){return usuario+" ("+rol+")";}
}
