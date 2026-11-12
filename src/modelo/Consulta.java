package modelo;
import java.time.LocalDateTime;
public class Consulta {
    private int id, idCita;
    private String sintomas, diagnostico, tratamiento;
    private LocalDateTime fecha;
    public Consulta(int id,int idCita,String sintomas,String diagnostico,String tratamiento){
        this.id=id;this.idCita=idCita;this.sintomas=sintomas;this.diagnostico=diagnostico;this.tratamiento=tratamiento;
        this.fecha=LocalDateTime.now();
    }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getIdCita(){return idCita;}
    public String getSintomas(){return sintomas;}
    public String getDiagnostico(){return diagnostico;}
    public String getTratamiento(){return tratamiento;}
    public LocalDateTime getFecha(){return fecha;}
    public void actualizar(String sintomas,String diagnostico,String tratamiento){this.sintomas=sintomas;this.diagnostico=diagnostico;this.tratamiento=tratamiento;}
    @Override public String toString(){return "Consulta #"+id+" (cita #"+idCita+")";}
}
