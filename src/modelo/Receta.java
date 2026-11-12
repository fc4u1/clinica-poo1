package modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;
public class Receta {
    private int id,idConsulta;
    private LocalDateTime fecha;
    private String estado;
    private final ArrayList<DetalleReceta> detalles;
    public Receta(int id,int idConsulta,ArrayList<DetalleReceta> detalles){
        this.id=id;this.idConsulta=idConsulta;this.fecha=LocalDateTime.now();this.estado="Pendiente";
        this.detalles=new ArrayList<>(detalles);
    }
    public int getId(){return id;}
    public int getIdConsulta(){return idConsulta;}
    public LocalDateTime getFecha(){return fecha;}
    public String getEstado(){return estado;}
    public ArrayList<DetalleReceta> getDetalles(){return new ArrayList<>(detalles);}
    public void marcarDispensada(){estado="Dispensada";}
    @Override public String toString(){return "Receta #"+id+" (consulta #"+idConsulta+")";}
}
