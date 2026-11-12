package modelo;
public class Proveedor {
    private int id; 
    private String ruc, empresa, telefono;
    public Proveedor(int id,String ruc,String empresa,String telefono){
        this.id=id;
        this.ruc=ruc;
        this.empresa=empresa;
        this.telefono=telefono;}
    public int getId(){return id;} 
    public void setId(int id){this.id=id;}
    public String getRuc(){return ruc;} 
    public String getEmpresa(){return empresa;} 
    public String getTelefono(){return telefono;}
    public void setRuc(String ruc) {this.ruc = ruc;}
    public void setEmpresa(String empresa) {this.empresa = empresa;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    
    @Override public String toString(){return empresa;}
}
