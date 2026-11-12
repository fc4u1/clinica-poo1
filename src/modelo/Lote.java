package modelo;
import java.time.LocalDate;
public class Lote {
    private int id,idMedicamento,idAlmacen,idProveedor,cantidad;
    private LocalDate fechaIngreso,fechaVencimiento;
    public Lote(int id,int idMedicamento,int idAlmacen,LocalDate fechaIngreso,LocalDate fechaVencimiento,int cantidad){
        this(id,idMedicamento,idAlmacen,0,fechaIngreso,fechaVencimiento,cantidad);
    }
    public Lote(int id,int idMedicamento,int idAlmacen,int idProveedor,LocalDate fechaIngreso,LocalDate fechaVencimiento,int cantidad){
        this.id=id;
        this.idMedicamento=idMedicamento;
        this.idAlmacen=idAlmacen;
        this.idProveedor=idProveedor;
        this.fechaIngreso=fechaIngreso;
        this.fechaVencimiento=fechaVencimiento;
        this.cantidad=cantidad;
    }
    public int getId(){return id;} 
    public int getIdMedicamento(){return idMedicamento;}
    public int getIdAlmacen(){return idAlmacen;}
    public int getIdProveedor(){return idProveedor;} 
    public int getCantidad(){return cantidad;}
    public LocalDate getFechaIngreso(){return fechaIngreso;} 
    public LocalDate getFechaVencimiento(){return fechaVencimiento;}
    public void setId(int id) {this.id = id;}
    public void setIdMedicamento(int idMedicamento) {this.idMedicamento = idMedicamento;}
    public void setIdAlmacen(int idAlmacen) {this.idAlmacen = idAlmacen;}
    public void setIdProveedor(int idProveedor) {this.idProveedor = idProveedor;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setFechaIngreso(LocalDate fechaIngreso) {this.fechaIngreso = fechaIngreso;}
    public void setFechaVencimiento(LocalDate fechaVencimiento) {this.fechaVencimiento = fechaVencimiento;}
    
    public void modificarStock(int cantidad){this.cantidad=cantidad;}
    @Override public String toString(){return "Lote "+id+" - Med. "+idMedicamento+" ("+cantidad+" und.)";}
}
