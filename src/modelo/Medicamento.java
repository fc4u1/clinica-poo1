package modelo;
public class Medicamento {
    private int id,stockMinimo;
    private String nombreComercial,nombreGenerico,tipo;
    public Medicamento(int id,String nombreComercial,String nombreGenerico,String tipo,int stockMinimo){
        this.id=id;
        this.nombreComercial=nombreComercial;
        this.nombreGenerico=nombreGenerico;
        this.tipo=tipo;
        this.stockMinimo=stockMinimo;
    }
    public void setStockMinimo(int stockMinimo) {this.stockMinimo = stockMinimo;}
    public void setNombreComercial(String nombreComercial) {this.nombreComercial = nombreComercial;}
    public void setNombreGenerico(String nombreGenerico) {this.nombreGenerico = nombreGenerico;}
    public void setTipo(String tipo) {this.tipo = tipo;}   
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getNombreComercial(){return nombreComercial;}
    public String getNombreGenerico(){return nombreGenerico;}
    public String getTipo(){return tipo;}
    public int getStockMinimo(){return stockMinimo;}
    
    @Override public String toString(){return nombreComercial;}
}
