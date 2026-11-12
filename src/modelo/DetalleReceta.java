package modelo;
public class DetalleReceta {
    private int idMedicamento,cantidad;
    public DetalleReceta(int idMedicamento,int cantidad){this.idMedicamento=idMedicamento;this.cantidad=cantidad;}
    public int getIdMedicamento(){return idMedicamento;}
    public int getCantidad(){return cantidad;}
}
