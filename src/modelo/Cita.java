package modelo;
import java.time.LocalDate;
import java.time.LocalTime;
public class Cita {
    private int id,idPaciente,idMedico,idEmpleado;
    private LocalDate fecha; 
    private LocalTime hora; 
    private String estado;
    public Cita(int id,int idPaciente,int idMedico,int idEmpleado,LocalDate fecha,LocalTime hora,String estado){
        this.id=id;
        this.idPaciente=idPaciente;
        this.idMedico=idMedico;
        this.idEmpleado=idEmpleado;
        this.fecha=fecha;
        this.hora=hora;
        this.estado=estado;
    }
    public void setIdPaciente(int idPaciente) {this.idPaciente = idPaciente;}
    public void setIdMedico(int idMedico) {this.idMedico = idMedico;}
    public void setIdEmpleado(int idEmpleado) {this.idEmpleado = idEmpleado;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}
    public void setHora(LocalTime hora) {this.hora = hora;}
    public void setEstado(String estado) {this.estado = estado;}
    public int getId(){return id;}
    public void setId(int value){id=value;}
    public int getIdPaciente(){return idPaciente;}
    public int getIdMedico(){return idMedico;}
    public int getIdEmpleado(){return idEmpleado;}
    public LocalDate getFecha(){return fecha;}
    public LocalTime getHora(){return hora;}
    public String getEstado(){return estado;}
    
    public void cancelar(){estado="Cancelada";} 
    public void eliminar(){estado="Eliminada";}
    public void reprogramar(LocalDate fecha,LocalTime hora){this.fecha=fecha;this.hora=hora;}
}
