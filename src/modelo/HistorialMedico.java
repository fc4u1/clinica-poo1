package modelo;
import java.time.LocalDate;
public class HistorialMedico {
    private int idPaciente; 
    private String sangre, alergias, cronicas, observaciones;
    private LocalDate actualizado;
    public HistorialMedico(int idPaciente,String sangre,String alergias,String cronicas,String observaciones){
        this.idPaciente=idPaciente;
        this.sangre=sangre;
        this.alergias=alergias;
        this.cronicas=cronicas;
        this.observaciones=observaciones;
        this.actualizado=LocalDate.now();
    }
    public int getIdPaciente(){return idPaciente;} 
    public String getSangre(){return sangre;}
    public String getAlergias(){return alergias;} 
    public String getCronicas(){return cronicas;}
    public String getObservaciones(){return observaciones;} 
    public LocalDate getActualizado(){return actualizado;}
    public void setIdPaciente(int idPaciente) {this.idPaciente = idPaciente;}
    public void setSangre(String sangre) {this.sangre = sangre;}
    public void setAlergias(String alergias) {this.alergias = alergias;}
    public void setCronicas(String cronicas) {this.cronicas = cronicas;}
    public void setObservaciones(String observaciones) {this.observaciones = observaciones;}
    public void setActualizado(LocalDate actualizado) {this.actualizado = actualizado;}
}
