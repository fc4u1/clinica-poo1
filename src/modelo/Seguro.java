package modelo;
public class Seguro {
    private int id;
    private String aseguradora,tipo,telefono;
    public Seguro(int id,String aseguradora,String tipo,String telefono){this.id=id;this.aseguradora=aseguradora;this.tipo=tipo;this.telefono=telefono;}
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getAseguradora(){return aseguradora;}
    public String getTipo(){return tipo;}
    public String getTelefono(){return telefono;}
    @Override public String toString(){return aseguradora+" - "+tipo;}
}
