package modelo;
import java.time.LocalDate;
import java.time.LocalTime;
public class HorarioMedico {
    private int id,idMedico;
    private LocalDate fecha;
    private LocalTime inicio,fin;
    private String consultorio;
    public HorarioMedico(int id,int idMedico,LocalDate fecha,LocalTime inicio,LocalTime fin,String consultorio){
        this.id=id;this.idMedico=idMedico;this.fecha=fecha;this.inicio=inicio;this.fin=fin;this.consultorio=consultorio;
    }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getIdMedico(){return idMedico;}
    public LocalDate getFecha(){return fecha;}
    public LocalTime getInicio(){return inicio;}
    public LocalTime getFin(){return fin;}
    public String getConsultorio(){return consultorio;}
    @Override public String toString(){return fecha+" "+inicio+"-"+fin+" ("+consultorio+")";}
}
