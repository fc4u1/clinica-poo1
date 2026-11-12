package modelo;
import java.time.LocalDate;
public class PolizaPaciente {
    private int id,idPaciente,idSeguro;
    private String numero;
    private LocalDate inicio,fin;
    public PolizaPaciente(int id,int idPaciente,int idSeguro,String numero,LocalDate inicio,LocalDate fin){
        this.id=id;this.idPaciente=idPaciente;this.idSeguro=idSeguro;this.numero=numero;this.inicio=inicio;this.fin=fin;
    }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public int getIdPaciente(){return idPaciente;}
    public int getIdSeguro(){return idSeguro;}
    public String getNumero(){return numero;}
    public LocalDate getInicio(){return inicio;}
    public LocalDate getFin(){return fin;}
    public boolean vigente(LocalDate hoy){return !inicio.isAfter(hoy)&&!fin.isBefore(hoy);}
}
