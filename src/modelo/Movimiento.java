package modelo;
import java.time.LocalDateTime;
public class Movimiento {
    private int id,idLote,idEmpleado,cantidad;
    private String tipo;
    private LocalDateTime fecha;
    public Movimiento(int id,int idLote,int idEmpleado,String tipo,LocalDateTime fecha,int cantidad){
        this.id=id;
        this.idLote=idLote;
        this.idEmpleado=idEmpleado;
        this.tipo=tipo;
        this.fecha=fecha;
        this.cantidad=cantidad;
    }
    public void setId(int id) {this.id = id;}
    public void setIdLote(int idLote) {this.idLote = idLote;}
    public void setIdEmpleado(int idEmpleado) {this.idEmpleado = idEmpleado;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    public void setFecha(LocalDateTime fecha) {this.fecha = fecha;}
    public int getId(){return id;}
    public int getIdLote(){return idLote;}
    public int getIdEmpleado(){return idEmpleado;}
    public String getTipo(){return tipo;}
    public LocalDateTime getFecha(){return fecha;}
    public int getCantidad(){return cantidad;}
}
