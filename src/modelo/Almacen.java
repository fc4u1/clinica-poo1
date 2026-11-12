package modelo;
public class Almacen {
    private int id,capacidad; 
    private String nombre;
    public Almacen(int id,String nombre,int capacidad){
        this.id=id;
        this.nombre=nombre;
        this.capacidad=capacidad;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getCapacidad() {return capacidad;}
    public void setCapacidad(int capacidad) {this.capacidad = capacidad;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    @Override public String toString(){return nombre;}
}
